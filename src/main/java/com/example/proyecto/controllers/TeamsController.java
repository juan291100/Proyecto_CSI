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
/**
 * La clase TeamsController es responsable de manejar las operaciones CRUD para equipos
 * europeas utilizando anotaciones Spring.
 */
public class TeamsController {

    /*@Autowired permite inyectar unas dependencias con otras dentro de Spring*/
    @Autowired TeamsService tService;
    @Autowired LeaguesService lService;

    /**
     * El método getAllTeamsByLeagueId recupera los equipos según su liga.
     * 
     * @param leagueId El parámetro leagueId representa el identificador único de la liga europea. 
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return El método devuelve el template europeanTeams.
     */
    @GetMapping("/europeanTeams/{leagueId}")
    public String getAllTeamsByLeagueId(@PathVariable Long leagueId, Model model){
        model.addAttribute("teams", tService.getAllByLeagueId(leagueId));
        model.addAttribute("league", lService.getById(leagueId));
        model.addAttribute("leagueId", leagueId);
        return "europeanTeams/europeanTeams";
    }

    /**
     * El método saveTeamForm maneja una solicitud GET para mostrar un formulario para agregar un equipo europeo.
     * 
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return El método devuelve el template addTeam".
     */
    @GetMapping("/europeanTeams/{leagueId}/add")
    public String saveTeamForm(@PathVariable Long leagueId, Model model){
        TeamsModel tModel = new TeamsModel(leagueId);
        model.addAttribute("team", tModel);
        return "europeanTeams/addTeam";
    }

    /**
     * El método saveTeam guarda un objeto de equipo europeo y redirige a la página de europeanTeams.
     * 
     * @param tModel El parámetro tModel es de tipo TeamsModel. Se utiliza para capturar los datos de un equipo
     * que se está agregando o guardando.
     * @return El método redirige al template europeanLeagues.
     */
    @PostMapping("/europeanTeams/{leagueId}/add")
    public String saveTeam(@ModelAttribute("team") TeamsModel tModel) {
        tService.saveTeam(tModel);
        return "redirect:/europeanTeams/{leagueId}";
    }

    /**
     * El método updateTeamForm recupera un equipo europeo específico por su ID para su actualización.
     * 
     * @param teamId El parámetro teamId representa el identificador único del equipo europeo que desea actualizar. 
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return El método devuelve el template updateTeam.
     */
    @GetMapping("/europeanTeams/{leagueId}/update/{teamId}")
    public String updateTeamForm(@PathVariable Long teamId, Model model){
        model.addAttribute("team", tService.getById(teamId));
        return "europeanTeams/updateTeam";
    }

    /**
     * El método updateTeam actualiza un objeto de equipo europeo y redirige a la página de europeanTeams.
     * 
     * @param teamId El parámetro teamId representa el identificador único del equipo europeo que desea actualizar. 
     * @param tModel El parámetro tModel es de tipo TeamsModel. Se utiliza para capturar los datos del equipo
     * que se está actualizando.
     * @return El método redirige al template europeanTeams.
     */
    @PostMapping("/europeanTeams/{leagueId}/update/{teamId}")
    public String updateTeam(@PathVariable Long teamId, @ModelAttribute("team") TeamsModel tModel){
        tService.updateTeam(teamId, tModel);
        return "redirect:/europeanTeams/{leagueId}";
    }

    /**
     * El método deleteTeam elimina un objeto de equipo europeo.
     * 
     * @param teamId El parámetro teamId representa el identificador único del equipo europeo que desea eliminar. 
     * @return El método no devuelve nada para eliminar la fila escogida.
     */
    @ResponseBody
    @DeleteMapping("/europeanTeams/delete/{teamId}")
    public String deleteTeam(@PathVariable Long teamId){
        tService.deleteTeam(teamId);
        return "";
    }

}
