package org.jks.test;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    }
}
