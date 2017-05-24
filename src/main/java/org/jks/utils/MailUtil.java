package org.jks.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.jks.model.EmailPayload;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liaojian
 * @version 03/04/2017
 */
public class MailUtil {
    private static final Logger LOG = LoggerFactory.getLogger(MailUtil.class);
    public static String cacheToken;
    public static DateTime lastDateTime;
    public static String lastUsername;
    public static String lastPassword;
    public static String mailServiceUrl = "https://qas.backend.promo.mxj.mx";

    public static String getToken() {
        String username = "api_user";
        String password = "wojiaowhl";

        if (lastDateTime == null || DateTime.now().plusMinutes(-30).isAfter(lastDateTime)
                || !password.equals(lastPassword) || !username.equals(lastUsername)) {

            final String url = String.format("%s/api/jwt/auth", mailServiceUrl);
            Request request = Request.Post(url)
//                    .setHeader("Content-Type", "application/json")
                    .bodyForm(new BasicNameValuePair("name", username), new BasicNameValuePair("password", password));
            Executor executor = Executor.newInstance(/*HttpClientBuilder.insecureHttpClient()*/);

            try {
                String json = executor.execute(request).returnContent().asString(Consts.UTF_8);
                LOG.info(json);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(json);
                Assert.isTrue(node.get("code").asInt() == 200, "获取promo token失败");
                cacheToken = node.get("data").get("token").asText();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            lastPassword = password;
            lastUsername = username;
            lastDateTime = DateTime.now();
        }

        return cacheToken;
    }

    public static void sendEmail(EmailPayload emailPayload) {
        try {
            String token = getToken();
            final String url = String.format("%s/api/mail/html", mailServiceUrl);
            List<BasicNameValuePair> bodyFrom = new ArrayList<>();
            bodyFrom.add(new BasicNameValuePair("emails", StringUtils.join(emailPayload.getToEmails().iterator(),",")));
            bodyFrom.add(new BasicNameValuePair("subject", emailPayload.getSubject()));
            bodyFrom.add(new BasicNameValuePair("from", emailPayload.getFromEmail()));
            bodyFrom.add(new BasicNameValuePair("fromname", emailPayload.getFromName()));
            bodyFrom.add(new BasicNameValuePair("html", emailPayload.getHtml()));

            Request request = Request.Post(url)
                    .addHeader("authorization", "bearer " + token)
                    .bodyForm(bodyFrom, Charset.forName("UTF-8"));
            Executor executor = Executor.newInstance(/*HttpClientBuilder.insecureHttpClient()*/);

            String json = executor.execute(request).returnContent().asString(Consts.UTF_8);
            LOG.info(json);
        } catch (IOException e) {
            LOG.error("send email error:", e);
        }
    }

    public static void main(String args[]){
        EmailPayload emailPayload = new EmailPayload();
        emailPayload.setFromEmail("system@mydreamplus.com");
        emailPayload.setFromName("梦想加");
        emailPayload.setSubject("test");
        emailPayload.setHtml("hello");
        emailPayload.setToEmails(Lists.newArrayList("liaojian.2008.ok@163.com"));
        sendEmail(emailPayload);
    }
}
