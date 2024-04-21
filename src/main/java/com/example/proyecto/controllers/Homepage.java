package com.example.proyecto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homepage {

    @GetMapping("/")
    public String homepage(){
        return "homepage";
    }

    @GetMapping("/prueba")
    public String prueba(){
        return "prueba";
    }

}
