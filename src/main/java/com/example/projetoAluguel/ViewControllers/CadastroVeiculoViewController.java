package com.example.projetoAluguel.ViewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastrarVeiculo")
public class CadastroVeiculoViewController {

    @GetMapping
    public String cadastroVeiculo(){
        return "cadastroVeiculo";
    }
}
