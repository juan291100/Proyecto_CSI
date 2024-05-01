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
import com.example.proyecto.models.TeamsModel;
import com.example.proyecto.services.LeaguesService;
import com.example.proyecto.services.TeamsService;

@Controller
public class TeamsController {

    @Autowired TeamsService tService;
    @Autowired LeaguesService lService;

    @GetMapping("/europeanTeams/{leagueId}")
    public String getAllTeamsByLeagueId(@PathVariable Long leagueId, Model model){
        model.addAttribute("teams", tService.getAllByLeagueId(leagueId));
        model.addAttribute("league", lService.getById(leagueId));
        model.addAttribute("leagueId", leagueId);
        return "europeanTeams/europeanTeams";
    }

    @GetMapping("/europeanTeams/{leagueId}/add")
    public String saveTeamForm(@PathVariable Long leagueId, Model model){
        TeamsModel tModel = new TeamsModel(leagueId);
        model.addAttribute("team", tModel);
        return "europeanTeams/addTeam";
    }

    @PostMapping("/europeanTeams/{leagueId}/add")
    public String saveTeam(@ModelAttribute("team") TeamsModel tModel) {
        tService.saveTeam(tModel);
        return "redirect:/europeanTeams/{leagueId}";
    }

    @GetMapping("/europeanTeams/{leagueId}/update/{teamId}")
    public String updateTeamForm(@PathVariable Long teamId, Model model){
        model.addAttribute("team", tService.getById(teamId));
        return "europeanTeams/updateTeam";
    }

    @PostMapping("/europeanTeams/{leagueId}/update/{teamId}")
    public String updateTeam(@PathVariable Long teamId, @ModelAttribute("team") TeamsModel tModel){
        tService.updateTeam(teamId, tModel);
        return "redirect:/europeanTeams/{leagueId}";
    }

    @ResponseBody
    @DeleteMapping("/europeanTeams/delete/{teamId}")
    public String deleteTeam(@PathVariable Long teamId){
        tService.deleteTeam(teamId);
        return "";
    }

}
