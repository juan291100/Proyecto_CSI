package com.example.proyecto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * La clase HomepageController  es un controlador que asigna el 
 * endpoint "/" a la vista de la página de inicio.
 */
@Controller
public class HomepageController {

    @GetMapping("/")
    public String homepage(){
        return "home/homepage";
    }
    
}
