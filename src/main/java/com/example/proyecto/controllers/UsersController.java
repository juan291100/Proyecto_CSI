package com.example.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.proyecto.models.UsersModel;
import com.example.proyecto.services.UsersService;

@Controller
public class UsersController {

    @Autowired UsersService uService;

    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") UsersModel uModel) {
        uService.saveUser(uModel);
        return "redirect:/users";
    }

}
