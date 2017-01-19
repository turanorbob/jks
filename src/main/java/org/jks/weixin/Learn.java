package org.jks.weixin;

import com.alibaba.druid.support.json.JSONUtils;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author liaojian
 * @version 12/10/2016
 */
public class Learn {
    private static Logger logger = LoggerFactory.getLogger(Learn.class);

    private static ResponseHandler<String> stringResponseHandler = new ResponseHandler<String>() {
        @Override
        public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
            int status = httpResponse.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                //获得调用成功后  返回的数据
                HttpEntity entity = httpResponse.getEntity();
                if(null != entity){
                    return EntityUtils.toString(entity);
                }else{
                    return null;
                }
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        }
    };

    private static ResponseHandler<Object> jsonObjectResponseHandler = new ResponseHandler<Object>() {
        @Override
        public Object handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
            int status = httpResponse.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                //获得调用成功后  返回的数据
                HttpEntity entity = httpResponse.getEntity();
                if(null!=entity){
                    String result= EntityUtils.toString(entity);
                    logger.info("response: {}", result);
                    return JSONUtils.parse(result);
                }else{
                    return null;
                }
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        }
    };

    public static String getAccessToken(String appId, String secret){
        String grantType = "client_credential";
        String urlTemplate = "https://api.weixin.qq.com/cgi-bin/token?grant_type=%s&appid=%s&secret=%s";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(String.format(urlTemplate, grantType, appId, secret));

        try{
            Map<String, Object> result = (Map<String, Object>)httpClient.execute(httpGet, jsonObjectResponseHandler);
            logger.info("access token : {}", result.get("access_token"));
            return result.get("access_token") + "";
        }
        catch(IOException e){
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static List<String> getWechatIpList(String accessToken){
        String urlTemplate = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=%s";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(String.format(urlTemplate, accessToken));

        try{
            Map<String, Object> result = (Map<String, Object>)httpClient.execute(httpGet, jsonObjectResponseHandler);
            logger.info("wechat ip list : {}", result.get("ip_list"));
            return (List<String>)result.get("ip_list");
        }
        catch(IOException e){
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 验证消息真实性
     */
    public static boolean checkMsg(String signature, String timestamp, String nonce){
        String str = signature + timestamp + nonce;
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String decrypt = String.valueOf(chars);
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decrypt.getBytes());
            //获取字节数组
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            if(signature.equals(hexString.toString())){
                return true;
            }
        }
        catch(Exception e){
            logger.error(e.getMessage(), e);
        }


        return false;
    }

    public static void main(String args[]){
        String appId = "wx8e619f5a179d87f8";
        String secret = "97eccf7b6b490d393f9b5ab4ae5b6301";
        String token = "E6FEF662-5F5A-8787-9879-ECE7A2056D2E";
        String aesKey = "1";

        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(appId); // 设置微信公众号的appid
        config.setSecret(secret); // 设置微信公众号的app corpSecret
        config.setToken(token); // 设置微信公众号的token
        config.setAesKey(aesKey); // 设置微信公众号的EncodingAESKey

        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(config);

        // 获得access token
        String accessToken = getAccessToken(appId, secret);

        System.out.println(accessToken);

        // 获得IP列表
        //List<String> ipList = getWechatIpList(accessToken);



    }
}
