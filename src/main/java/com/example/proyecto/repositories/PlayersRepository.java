package com.example.proyecto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.proyecto.models.PlayersModel;

@Repository
public interface PlayersRepository extends JpaRepository<PlayersModel,Long>{
    public List<PlayersModel> findByTeamId(Long teamId);
}
