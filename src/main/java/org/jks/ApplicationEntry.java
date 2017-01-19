package org.jks;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

/**
 * @author liaojian
 * @version 10/20/2016
 */
@EnableAutoConfiguration
public class ApplicationEntry {
    private final static Logger LOG = Logger.getLogger(ApplicationEntry.class);

    protected static void run(String args[]){
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setShowBanner(false);

        String host = "127.0.0.1";
        try{
            host = InetAddress.getLocalHost().getHostAddress();
        }
        catch(Exception e){
            LOG.error("get host error, {}", e);
        }
        Environment env = springApplication.run(args).getEnvironment();
        String info = String.format("http://%s:%s", host, env.getProperty("server.port"));
        LOG.info(info);

    }

}
