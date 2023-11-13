package com.example.projetoAluguel.ViewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastrarCliente")
public class CadastroClienteViewController {

    @GetMapping
    public String cadastrarCliente(){return "cadastroCliente";}
}
