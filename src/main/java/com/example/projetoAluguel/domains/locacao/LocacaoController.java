package com.example.projetoAluguel.domains.locacao;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping(value = "/locacoes", produces = MediaType.APPLICATION_JSON_VALUE)
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



    @DeleteMapping("/{codLocacao}")
    @ResponseBody
    public String deletar(@PathVariable("codLocacao") int codLocacao){
        return locacaoService.delete(codLocacao);
    }



    @GetMapping
    @ResponseBody
    public List<LocacaoDTO> getLocacoes(@RequestParam(value = "cliente", required = false)String nomeCliente,
                                        @RequestParam(value = "registroCliente", required = false)String registroCliente, // registro é um sinonimo para cpf e cnpj
                                        @RequestParam(value = "codLocacao", required = false) String codLocacao,
                                        @RequestParam(value = "status", required = false)String status){

        if (nomeCliente != null){
            return locacaoService.getLocacaoCliente("nome", nomeCliente);
        } else if (registroCliente != null) {
            return locacaoService.getLocacaoCliente("cpfCnpj", registroCliente);
        } else if (codLocacao != null) {
            return Collections.singletonList(locacaoService.getCodLocacao(Integer.parseInt(codLocacao)));
        }else {
           return locacaoService.getStatus(status);
        }
    }


//    @GetMapping
//    @ResponseBody
//    public List<LocacaoDTO> getALL(){
//        return locacaoService.getALL();
//    }

}
