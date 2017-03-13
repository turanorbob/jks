package org.jks.utils;

import com.alibaba.druid.support.json.JSONUtils;

/**
 * @author liaojian
 * @version 03/04/2017
 */
public class JsonUtil {

    public static void main(String args[]){
        String tt = "{\n" +
                "\t\"subject\":\"门禁卡绑定失败\",\n" +
                "\t\"content\":\"<html>\n" +
                "<head>\n" +
                "    <meta http-equiv=\\\"Content-Type\\\" content=\\\"text/html; charset=UTF-8\\\" />\n" +
                "    <title>门禁卡绑定失败</title>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\\\"font-size: 16px;\\\">\n" +
                "    <div style=\\\"font-family: 'Microsoft YaHei',simhei;font-weight: normal;color:#6B6B6B;padding-left: 15px; \\\">\n" +
                "        <p style=\\\"line-height:24px;\\\">\n" +
                "            <span>您好，${data.lastName}${data.firstName}。</span>\n" +
                "        </p>\n" +
                "        <p style=\\\"text-indent:36px;  line-height:24px;\\\">\n" +
                "            <span>\n" +
                "                您尝试绑定的卡片（${data.doorAccessCardNumber}）已经被注册！注册人来自${data.organizationName}的${data.registeredUserLastName}${data.registeredUserFirstName}\n" +
                "            </span>\n" +
                "        </p>\n" +
                "        <p style=\\\"line-height:48px;\\\">\n" +
                "            <span>\n" +
                "                梦想加团队\n" +
                "            </span>\n" +
                "        </p>\n" +
                "    </div>\n" +
                "    ${signature}\n" +
                "</body>\n" +
                "\n" +
                "</html>\",\n" +
                "\t\"personal\":\"梦想加\"\n" +
                "}\n" +
                "\n";

        try{
            tt = tt.replaceAll("\n", "");
            tt = tt.replaceAll("\t", "");
            Object aa = JSONUtils.parse(tt);
            System.out.println("ok");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
