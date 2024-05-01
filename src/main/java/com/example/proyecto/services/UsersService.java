package com.example.proyecto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proyecto.encrypt.EncryptionService;
import com.example.proyecto.models.UsersModel;
import com.example.proyecto.repositories.UsersRepository;

@Service
/**
 * La clase UsersService proporciona métodos para administrar los datos del usuario, incluido
 * el cifrado y descifrado de la información de los usuarios.
 */
public class UsersService {

    /*@Autowired permite inyectar unas dependencias con otras dentro de Spring*/
    @Autowired UsersRepository uRepository;
    EncryptionService eService = new EncryptionService();

    /**
     * El método getAll devuelve una lista de objetos UsersModel llamando 
     * al método findAll del uRepository.
     * 
     * @return Se devuelve una lista de objetos UsersModel.
     */
    public List<UsersModel> getAll(){
        return this.uRepository.findAll();
    }

    /**
     * El método saveUser verifica si ya existe un usuario con el mismo nombre de usuario o
     * correo electrónico, descifra a los usuarios existentes, los vuelve a cifrar y luego guarda al
     * nuevo usuario si no se encuentran duplicados.
     * 
     * @param uModel Objeto de UsersModel el cual representa los datos del usuario que deben guardarse en la base de datos.
     * @return Se devuelve un objeto de UsersModel, específicamente el modelo de
     * usuario que se guardó en el repositorio. Si ya existe un usuario con el mismo nombre de usuario
     * o correo electrónico, devuelve "nulo".
     */
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

    /**
     * El método validateUser comprueba si un modelo de usuario coincide con el nombre de usuario,
     * el correo electrónico y la contraseña de los usuarios existentes después de descifrarlos y
     * cifrarlos.
     * 
     * @param uModel Representa el modelo de usuario que debe validarse.
     * @return Si se encuentra una coincidencia, el método devuelve el
     * objeto de usuario, en caso contrario devuelve "nulo".
     */
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

    /**
     * El método encryptUser cifra el nombre de usuario, el correo electrónico y la contraseña de un
     * modelo de usuario.
     * 
     * @param uModel El modelo de usuario a encriptar.
     */
    public void encryptUser(UsersModel uModel){
        uModel.setUserName(eService.encrypt(uModel.getUserName()));
        uModel.setUserEmail(eService.encrypt(uModel.getUserEmail()));
        uModel.setUserPassword(eService.encrypt(uModel.getUserPassword()));
    }

    /**
     * El método decryptUser descifra el nombre de usuario, el correo electrónico y la contraseña de
     * un modelo de usuario.
     * 
     * @param u El modelo de usuario a descifrar.
     */
    public void decryptUser(UsersModel u){
        u.setUserName(eService.decrypt(u.getUserName()));
        u.setUserEmail(eService.decrypt(u.getUserEmail()));
        u.setUserPassword(eService.decrypt(u.getUserPassword()));
    }

}
