package com.example.proyecto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proyecto.models.LeaguesModel;
import com.example.proyecto.repositories.LeaguesRepository;

@Service
public class LeaguesService {

    @Autowired LeaguesRepository lRepository;

    public List<LeaguesModel> getAll(){
        return this.lRepository.findAll();
    }

    public LeaguesModel getById(Long id){
        return this.lRepository.findById(id).get();
    }

    public LeaguesModel saveLeague(LeaguesModel lModel){
        return this.lRepository.save(lModel);
    }

    public LeaguesModel updateLeague(Long id, LeaguesModel lRequest){
        LeaguesModel lModel = lRepository.findById(id).get();
        lModel.setLeagueName(lRequest.getLeagueName());
        lModel.setLeagueCountry(lRequest.getLeagueCountry());
        lModel.setLeagueAssociation(lRequest.getLeagueAssociation());
        return saveLeague(lModel);
    }

    public void deleteLeague(Long id){
        this.lRepository.deleteById(id);
    }

}
