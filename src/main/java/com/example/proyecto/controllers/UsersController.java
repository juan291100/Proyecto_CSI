package com.example.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.proyecto.models.UsersModel;
import com.example.proyecto.services.UsersService;

/**
 * La clase UsersController maneja el inicio de sesión, el registro y 
 * la validación del usuario mediante anotaciones de Spring.
 */
@Controller
public class UsersController {

    /*@Autowired permite inyectar unas dependencias con otras dentro de Spring*/
    @Autowired UsersService uService;


    /**
     * El método login maneja solicitudes GET al endpoint "/login".
     * 
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return El método devuelve el template login.
     */
    @GetMapping("/login")
    public String login(Model model){
        UsersModel uModel = new UsersModel();
        model.addAttribute("user", uModel);
        return "home/login";
    }

    /**
     * El método validateUser valida las credenciales del usuario y
     * redirige a una página diferente si tiene éxito.
     * 
     * @param uModel El parámetro uModel es un objeto de tipo UsersModel que se utiliza para
     * representar datos del usuario, los cuales recibe de un formulario.
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return Si el usuario se valida correctamente, el método devolverá una redirección a
     * "/europeanLeagues". Si la validación del usuario falla, se agregará un mensaje de error al
     * modelo y el método devolverá "/login".
     */
    @PostMapping("/login")
    public String validateUser(@ModelAttribute("user") UsersModel uModel, Model model){
        uModel.setUserEmail(uModel.getUserName());
        if(uService.validateUser(uModel) == null){
            model.addAttribute("error", "The username, email or password is incorrect.");
            return "home/login";
        }else{
            return "redirect:/europeanLeagues";
        }
    }

    /**
     * El método register maneja solicitudes GET al endpoint "/register".
     * 
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return El método devuelve el template register.
     */
    @GetMapping("/register")
    public String register(Model model){
        UsersModel uModel = new UsersModel();
        model.addAttribute("user", uModel);
        return "home/register";
    }

    /**
     * El método addUser registra a un nuevo usuario y lo redirige a la página de
     * inicio de sesión si tiene éxito, mostrando un mensaje de error si el nombre de usuario o el
     * correo electrónico ya están en uso.
     * 
     * @param uModel Se utiliza para capturar los datos del usuario enviados durante el proceso de registro. 
     * @param model El parámetro model es un objeto de la clase Model. 
     * Se utiliza para pasar datos a la plantilla HTML.
     * @return Si el método uService.saveUser(uModel) devuelve null, el método devolverá el template
     * register con un mensaje de error. Si el método uService.saveUser(uModel) devuelve un valor no nulo, 
     * el método redirigirá al template login.
     */
    @PostMapping("/register")
    public String addUser(@ModelAttribute("user") UsersModel uModel, Model model){
        if(uService.saveUser(uModel) == null){
            model.addAttribute("error", "The username or email is already in use.");
            return "home/register";
        }else{
            return "redirect:/login";
        }
    }

}
