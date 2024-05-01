package com.example.proyecto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.proyecto.models.PlayersModel;

@Repository
/*
 * La clase PlayersRepository es responsable de acceder a la base de datos y 
 * realizar operaciones CRUD u otras con los datos referentes a los jugadores.
 */
public interface PlayersRepository extends JpaRepository<PlayersModel,Long>{
    public List<PlayersModel> findByTeamId(Long teamId);
    public List<PlayersModel> findByTeamIdAndPlayerNameContainingOrTeamIdAndPlayerLastNameContainingOrTeamIdAndPlayerPositionContaining
        (Long teamId, String name, Long teamId2, String lastName, Long teamId3, String position);
    public List<PlayersModel> findByTeamIdAndPlayerAgeOrTeamIdAndPlayerSquadNumber(Long teamId, int age, Long teamId2, int squadNumber);
}
