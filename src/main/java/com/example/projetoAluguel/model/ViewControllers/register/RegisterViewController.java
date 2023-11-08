package com.example.projetoAluguel.model.ViewControllers.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterViewController {

    @GetMapping
    public String registerPage(){
        return "register";
    }
}
