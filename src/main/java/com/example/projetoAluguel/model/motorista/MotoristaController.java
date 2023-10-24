package com.example.projetoAluguel.model.motorista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/motorista", produces = MediaType.APPLICATION_JSON_VALUE)
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;

    @PostMapping
    @ResponseBody
    public MotoristaDTO criar(@RequestBody MotoristaDTO motoristaDTO){ //preciso aprender como coletar a data no formato correto para a requisição http

        return motoristaService.criar(motoristaDTO);
    }

    @PutMapping({"/{motoristaId}"})
    @ResponseBody
    public MotoristaDTO atualizar(@PathVariable("motoristaId") UUID motoristaId,
                                  @RequestBody MotoristaDTO motoristaDTO){
        return motoristaService.atualizar(motoristaDTO, motoristaId);
    }

    @GetMapping
    @ResponseBody
    public List<MotoristaDTO> getALL(){

        return motoristaService.getALL();
    }

    @DeleteMapping("/{motoristaId}")
    @ResponseBody
    public String deletar(@PathVariable("motoristaId") UUID motoristaId){

        return motoristaService.delete(motoristaId);
    }

}
