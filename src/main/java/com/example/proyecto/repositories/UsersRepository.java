package com.example.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.proyecto.models.UsersModel;

@Repository
/*
 * La clase UsersRepository es responsable de acceder a la base de datos y 
 * realizar operaciones CRUD u otras con los datos referentes a los usuarios.
 */
public interface UsersRepository extends JpaRepository<UsersModel,Long>{}
