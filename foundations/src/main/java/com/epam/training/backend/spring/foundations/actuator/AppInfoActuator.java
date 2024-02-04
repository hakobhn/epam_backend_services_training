package com.epam.training.backend.spring.foundations.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id = "appInfo")
@RequiredArgsConstructor
public class AppInfoActuator {

    private static final String PROFILE = "Profile";
    private static final String DB_URL = "DB_URL";

    private final Environment environment;
    private final DataSourceProperties dataSourceProperties;

    @ReadOperation
    public Map<String, String> getAppInfo() {
        String profiles = String.join(", ", environment.getActiveProfiles());

        return Map.of(
                PROFILE, profiles,
                DB_URL, dataSourceProperties.getUrl()
        );
    }
}
