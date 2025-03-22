package com.agenda.agenda.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Indica que este es un controlador REST
@RequestMapping("/")  // Mapea este controlador a la raíz
public class HomeController {

    @GetMapping  // Maneja solicitudes GET en "/"
    public String home() {
        return "Bienvenido a la API de Agenda";
    }
}