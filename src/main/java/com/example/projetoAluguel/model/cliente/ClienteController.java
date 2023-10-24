package com.example.projetoAluguel.model.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {
    @Autowired
    private  ClienteService clienteService;

    @PostMapping
    @ResponseBody
    public ClienteDTO criar(@RequestBody ClienteDTO clienteDTO){

        return clienteService.criar(clienteDTO);
    }

    @PutMapping("/{clienteId}")
    @ResponseBody
    public ClienteDTO atualizar(@PathVariable("clienteId") UUID clienteId,
                                @RequestBody ClienteDTO clienteDTO){
        return clienteService.atualizar(clienteDTO, clienteId);
    }

    @GetMapping
    @ResponseBody
    public List<ClienteDTO> getALL(){

        return clienteService.getALL();
    }

    @DeleteMapping("/{clienteId}")
    @ResponseBody
    public String deletar(@PathVariable("clienteId") UUID clienteId){

        return clienteService.delete(clienteId);
    }
}
