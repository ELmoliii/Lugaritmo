package com.lugaritmo.api.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SaludoController {

    @QueryMapping
    public String saludo() {
        return "¡Hola! El backend de LUGARITMO ya está vivo y hablando GraphQL.";
    }
}