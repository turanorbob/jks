package org.jks.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liaojian
 * @version 02/06/2017
 */
public class FreeMarkerTest {

    public static void main(String args[]){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("username", "lisi");
        map.put("age", 12);
        String templateString="${username}/${age}";
        StringWriter result = new StringWriter();
        Template t = null;
        try {
            t = new Template("name1", new StringReader(templateString), new Configuration());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            t.process(map, result);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result.toString());
    }
}
