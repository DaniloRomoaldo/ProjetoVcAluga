package com.example.projetoAluguel.model.locacao;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/locacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocacaoController {

    @Autowired
    private LocacaoService locacaoService;

    @PostMapping
    @ResponseBody
    public LocacaoDTO criar(@RequestBody LocacaoDTO locacaoDTO) throws JsonProcessingException {
        return locacaoService.criar(locacaoDTO);
    }

    @PutMapping("/{codLocacao}")
    @ResponseBody
    public LocacaoDTO atualizar(@PathVariable("codLocacao")int codLocacao,
                              @RequestBody LocacaoDTO locacaoDTO){
        return locacaoService.atualizar(locacaoDTO,codLocacao);
    }

    @GetMapping
    @ResponseBody
    public List<LocacaoDTO> getALL(){
        return locacaoService.getALL();
    }

    @DeleteMapping("/{codLocacao}")
    @ResponseBody
    public String deletar(@PathVariable("codLocacao") int codLocacao){
        return locacaoService.delete(codLocacao);
    }

}
