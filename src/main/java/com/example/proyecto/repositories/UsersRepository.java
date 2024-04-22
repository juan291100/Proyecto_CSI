package com.example.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.proyecto.models.UsersModel;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel,Long>{

}
