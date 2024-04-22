package com.example.proyecto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proyecto.models.TeamsModel;
import com.example.proyecto.repositories.TeamsRepository;

@Service
public class TeamsService {

    @Autowired TeamsRepository tRepository;

    public List<TeamsModel> getAll(){
        return this.tRepository.findAll();
    }

    public TeamsModel getById(Long id){
        return this.tRepository.findById(id).get();
    }

    public TeamsModel saveTeam(TeamsModel tModel){
        return this.tRepository.save(tModel);
    }

    public TeamsModel updateTeam(Long id, TeamsModel tRequest){
        TeamsModel tModel = tRepository.findById(id).get();
        tModel.setLeagueId(tRequest.getLeagueId());
        tModel.setTeamName(tRequest.getTeamName());
        tModel.setTeamYear(tRequest.getTeamYear());
        tModel.setTeamCity(tRequest.getTeamCity());
        tModel.setTeamTrophies(tRequest.getTeamTrophies());
        return saveTeam(tModel);
    }

    public void deleteTeam(Long id){
        this.tRepository.deleteById(id);
    }

}
