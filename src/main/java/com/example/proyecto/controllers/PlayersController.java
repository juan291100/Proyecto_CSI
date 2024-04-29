package com.example.proyecto.controllers;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.proyecto.impresion.OpenPDF;
import com.example.proyecto.models.PlayersModel;
import com.example.proyecto.services.LeaguesService;
import com.example.proyecto.services.PlayersService;
import com.example.proyecto.services.TeamsService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class PlayersController {

    @Autowired PlayersService pService;
    @Autowired TeamsService tService;
    @Autowired LeaguesService lService;

    @GetMapping("/europeanPlayers/{teamId}")
    public String getAllPlayersByTeamId(@PathVariable Long teamId, Model model){
        model.addAttribute("players", pService.getAllByTeamId(teamId));
        model.addAttribute("team", tService.getById(teamId));
        model.addAttribute("league", lService.getById(tService.getLeagueIdbyId(teamId)));
        model.addAttribute("teamId", teamId);
        return "europeanPlayers";
    }

    @PostMapping("/europeanPlayers/{teamId}/searchPlayers")
    public String searchPlayer(@RequestParam(name="searchData") String search, @PathVariable Long teamId, Model model){
        try {
            model.addAttribute("players", pService.searchPlayersInt(teamId, search));
        } catch (NumberFormatException e) {
            model.addAttribute("players", pService.searchPlayersString(teamId, search));
        }
        return "europeanPlayers :: search-results";
    } 

    @GetMapping("/europeanPlayers/{teamId}/add")
    public String savePlayerForm(@PathVariable Long teamId, Model model){
        PlayersModel pModel = new PlayersModel(teamId);
        model.addAttribute("player", pModel);
        return "addPlayer";
    }

    @PostMapping("/europeanPlayers/{teamId}/add")
    public String savePlayer(@ModelAttribute("player") PlayersModel pModel) {
        pService.savePlayer(pModel);
        return "redirect:/europeanPlayers/{teamId}";
    }

    @GetMapping("/europeanPlayers/{teamId}/update/{playerId}")
    public String updatePlayerForm(@PathVariable Long playerId, Model model){
        model.addAttribute("player", pService.getById(playerId));
        return "updatePlayer";
    }

    @PostMapping("/europeanPlayers/{teamId}/update/{playerId}")
    public String updatePlayer(@PathVariable Long playerId, @ModelAttribute("player") PlayersModel pModel){
        pService.updatePlayer(playerId, pModel);
        return "redirect:/europeanPlayers/{teamId}";
    }

    @ResponseBody
    @DeleteMapping("/europeanPlayers/delete/{playerId}")
    public String deletePlayer(@PathVariable Long playerId){
        pService.deletePlayer(playerId);
        return "";
    }

    @GetMapping("/europeanPlayers/{teamId}/exportToPDF")
    public void exportToPDF(@PathVariable Long teamId, HttpServletResponse response) throws IOException{
        OpenPDF openPDF = new OpenPDF();
        openPDF.exportToPdf(pService.getAllByTeamId(teamId), response);
    }

}
