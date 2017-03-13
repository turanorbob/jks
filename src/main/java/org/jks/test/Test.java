package org.jks.test;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author liaojian
 * @version 09/22/2016
 */
public class Test {
    public static String encode(String url)
    {


        try {

            Matcher matcher = Pattern.compile("[\\u4e00-\\u9fa5]").matcher(url);
            int count = 0;
            while (matcher.find()) {
                //System.out.println(matcher.group());
                String tmp=matcher.group();
                url=url.replaceAll(tmp,java.net.URLEncoder.encode(tmp,"UTF-8"));
            }
            // System.out.println(count);
            //url = java.net.URLEncoder.encode(url,"gbk");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return url;
    }

    public static void main(String args[]){
        DateTimeZone DEFAULT = DateTimeZone.forID("Asia/Shanghai");
        DateTime startDateTime = DateTime.parse("2017-01-22T09:00:00.000Z");
        startDateTime = startDateTime.withZone(DEFAULT);
        DateTime now = DateTime.now();
        now = now.withZone(DEFAULT);
        Duration duration = new Duration(now.withTime(0,0,0,0), startDateTime.withTime(0,0,0,0));


        System.out.println(duration.getStandardDays());
        System.out.println(System.currentTimeMillis());

        List<String> mails = Lists.newArrayList();
        mails = mails.stream().filter(t->t.equals("BB")).map(String::toString).collect(Collectors.toList());
        System.out.println(StringUtils.join(mails, ","));

        String A = "AA";
        String B = null;
        System.out.println(A.equals(B));

    }
}
