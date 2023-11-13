package com.example.projetoAluguel.ViewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/locacao")
public class LocacaoViewController {

    @GetMapping
    public String locacao(){
        return "locacao";
    }
}
