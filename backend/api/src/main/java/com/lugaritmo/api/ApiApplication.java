package com.lugaritmo.api;

import com.lugaritmo.api.config.factory.DatabaseEnvironment; // Importamos la interfaz
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        // 1. Cargamos el .env para que la fábrica sepa si es 'dev' o 'prod'
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        // 2. Arrancamos la aplicación y guardamos el "contexto"
        ConfigurableApplicationContext context = SpringApplication.run(ApiApplication.class, args);

        // 3. Le pedimos a la fábrica el objeto que ha creado
        DatabaseEnvironment dbEnv = context.getBean(DatabaseEnvironment.class);

        // 4. El print de la victoria
        System.out.println("\n--- COMPROBACIÓN DE FÁBRICA (Factory Method) ---");
        System.out.println(dbEnv.getMessage()); // Nos dirá si es Desarrollo o Producción
        System.out.println("Conectado a: " + dbEnv.getUrl());
        System.out.println("Usuario: " + dbEnv.getUsername());
        System.out.println("--------------------------------------------------\n");
    }
}