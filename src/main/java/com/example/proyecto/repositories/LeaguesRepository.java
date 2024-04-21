package com.example.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.proyecto.models.LeaguesModel;

@Repository
public interface LeaguesRepository extends JpaRepository<LeaguesModel,Long>{

}
