package com.example.proyecto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proyecto.models.PlayersModel;
import com.example.proyecto.repositories.PlayersRepository;

@Service
public class PlayersService {

    @Autowired PlayersRepository pRepository;

    public List<PlayersModel> getAll(){
        return this.pRepository.findAll();
    }

    public PlayersModel getById(Long id){
        return this.pRepository.findById(id).get();
    }

    public List<PlayersModel> searchPlayersString(Long id, String search){
        return this.pRepository.findByTeamIdAndPlayerNameContainingOrTeamIdAndPlayerLastNameContainingOrTeamIdAndPlayerPositionContaining
            (id, search, id, search, id, search);
    }

    public List<PlayersModel> searchPlayersInt(Long id, String search){
        return this.pRepository.findByTeamIdAndPlayerAgeOrTeamIdAndPlayerSquadNumber(id, Integer.parseInt(search), id, Integer.parseInt(search));
    }

    public List<PlayersModel> getAllByTeamId(Long teamId){
        return this.pRepository.findByTeamId(teamId);
    }

    public PlayersModel savePlayer(PlayersModel pModel){
        return this.pRepository.save(pModel);
    }

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

    public void deletePlayer(Long id){
        this.pRepository.deleteById(id);
    }

}
