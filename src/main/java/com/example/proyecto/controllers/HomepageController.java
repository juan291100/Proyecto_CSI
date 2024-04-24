package com.example.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.proyecto.services.UsersService;

@Controller
public class HomepageController {

    @Autowired UsersService uService;

    @GetMapping("/")
    public String homepage(){
        return "homepage";
    }
    
}
