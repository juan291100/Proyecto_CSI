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
        tModel.setIdLeague(tRequest.getIdLeague());
        tModel.setNameTeam(tRequest.getNameTeam());
        tModel.setYearTeam(tRequest.getYearTeam());
        tModel.setCityTeam(tRequest.getCityTeam());
        tModel.setTrophiesTeam(tRequest.getTrophiesTeam());
        return saveTeam(tModel);
    }

    public void deleteTeam(Long id){
        this.tRepository.deleteById(id);
    }

}
