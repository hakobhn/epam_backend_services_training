package com.epam.training.backend.spring.foundations.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DatasourceConfig {

    @Bean
    @Primary
    @ConditionalOnProperty(name = "application.demo.mode", havingValue = "false", matchIfMissing = true)
    public DataSource datasource(DataSourceProperties properties) {
        log.debug("Live mode datasource: driver {}, url {}, username {}, password: {}", properties.getDriverClassName(),
                properties.getUrl(), properties.getUsername(), properties.getDriverClassName());
        return properties.initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @ConditionalOnProperty(name = "application.demo.mode", havingValue = "true", matchIfMissing = false)
    @ConfigurationProperties(prefix="datasource.demo")
    public DataSource demoDataSource(DataSourceProperties properties) {
        log.debug("Demo mode datasource: driver {}, url {}, username {}, password: {}", properties.getDriverClassName(),
                properties.getUrl(), properties.getUsername(), properties.getDriverClassName());
        return properties.initializeDataSourceBuilder()
                .build();
    }

    /**
     * Will be loaded only with env var: SPRING_PROFILES_ACTIVE=prod
     * @param properties
     * @return
     */
    @Bean("RegistryDatasource")
    @Profile("prod")
    @ConfigurationProperties(prefix="datasource.registry")
    public DataSource registryDataSource(DataSourceProperties properties) {
        log.debug("Some prod activities registry datasource: driver {}, url {}, username {}, password: {}", properties.getDriverClassName(),
                properties.getUrl(), properties.getUsername(), properties.getDriverClassName());
        return properties.initializeDataSourceBuilder()
                .build();
    }

}
