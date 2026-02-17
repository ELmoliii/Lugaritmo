package com.lugaritmo.api.config.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseFactory {

    @Value("${APP_ENV:dev}")
    private String environment;

    @Bean
    public DatabaseEnvironment getDatabaseEnvironment() {
        if ("prod".equalsIgnoreCase(environment)) {
            return new ProdDatabaseEnvironment();
        } else {
            return new DevDatabaseEnvironment();
        }
    }
}