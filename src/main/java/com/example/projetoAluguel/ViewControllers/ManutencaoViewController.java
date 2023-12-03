package com.example.projetoAluguel.ViewControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/listaManutencao")
public class ManutencaoViewController {
    @GetMapping
    public String locacoesAtivas(){
        return "manutencaoControle";
    }
}
