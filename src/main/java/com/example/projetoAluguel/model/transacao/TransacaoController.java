package com.example.projetoAluguel.model.transacao;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    @ResponseBody
    public TransacaoDTO criar(@RequestBody TransacaoDTO transacaoDTO) throws JsonProcessingException {
        return transacaoService.criar(transacaoDTO);
    }

    @PutMapping("/{codTransacao}")
    @ResponseBody
    public TransacaoDTO atualizar(@PathVariable("codTransacao")String codTransacao,
                                  @RequestBody TransacaoDTO transacaoDTO){
        return transacaoService.atualizar(transacaoDTO, codTransacao);
    }

    @GetMapping
    @ResponseBody
    public List<TransacaoDTO> getALL(){
        return transacaoService.getALL();
    }

    @DeleteMapping("/{codTransacao}")
    @ResponseBody
    public String deletear(@PathVariable("codTransacao") String codTransacao){
        return transacaoService.delete(codTransacao);
    }

}
