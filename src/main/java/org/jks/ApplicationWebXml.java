package org.jks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author liaojian
 * @version 11/22/2016
 */
public class ApplicationWebXml extends SpringBootServletInitializer {
    private final Logger LOG = LoggerFactory.getLogger(ApplicationWebXml.class);
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.profiles(addDefaultProfile())
                .showBanner(false)
                .sources(Application.class);
    }

    private String addDefaultProfile() {
        String profile = System.getProperty("spring.profiles.active");
        if (profile != null) {
            LOG.info("Running with Spring profile(s) : {}", profile);
            return profile;
        }

        LOG.warn("No Spring profile configured, running with default configuration");
        return "dev";
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }
}
