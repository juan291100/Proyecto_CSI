package com.example.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.proyecto.models.LeaguesModel;
import com.example.proyecto.services.LeaguesService;

@Controller
public class LeaguesController {

    @Autowired LeaguesService lService;

    @GetMapping("/europeanLeagues")
    public String getAllLeagues(Model model){
        model.addAttribute("leagues", lService.getAll());
        return "europeanLeagues/europeanLeagues";
    }

    @GetMapping("/europeanLeagues/add")
    public String saveLeagueForm(Model model){
        LeaguesModel lModel = new LeaguesModel();
        model.addAttribute("league", lModel);
        return "europeanLeagues/addLeague";
    }

    @PostMapping("/europeanLeagues/add")
    public String saveLeague(@ModelAttribute("league") LeaguesModel lModel) {
        lService.saveLeague(lModel);
        return "redirect:/europeanLeagues";
    }

    @GetMapping("/europeanLeagues/update/{leagueId}")
    public String updateStudentForm(@PathVariable Long leagueId, Model model){
        model.addAttribute("league", lService.getById(leagueId));
        return "europeanLeagues/updateLeague";
    }

    @PostMapping("/europeanLeagues/update/{leagueId}")
    public String updateLeague(@PathVariable Long leagueId, @ModelAttribute("league") LeaguesModel lModel){
        lService.updateLeague(leagueId, lModel);
        return "redirect:/europeanLeagues";
    }

    @ResponseBody
    @DeleteMapping("/europeanLeagues/delete/{leagueId}")
    public String deleteLeague(@PathVariable Long leagueId){
        lService.deleteLeague(leagueId);
        return "";
    }

}
