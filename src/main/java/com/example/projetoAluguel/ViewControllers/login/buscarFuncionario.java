package com.example.projetoAluguel.ViewControllers.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buscarFuncionario")
public class buscarFuncionario {

    @GetMapping
    public String buscarFuncionario(){
        return "buscarFuncionario";
    }
}
