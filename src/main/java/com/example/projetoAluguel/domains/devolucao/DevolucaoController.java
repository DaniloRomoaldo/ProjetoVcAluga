package com.example.projetoAluguel.domains.devolucao;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/devolucao", produces = MediaType.APPLICATION_JSON_VALUE)
public class DevolucaoController {

    @Autowired
    private DevolucaoService devolucaoService;

    @PostMapping
    @ResponseBody
    public  DevolucaoDTO criar(@RequestBody DevolucaoDTO devolucaoDTO) throws JsonProcessingException {
        return devolucaoService.criar(devolucaoDTO);
    }

    @PutMapping("/{codLocacao}")
    @ResponseBody
    public DevolucaoDTO atualizar(@PathVariable("codLocacao")int codLocacao,
                                  @RequestBody DevolucaoDTO devolucaoDTO){
        return devolucaoService.atualizar(devolucaoDTO, codLocacao);
    }

    @GetMapping
    @ResponseBody
    public List<DevolucaoDTO> getALL(){
        return devolucaoService.getALL();
    }

    @DeleteMapping("/{codLocacao}")
    @ResponseBody
    public String deletar(@PathVariable("codLocacao") int codLocacao){
        return devolucaoService.delete(codLocacao);
    }

}
