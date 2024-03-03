package com.epam.training.backend_services.rest.service.impl.heap.config;

import com.epam.training.backend_services.rest.dto.Subscription;
import com.epam.training.backend_services.rest.dto.User;
import com.epam.training.backend_services.rest.service.impl.heap.StorageManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Bean
    public StorageManager<User> userStorageManager() {
        return new StorageManager<>();
    }

    @Bean
    public StorageManager<Subscription> subscriptionStorageManager() {
        return new StorageManager<>();
    }

}
