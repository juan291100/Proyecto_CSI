package com.example.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.proyecto.models.TeamsModel;

@Repository
public interface TeamsRepository extends JpaRepository<TeamsModel,Long>{

}
