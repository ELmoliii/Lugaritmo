package com.lugaritmo.api.config.factory;

public interface DatabaseEnvironment {
    String getUrl();
    String getUsername();
    String getPassword();
    String getMessage();
}