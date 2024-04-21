package com.example.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.proyecto.models.LeaguesModel;
import com.example.proyecto.services.LeaguesService;

@Controller
public class LeaguesController {

    @Autowired LeaguesService lService;

    @GetMapping("/europeanLeagues")
    public String getAllLeagues(Model model){
        model.addAttribute("leagues", lService.getAll());
        return "europeanLeagues";
    }

    @PostMapping("/europeanLeagues")
    public String saveLeague(@ModelAttribute("league") LeaguesModel lModel) {
        lService.saveLeague(lModel);
        return "redirect:/europeanLeagues";
    }

    @PutMapping("/europeanLeagues/update/{id}")
    public String updateLeague(@PathVariable Long id, @ModelAttribute("league") LeaguesModel lModel){
        lService.updateLeague(id, lModel);
        return "redirect:/europeanLeagues";
    }

    @DeleteMapping("/europeanLeagues/delete/{id}")
    public String deleteLeague(@PathVariable Long id){
        lService.deleteLeague(id);
        return "redirect:/europeanLeagues";
    }

}
