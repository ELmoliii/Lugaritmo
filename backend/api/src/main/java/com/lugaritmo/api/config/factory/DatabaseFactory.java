package com.lugaritmo.api.config.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Map;

@Configuration
public class DatabaseFactory {

    @Value("${APP_ENV:dev}")
    private String environment;

    @Bean
    @Primary
    public DatabaseEnvironment getDatabaseEnvironment(Map<String, DatabaseEnvironment> environments) {
        String key = environment.toLowerCase();
        return environments.getOrDefault(key, environments.get("dev"));
    }
}