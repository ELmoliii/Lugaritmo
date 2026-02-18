package com.lugaritmo.api.config.factory;

import org.springframework.stereotype.Component;

@Component("dev")
public class DevDatabaseEnvironment implements DatabaseEnvironment {
    @Override
    public String getUrl() {
        // Busca la variable en el .env, si no está usa un valor por defecto
        return System.getProperty("DB_URL");
    }

    @Override
    public String getUsername() {
        return System.getProperty("DB_USER");
    }

    @Override
    public String getPassword() {
        return System.getProperty("DB_PASS");
    }

    @Override
    public String getMessage() {
        return "🚀 MODO DESARROLLO (Cargando datos de .env)";
    }
}