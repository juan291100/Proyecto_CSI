package com.example.proyecto.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.example.proyecto.impresion.OpenPDF;
import com.example.proyecto.models.PlayersModel;
import com.example.proyecto.services.LeaguesService;
import com.example.proyecto.services.PlayersService;
import com.example.proyecto.services.TeamsService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
/**
 * La clase PlayersController es responsable de manejar las operaciones CRUD para jugadores
 * europeos utilizando anotaciones Spring.
 */
public class PlayersController {

    /*@Autowired permite inyectar unas dependencias con otras dentro de Spring*/
    @Autowired PlayersService pService;
    @Autowired TeamsService tService;
    @Autowired LeaguesService lService;

    /**
     * El método getAllPlayersByTeamId recupera los jugadores según su equipo.
     * 
     * @param teamId El parámetro teamId representa el identificador único del equipo europeo. 
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return El método devuelve el template europeanPlayers.
     */
    @GetMapping("/europeanPlayers/{teamId}")
    public String getAllPlayersByTeamId(@PathVariable Long teamId, Model model){
        model.addAttribute("players", pService.getAllByTeamId(teamId));
        model.addAttribute("team", tService.getById(teamId));
        model.addAttribute("league", lService.getById(tService.getLeagueIdbyId(teamId)));
        model.addAttribute("teamId", teamId);
        return "europeanPlayers/europeanPlayers";
    }

    /**
     * El método searchPlayer busca los jugadores segun el input escrito.
     * 
     * @param search El parámetro search representa el String de busqueda. 
     * @param teamId El parámetro teamId representa el identificador único del equipo europeo. 
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return El método devuelve el template europeanPlayers, especificamente la tabla de los jugadores.
     */
    @PostMapping("/europeanPlayers/{teamId}/searchPlayers")
    public String searchPlayer(@RequestParam(name="searchData") String search, @PathVariable Long teamId, Model model){
        try {
            model.addAttribute("players", pService.searchPlayersInt(teamId, search));
        } catch (NumberFormatException e) {
            model.addAttribute("players", pService.searchPlayersString(teamId, search));
        }
        return "europeanPlayers/europeanPlayers :: updateTable";
    } 

    /**
     * El método savePlayerForm maneja una solicitud GET para mostrar un formulario para agregar un jugador europeo.
     * 
     * @param teamId El parámetro teamId representa el identificador único del equipo europeo. 
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return El método devuelve el template addPlayer".
     */
    @GetMapping("/europeanPlayers/{teamId}/add")
    public String savePlayerForm(@PathVariable Long teamId, Model model){
        PlayersModel pModel = new PlayersModel(teamId);
        model.addAttribute("player", pModel);
        return "europeanPlayers/addPlayer";
    }

    /**
     * El método savePlayer guarda un objeto de jugador europeo y redirige a la página de europeanPlayers.
     * 
     * @param pModel El parámetro tModel es de tipo PlayersModel. Se utiliza para capturar los datos de un jugador
     * que se está agregando o guardando.
     * @return El método redirige al template europeanPlayers.
     */
    @PostMapping("/europeanPlayers/{teamId}/add")
    public String savePlayer(@ModelAttribute("player") PlayersModel pModel) {
        pService.savePlayer(pModel);
        return "redirect:/europeanPlayers/{teamId}";
    }

    /**
     * El método updatePlayerForm recupera un jugador europeo específico por su ID para su actualización.
     * 
     * @param playerId El parámetro playerId representa el identificador único del jugador europeo que desea actualizar. 
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return El método devuelve el template updatePlayer.
     */
    @GetMapping("/europeanPlayers/{teamId}/update/{playerId}")
    public String updatePlayerForm(@PathVariable Long playerId, Model model){
        model.addAttribute("player", pService.getById(playerId));
        return "europeanPlayers/updatePlayer";
    }

