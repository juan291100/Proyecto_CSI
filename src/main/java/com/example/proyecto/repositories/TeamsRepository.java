package com.example.proyecto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.proyecto.models.TeamsModel;

@Repository
public interface TeamsRepository extends JpaRepository<TeamsModel,Long>{
    public List<TeamsModel> findByLeagueId(Long leagueId);
}
