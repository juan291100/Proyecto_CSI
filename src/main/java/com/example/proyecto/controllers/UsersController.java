package com.example.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.proyecto.models.UsersModel;
import com.example.proyecto.services.UsersService;

@Controller
public class UsersController {

    @Autowired UsersService uService;

    @GetMapping("/prueba")
    public String prueba(){
        return "prueba";
    }

    @GetMapping("/login")
    public String login(Model model){
        UsersModel uModel = new UsersModel();
        model.addAttribute("user", uModel);
        return "login";
    }

    @PostMapping("/login")
    public String validateUser(@ModelAttribute("user") UsersModel uModel, Model model){
        uModel.setUserEmail(uModel.getUserName());
        if(uService.validateUser(uModel) == null){
            model.addAttribute("error", "The username, email or password is incorrect.");
            return "login";
        }else{
            return "redirect:/europeanLeagues";
        }
    }

    @GetMapping("/register")
    public String register(Model model){
        UsersModel uModel = new UsersModel();
        model.addAttribute("user", uModel);
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("user") UsersModel uModel, Model model){
        if(uService.saveUser(uModel) == null){
            model.addAttribute("error", "The username or email is already in use.");
            return "register";
        }else{
            return "redirect:/login";
        }
    }

}
