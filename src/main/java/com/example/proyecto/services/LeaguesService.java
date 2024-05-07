package com.example.proyecto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proyecto.models.LeaguesModel;
import com.example.proyecto.repositories.LeaguesRepository;

@Service
/**
 * La clase LeaguesService proporciona métodos para administrar los datos de las ligas.
 */
public class LeaguesService {

    /*@Autowired permite inyectar unas dependencias con otras dentro de Spring*/
    @Autowired LeaguesRepository lRepository;

    /**
     * El método getAll devuelve una lista de objetos LeaguesModel llamando 
     * al método findAll del lRepository.
     * 
     * @return Se devuelve una lista de objetos LeaguesModel.
     */
    public List<LeaguesModel> getAll(){
        return this.lRepository.findAll();
    }

    /**
     * Esta función recupera un objeto LeaguesModel por su ID.
     * 
     * @param id Es un identificador único que se utiliza para recuperar un objeto
     * LeaguesModel específico del repositorio.
     * @return Se devuelve un objeto LeaguesModel.
     */
    public LeaguesModel getById(Long id){
        return this.lRepository.findById(id).get();
    }

    /**
     * El método saveLeague guarda un objeto LeaguesModel y devuelve el
     * objeto guardado.
     * 
     * @param lModel Es un objeto de tipo LeaguesModel el cual representa una liga
     * en la base de datos.
     * @return Se devuelve el objeto guardado.
     */
    public LeaguesModel saveLeague(LeaguesModel lModel){
        return this.lRepository.save(lModel);
    }

    /**
     * El método updateLeague actualiza una objeto LeaguesModel con los datos proporcionados y lo guarda
     * en el repositorio.
     * 
     * @param id Es un identificador único que se utiliza para indicar el objeto a actualizar en la
     * base de datos.
     * @param lRequest El parámetro lRequest se utiliza para actualizar los datos de una liga.
     * @return Se devuelve el objeto actualizado.
     */
    public LeaguesModel updateLeague(Long id, LeaguesModel lRequest){
        LeaguesModel lModel = lRepository.findById(id).get();
        lModel.setLeagueName(lRequest.getLeagueName());
        lModel.setLeagueCountry(lRequest.getLeagueCountry());
        lModel.setLeagueAssociation(lRequest.getLeagueAssociation());
        lModel.setLeagueImagePath(lRequest.getLeagueImagePath());
        lModel.setLeagueCountryShort(lRequest.getLeagueCountryShort());
        lModel.setLeagueCountryFlagImagePath(lRequest.getLeagueCountryFlagImagePath());
        return saveLeague(lModel);
    }

    /**
     * El método deleteLeague elimina una entidad de liga del repositorio por su ID.
     * 
     * @param id Es el identificador único de la liga que debe eliminarse del repositorio.
     */
    public void deleteLeague(Long id){
        this.lRepository.deleteById(id);
    }

}
