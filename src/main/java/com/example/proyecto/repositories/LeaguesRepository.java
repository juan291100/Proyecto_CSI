package com.example.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.proyecto.models.LeaguesModel;

@Repository
/*
 * La clase LeaguesRepository es responsable de acceder a la base de datos y 
 * realizar operaciones CRUD u otras con los datos referentes a las ligas.
 */
public interface LeaguesRepository extends JpaRepository<LeaguesModel,Long>{}
