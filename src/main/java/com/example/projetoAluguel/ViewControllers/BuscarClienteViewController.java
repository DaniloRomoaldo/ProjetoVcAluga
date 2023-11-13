package com.example.projetoAluguel.ViewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buscarCliente")
public class BuscarClienteViewController {

    @GetMapping
    public String bucarCliente(){
        return "buscarCliente";
    }
}
