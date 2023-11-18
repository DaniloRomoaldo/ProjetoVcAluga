package com.example.projetoAluguel.domains.cliente;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {
    @Autowired
    private  ClienteService clienteService;

    @PostMapping
    @ResponseBody
    public ClienteDTO criar(@RequestBody ClienteDTO clienteDTO) throws JsonProcessingException {

        return clienteService.criar(clienteDTO);
    }

    @PutMapping("/{cpfCnpj}")
    @ResponseBody
    public ClienteDTO atualizar(@PathVariable("cpfCnpj") String cpfCnpj,
                                @RequestBody ClienteDTO clienteDTO){
        return clienteService.atualizar(clienteDTO, cpfCnpj);
    }



    @DeleteMapping("/{clienteId}")
    @ResponseBody
    public String deletar(@PathVariable("clienteId") UUID clienteId){

        return clienteService.delete(clienteId);
    }


    @GetMapping
    @ResponseBody
    public List<ClienteDTO> getVeiculos(
            @RequestParam(value = "registro", required = false)String registro,
            @RequestParam(value = "nome", required = false)String nome,
            @RequestParam(value = "tipo", required = false)String tipo){

        if (registro != null){
            return Collections.singletonList(clienteService.getRegistro(registro));
        } else if (tipo != null) {
            return clienteService.getTipo(tipo);
        } else {
            return clienteService.getNome(nome);
        }



    }



//    @GetMapping
//    @ResponseBody
//    public List<ClienteDTO> getALL(){
//
//        return clienteService.getALL();
//    }

}
