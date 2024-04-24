package com.example.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyecto.models.UsersModel;
import com.example.proyecto.repositories.UsersRepository;

@Service
public class UsersService {

    @Autowired UsersRepository uRepository;

    public UsersModel saveUser(UsersModel uModel){
        return this.uRepository.save(uModel);
    }

    public UsersModel findUser(String name, String password){
        return this.uRepository.findByUserNameOrUserEmailAndUserPassword(name, name, password);
    }

}
