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
import com.example.proyecto.models.PlayersModel;
import com.example.proyecto.services.PlayersService;

@Controller
public class PlayersController {

    @Autowired PlayersService pService;

    @GetMapping("/europeanPlayers/{teamId}")
    public String getAllPlayersByTeamId(@PathVariable Long teamId, Model model){
        model.addAttribute("players", pService.getAllByTeamId(teamId));
        return "europeanPlayers";
    }

    @PostMapping("/europeanPlayers")
    public String savePlayer(@ModelAttribute("player") PlayersModel pModel) {
        pService.savePlayer(pModel);
        return "redirect:/europeanPlayers";
    }

    @PutMapping("/europeanPlayers/update/{id}")
    public String updatePlayer(@PathVariable Long id, @ModelAttribute("league") PlayersModel pModel){
        pService.updatePlayer(id, pModel);
        return "redirect:/europeanPlayers";
    }

    @DeleteMapping("/europeanPlayers/delete/{id}")
    public String deletePlayer(@PathVariable Long id){
        pService.deletePlayer(id);
        return "redirect:/europeanPlayers";
    }

}
