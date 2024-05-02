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

/**
 * La clase LeaguesController es responsable de manejar las operaciones CRUD para ligas
 * europeas utilizando anotaciones Spring.
 */
@Controller
public class LeaguesController {

    /*@Autowired permite inyectar unas dependencias con otras dentro de Spring*/
    @Autowired LeaguesService lService;

    /**
     * El método getAllLeagues recupera todas las ligas europeas para mostrarlas en el template europeanLeagues.
     * 
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return El método redirige al template europeanLeagues.
     */
    @GetMapping("/europeanLeagues")
    public String getAllLeagues(Model model){
        model.addAttribute("leagues", lService.getAll());
        return "europeanLeagues/europeanLeagues";
    }

    /**
     * El método saveLeagueForm maneja una solicitud GET para mostrar un formulario para agregar una liga europea.
     * 
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return El método devuelve el template addLeague".
     */
    @GetMapping("/europeanLeagues/add")
    public String saveLeagueForm(Model model){
        LeaguesModel lModel = new LeaguesModel();
        model.addAttribute("league", lModel);
        return "europeanLeagues/addLeague";
    }

    /**
     * El método saveLeague guarda un objeto de liga europea y redirige a la página de europeanLeagues.
     * 
     * @param lModel El parámetro lModel es de tipo LeaguesModel. Se utiliza para capturar los datos de una liga
     * que se está agregando o guardando.
     * @return El método redirige al template europeanLeagues.
     */
    @PostMapping("/europeanLeagues/add")
    public String saveLeague(@ModelAttribute("league") LeaguesModel lModel) {
        lService.saveLeague(lModel);
        return "redirect:/europeanLeagues";
    }

    /**
     * El método updateLeagueForm recupera una liga europea específica por su ID para su actualización.
     * 
     * @param leagueId El parámetro leagueId representa el identificador único de la liga europea que desea actualizar. 
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return El método devuelve el template updateLeague.
     */
    @GetMapping("/europeanLeagues/update/{leagueId}")
    public String updateLeagueForm(@PathVariable Long leagueId, Model model){
        model.addAttribute("league", lService.getById(leagueId));
        return "europeanLeagues/updateLeague";
    }

    /**
     * El método updateLeague actualiza un objeto de liga europea y redirige a la página de europeanLeagues.
     * 
     * @param leagieId El parámetro leagueId representa el identificador único de la liga europea que desea actualizar. 
     * @param lModel El parámetro lModel es de tipo LeaguesModel. Se utiliza para capturar los datos de una liga
     * que se está actualizando.
     * @return El método redirige al template europeanLeagues.
     */
    @PostMapping("/europeanLeagues/update/{leagueId}")
    public String updateLeague(@PathVariable Long leagueId, @ModelAttribute("league") LeaguesModel lModel){
        lService.updateLeague(leagueId, lModel);
        return "redirect:/europeanLeagues";
    }

    /**
     * El método deleteLeague elimina un objeto de liga europea.
     * 
     * @param leagieId El parámetro leagueId representa el identificador único de la liga europea que desea eliminar. 
     * @return El método no devuelve nada para eliminar la fila escogida.
     */
    @ResponseBody
    @DeleteMapping("/europeanLeagues/delete/{leagueId}")
    public String deleteLeague(@PathVariable Long leagueId){
        lService.deleteLeague(leagueId);
        return "";
    }

}
