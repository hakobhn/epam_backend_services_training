package com.epam.training.backend_services.avro_messaging.util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertyLoader {

    public Properties loadProperties() {
        Properties properties = new Properties();
        String propFileName = "config.properties";
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            log.error("Caught exception.", e);
        }
        return properties;
    }

}