    /**
     * El método updatePlayer actualiza un objeto de juagdor europeo y redirige a la página de europeanPlayers.
     * 
     * @param playerId El parámetro playerId representa el identificador único del jugador europeo que desea actualizar. 
     * @param pModel El parámetro pModel es de tipo PlayersModel. Se utiliza para capturar los datos del jugador
     * que se está actualizando.
     * @return El método redirige al template europeanPlayers.
     */
    @PostMapping("/europeanPlayers/{teamId}/update/{playerId}")
    public String updatePlayer(@PathVariable Long playerId, @ModelAttribute("player") PlayersModel pModel){
        pService.updatePlayer(playerId, pModel);
        return "redirect:/europeanPlayers/{teamId}";
    }

    /**
     * El método deletePlayer elimina un objeto de jugador europeo.
     * 
     * @param playerId El parámetro playerId representa el identificador único del jugador europeo que desea eliminar. 
     * @return El método no devuelve nada para eliminar la fila escogida.
     */
    @ResponseBody
    @DeleteMapping("/europeanPlayers/delete/{playerId}")
    public String deletePlayer(@PathVariable Long playerId){
        pService.deletePlayer(playerId);
        return "";
    }

    /**
     * El método exportToPDF exporta una tabla de jugafores a PDF.
     * 
     * @param teamId El parámetro teamId representa el identificador único del equipo europeo. 
     * @param response El parámetro respuesta se utiliza para enviar el archivo PDF generado al cliente/navegador.
     */
    @GetMapping("/europeanPlayers/{teamId}/exportToPDF")
    public void exportToPDF(@PathVariable Long teamId, HttpServletResponse response) throws IOException{
        OpenPDF openPDF = new OpenPDF();
        openPDF.exportToPdf(pService.getAllByTeamId(teamId), response);
    }

    /**
     *  El método exportToTxt exporta una tabla de jugafores a .txt.
     * 
     * @param teamId El parámetro teamId representa el identificador único del equipo europeo. 
     * @param response El parámetro respuesta se utiliza para enviar el archivo PDF generado al cliente/navegador.
     * @return La respuesta von el inputStreamResource
     */
    @GetMapping("/europeanPlayers/{teamId}/exportToTxt")
    public ResponseEntity<InputStreamResource> exportToTxt(@PathVariable Long teamId, HttpServletResponse response) 
            throws IOException{

        File fileToExport = new File("exportarListaJugadores.txt");
        fileToExport.createNewFile();
        fileToExport.deleteOnExit();
        FileWriter fileWriter = new FileWriter(fileToExport);
        for(PlayersModel pModel : pService.getAllByTeamId(teamId)){
            fileWriter.write(pModel.getPlayerName() + " || " + pModel.getPlayerLastName() + 
                " || " + pModel.getPlayerAge() + " || " + pModel.getPlayerPosition() + 
                    " || " + pModel.getPlayerSquadNumber() + "\n");
        }
        fileWriter.close();
		InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(fileToExport));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileToExport.getName())
				.contentType(MediaType.TEXT_PLAIN)
				.contentLength(fileToExport.length())
				.body(inputStreamResource);

    }

    /**
     *  El método importFromTxt exporta una tabla de jugafores a .txt.
     * 
     * @param teamId El parámetro teamId representa el identificador único del equipo europeo. 
     * @param mFile El archivo a importar obtenido del formulario.
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return El método devuelve el template europeanPlayers, en especifico la tabla que contiene.
     */
    @PostMapping("/europeanPlayers/{teamId}/importFromTxt")
    public String importFromTxt(@PathVariable Long teamId, @RequestParam("file") MultipartFile mFile, Model model) throws IOException {

        File fileToImport = new File("importarListaJugadores.txt");
        fileToImport.createNewFile();
        fileToImport.deleteOnExit();
        FileOutputStream fos = new FileOutputStream(fileToImport); 
        fos.write(mFile.getBytes());
        FileReader fileReader = new FileReader(fileToImport);
        Scanner scanner = new Scanner(fileReader);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            String separator = Pattern.quote(" || ");
            String[] data_split = data.split(separator);
            PlayersModel pModel = new PlayersModel(teamId, data_split[0], data_split[1], Integer.parseInt(data_split[2]), 
                data_split[3], Integer.parseInt(data_split[4]));
            pService.savePlayer(pModel);
        }
        fos.close();
        scanner.close();
        model.addAttribute("players", pService.getAllByTeamId(teamId));
        return "europeanPlayers/europeanPlayers :: updateTable";

    }
}
