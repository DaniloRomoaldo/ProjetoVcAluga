package com.example.projetoAluguel.ViewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastrarFuncionario")
public class CadastroFuncionarioViewController {

    @GetMapping
    public String cadastroFuncionario(){
        return "cadastroFuncionario";
    }
}
