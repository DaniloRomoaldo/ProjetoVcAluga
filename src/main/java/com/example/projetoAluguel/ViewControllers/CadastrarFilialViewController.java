package com.example.projetoAluguel.ViewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastrarFilial")
public class CadastrarFilialViewController {

    @GetMapping
    public String cadastroFilial(){
        return "cadastroFilial";
    }
}
