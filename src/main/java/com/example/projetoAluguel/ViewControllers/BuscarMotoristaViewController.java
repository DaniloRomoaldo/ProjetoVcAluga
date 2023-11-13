package com.example.projetoAluguel.ViewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buscarMotorista")
public class BuscarMotoristaViewController {

    @GetMapping
    public String buscarMotorista(){return "buscarMotorista";}
}
