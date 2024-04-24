package com.example.proyecto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proyecto.encrypt.EncryptionService;
import com.example.proyecto.models.UsersModel;
import com.example.proyecto.repositories.UsersRepository;

@Service
public class UsersService {

    @Autowired UsersRepository uRepository;
    EncryptionService eService = new EncryptionService();

    public List<UsersModel> getAll(){
        return this.uRepository.findAll();
    }

    public UsersModel saveUser(UsersModel uModel){
        for(UsersModel u : getAll()){
            decryptUser(u);
            if(u.getUserName().equals(uModel.getUserName()) || u.getUserEmail().equals(uModel.getUserEmail())){
                return null;
            }
            encryptUser(u);
        }
        encryptUser(uModel);
        return this.uRepository.save(uModel);
    }

    public UsersModel validateUser(UsersModel uModel){
        for(UsersModel u : getAll()){
            decryptUser(u);
            if((u.getUserName().equals(uModel.getUserName()) || u.getUserEmail().equals(uModel.getUserEmail())) 
                && u.getUserPassword().equals(uModel.getUserPassword())){
                return u;
            }
            encryptUser(u);
        }
        return null;
    }

    public void encryptUser(UsersModel uModel){
        uModel.setUserName(eService.encrypt(uModel.getUserName()));
        uModel.setUserEmail(eService.encrypt(uModel.getUserEmail()));
        uModel.setUserPassword(eService.encrypt(uModel.getUserPassword()));
    }

    public void decryptUser(UsersModel u){
        u.setUserName(eService.decrypt(u.getUserName()));
        u.setUserEmail(eService.decrypt(u.getUserEmail()));
        u.setUserPassword(eService.decrypt(u.getUserPassword()));
    }

    public void showUser(UsersModel uModel){
        System.out.println(uModel.getUserName());
        System.out.println(uModel.getUserEmail());
        System.out.println(uModel.getUserPassword());
    }

}
