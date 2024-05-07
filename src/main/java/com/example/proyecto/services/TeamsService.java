package com.example.proyecto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proyecto.models.TeamsModel;
import com.example.proyecto.repositories.TeamsRepository;

@Service
/**
 * La clase TeamsService proporciona métodos para administrar los datos de las ligas.
 */
public class TeamsService {

    /*@Autowired permite inyectar unas dependencias con otras dentro de Spring*/
    @Autowired TeamsRepository tRepository;

    /**
     * El método getAll devuelve una lista de objetos TeamsModel llamando 
     * al método findAll del tRepository.
     * 
     * @return Se devuelve una lista de objetos TeamsModel.
     */
    public List<TeamsModel> getAll(){
        return this.tRepository.findAll();
    }

    /**
     * El método getAllByLeagueId devuelve una lista de objetos TeamsModel por su leagueID.
     * 
     * @param leagueId Es un identificador único que se utiliza para recuperar 
     * una lista de objetos TeamsModel específicos del repositorio.
     * @return Se devuelve una lista de objetos TeamsModel.
     */
    public List<TeamsModel> getAllByLeagueId(Long leagueId){
        return this.tRepository.findByLeagueId(leagueId);
    }

    /**
     * El método getById recupera un objeto TeamsModel por su ID.
     * 
     * @param id Es un identificador único que se utiliza para recuperar un objeto
     * TeamsModel específico del repositorio.
     * @return Se devuelve un objeto TeamsModel.
     */
    public TeamsModel getById(Long id){
        return this.tRepository.findById(id).get();
    }

    /**
     * El método getLeagueIdbyId recupera el leagueId de un TeamsModel a partir de su ID.
     * 
     * @param id Es un identificador único que se utiliza para recuperar el leagueId de un TeamsModel.
     * @return Se devuelve el leagueId.
     */
    public Long getLeagueIdbyId(Long id){
        return this.getById(id).getLeagueId();
    }

    /**
     * El método saveTeam guarda un objeto TeamsModel y devuelve el
     * objeto guardado.
     * 
     * @param tModel Es un objeto de tipo TeamsModel el cual representa un equipo
     * en la base de datos.
     * @return Se devuelve el objeto guardado.
     */
    public TeamsModel saveTeam(TeamsModel tModel){
        return this.tRepository.save(tModel);
    }

    /**
     * El método updateTeam actualiza una objeto TeamsModel con los datos proporcionados y lo guarda
     * en el repositorio.
     * 
     * @param id Es un identificador único que se utiliza para indicar el objeto a actualizar en la
     * base de datos.
     * @param tRequest El parámetro tRequest se utiliza para actualizar los datos de un equipo.
     * @return Se devuelve el objeto actualizado.
     */
    public TeamsModel updateTeam(Long id, TeamsModel tRequest){
        TeamsModel tModel = tRepository.findById(id).get();
        tModel.setTeamName(tRequest.getTeamName());
        tModel.setTeamYear(tRequest.getTeamYear());
        tModel.setTeamCity(tRequest.getTeamCity());
        tModel.setTeamTrophies(tRequest.getTeamTrophies());
        tModel.setTeamImagePath(tRequest.getTeamImagePath());
        return saveTeam(tModel);
    }

    /**
     * El método deleteTeam elimina una entidad de equipo del repositorio por su ID.
     * 
     * @param id Es el identificador único del equipo que debe eliminarse del repositorio.
     */
    public void deleteTeam(Long id){
        this.tRepository.deleteById(id);
    }

}
