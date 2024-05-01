package com.example.proyecto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proyecto.models.PlayersModel;
import com.example.proyecto.repositories.PlayersRepository;

@Service
/**
 * La clase PlayersService proporciona métodos para administrar los datos de los jugadores.
 */
public class PlayersService {

    /*@Autowired permite inyectar unas dependencias con otras dentro de Spring*/
    @Autowired PlayersRepository pRepository;

    /**
     * El método getAll devuelve una lista de objetos PlayersModel llamando 
     * al método findAll del pRepository.
     * 
     * @return Se devuelve una lista de objetos PlayersModel.
     */
    public List<PlayersModel> getAll(){
        return this.pRepository.findAll();
    }

    /**
     * El método getById recupera un objeto PlayersModel por su ID.
     * 
     * @param id Es un identificador único que se utiliza para recuperar un objeto
     * PlayersModel específico del repositorio.
     * @return Se devuelve un objeto PlayersModel.
     */
    public PlayersModel getById(Long id){
        return this.pRepository.findById(id).get();
    }

    /**
     * El método getAllByLTeamId devuelve una lista de objetos PlayersModel por su teamID.
     * 
     * @param teamId Es un identificador único que se utiliza para recuperar 
     * una lista de objetos PlayersModel específicos del repositorio.
     * @return Se devuelve una lista de objetos PlayersModel.
     */
    public List<PlayersModel> getAllByTeamId(Long teamId){
        return this.pRepository.findByTeamId(teamId);
    }

    /**
     * El método searchPlayersString busca jugadores en un repositorio basándose en el ID del equipo y una cadena de
     * búsqueda que contiene el nombre, apellido o posición del jugador.
     * 
     * @param id Representa el ID del equipo que se utiliza para buscar jugadores.
     * @param search String que se utiliza para buscar jugadores según su nombre, apellido o posición.
     * @return Una lista de objetos PlayersModel que coinciden con los criterios de búsqueda.
     */
    public List<PlayersModel> searchPlayersString(Long id, String search){
        return this.pRepository.findByTeamIdAndPlayerNameContainingOrTeamIdAndPlayerLastNameContainingOrTeamIdAndPlayerPositionContaining
            (id, search, id, search, id, search);
    }

    /**
     * El método searchPlayersInt busca jugadores según el ID de su equipo, la edad del jugador y el número de
     * plantilla del jugador.
     * 
     * @param id Representa el ID del equipo que se utiliza para buscar jugadores.
     * @param search String que se utiliza para buscar jugadores según su edad o número de equipo.
     * @return Una lista de objetos PlayersModel que coinciden con los criterios de búsqueda.
     */
    public List<PlayersModel> searchPlayersInt(Long id, String search){
        return this.pRepository.findByTeamIdAndPlayerAgeOrTeamIdAndPlayerSquadNumber
            (id, Integer.parseInt(search), id, Integer.parseInt(search));
    }

    /**
     * El método savePlayer guarda un objeto PlayersModel y devuelve el
     * objeto guardado.
     * 
     * @param pModel Es un objeto de tipo PlayersModel el cual representa un jugador
     * en la base de datos.
     * @return Se devuelve el objeto guardado.
     */
    public PlayersModel savePlayer(PlayersModel pModel){
        return this.pRepository.save(pModel);
    }

    /**
     * El método updatePlayer actualiza una objeto PlayersModel con los datos proporcionados y lo guarda
     * en el repositorio.
     * 
     * @param id Es un identificador único que se utiliza para indicar el objeto a actualizar en la
     * base de datos.
     * @param pRequest El parámetro pRequest se utiliza para actualizar los datos de un jugador.
     * @return Se devuelve el objeto actualizado.
     */
    public PlayersModel updatePlayer(Long id, PlayersModel pRequest){
        PlayersModel pModel = pRepository.findById(id).get();
        pModel.setTeamId(pRequest.getTeamId());
        pModel.setPlayerName(pRequest.getPlayerName());
        pModel.setPlayerLastName(pRequest.getPlayerLastName());
        pModel.setPlayerAge(pRequest.getPlayerAge());
        pModel.setPlayerPosition(pRequest.getPlayerPosition());
        pModel.setPlayerSquadNumber(pRequest.getPlayerSquadNumber());
        return savePlayer(pModel);
    }

    /**
     * El método deletePlayer elimina una entidad de equipo del repositorio por su ID.
     * 
     * @param id Es el identificador único del jugador que debe eliminarse del repositorio.
     */
    public void deletePlayer(Long id){
        this.pRepository.deleteById(id);
    }

}
