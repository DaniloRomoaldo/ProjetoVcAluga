package com.example.projetoAluguel.model.ordem_manutencao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/manutencao", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManutencaoController {

    @Autowired
    private ManutencaoService manutencaoService;

    @PostMapping
    @ResponseBody
    public ManutencaoDTO criar(@RequestBody ManutencaoDTO manutencaoDTO){
        return manutencaoService.criar(manutencaoDTO);
    }

    @PutMapping("/{manutencaoId}")
    @ResponseBody
    public ManutencaoDTO atualizar(@PathVariable("manutencaoId")UUID manutencaoId,
                                   @RequestBody ManutencaoDTO manutencaoDTO){
        return manutencaoService.atualizar(manutencaoDTO, manutencaoId);
    }

    @GetMapping
    @ResponseBody
    public List<ManutencaoDTO> getALL(){
        return manutencaoService.getALL();
    }

    @DeleteMapping("/{manutencaoId}")
    @ResponseBody
    public String deletar(@PathVariable("manutencaoId") UUID manutencaoId){
        return manutencaoService.delete(manutencaoId);
    }

//   public ManutencaoController(ManutencaoService manutencaoService) {
//        this.manutencaoService = manutencaoService;
//    }
}
