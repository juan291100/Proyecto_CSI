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
import com.example.proyecto.models.TeamsModel;
import com.example.proyecto.services.TeamsService;

@Controller
public class TeamsController {

    @Autowired TeamsService tService;

    @GetMapping("/europeanTeams/{leagueId}")
    public String getAllTeamsByLeagueId(@PathVariable Long leagueId, Model model){
        model.addAttribute("teams", tService.getAllByLeagueId(leagueId));
        return "europeanTeams";
    }

    @PostMapping("/europeanTeams")
    public String saveTeam(@ModelAttribute("team") TeamsModel tModel) {
        tService.saveTeam(tModel);
        return "redirect:/europeanTeams";
    }

    @PutMapping("/europeanTeams/update/{id}")
    public String updateTeam(@PathVariable Long id, @ModelAttribute("team") TeamsModel tModel){
        tService.updateTeam(id, tModel);
        return "redirect:/europeanTeams";
    }

    @DeleteMapping("/europeanTeams/delete/{id}")
    public String deleteTeam(@PathVariable Long id){
        tService.deleteTeam(id);
        return "redirect:/europeanTeams";
    }

}
