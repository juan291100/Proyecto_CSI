package com.example.proyecto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.proyecto.models.TeamsModel;

@Repository
/*
 * La clase TeamsRepository es responsable de acceder a la base de datos y 
 * realizar operaciones CRUD u otras con los datos referentes a los equipos.
 */
public interface TeamsRepository extends JpaRepository<TeamsModel,Long>{
    public List<TeamsModel> findByLeagueId(Long leagueId);
}
