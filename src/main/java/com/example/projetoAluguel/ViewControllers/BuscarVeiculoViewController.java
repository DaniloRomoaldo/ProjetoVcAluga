package com.example.projetoAluguel.ViewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buscarVeiculo")
public class BuscarVeiculoViewController {

    @GetMapping
    public String buscarVeiculo(){return "buscarVeiculo";}
}
