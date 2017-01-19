package org.jks.config;

import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author liaojian
 * @version 12/05/2016
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration implements EnvironmentAware {
    private Environment env;
    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
    }

    @Bean
    @ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
        liquibase.setShouldRun(true);
        return liquibase;
    }

    @Bean
    public Hibernate4Module hibernate4Module() {
        return new Hibernate4Module();
    }
}
