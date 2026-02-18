package com.lugaritmo.api.config.factory;

import org.springframework.stereotype.Component;

@Component("prod")
public class ProdDatabaseEnvironment implements DatabaseEnvironment {
    @Override public String getUrl() { return System.getenv("DB_URL"); }
    @Override public String getUsername() { return System.getenv("DB_USER"); }
    @Override public String getPassword() { return System.getenv("DB_PASS"); }
    @Override public String getMessage() { return "💎 MODO PRODUCCIÓN (Nube)"; }
}