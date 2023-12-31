package com.example.projetoAluguel.domains.motorista;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/motorista", produces = MediaType.APPLICATION_JSON_VALUE)
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;

    @PostMapping
    @ResponseBody
    public MotoristaDTO criar(@RequestBody MotoristaDTO motoristaDTO) throws JsonProcessingException { //preciso aprender como coletar a data no formato correto para a requisição http

        return motoristaService.criar(motoristaDTO);
    }

    @PutMapping({"/{cnh}"})
    @ResponseBody
    public MotoristaDTO atualizar(@PathVariable("cnh") String cnh,
                                  @RequestBody MotoristaDTO motoristaDTO){
        return motoristaService.atualizar(motoristaDTO, cnh);
    }



    @DeleteMapping("/{motoristaId}")
    @ResponseBody
    public String deletar(@PathVariable("motoristaId") UUID motoristaId){

        return motoristaService.delete(motoristaId);
    }

    @GetMapping
    @ResponseBody
    public List<MotoristaDTO> getMotorista(@RequestParam(value = "cnh", required = false)String cnh,
                                           @RequestParam(value = "nome", required = false)String nome,
                                           @RequestParam(value = "all", required = false)String all,
                                           @RequestParam(value = "status", required = false)String status){

        if (cnh != null){
            return Collections.singletonList(motoristaService.getCnh(cnh));
        } else if (all != null) {
            return motoristaService.getALL();
        } else if (status != null) {
            return motoristaService.getMotoristaStatus(status);
        } else {
            return motoristaService.getMotorista(nome);
        }

    }

//    @GetMapping
//    @ResponseBody
//    public List<MotoristaDTO> getALL(){
//
//        return motoristaService.getALL();
//    }

}
