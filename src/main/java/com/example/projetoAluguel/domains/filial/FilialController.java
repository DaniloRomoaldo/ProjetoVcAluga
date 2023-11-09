package com.example.projetoAluguel.domains.filial;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/filial", produces = MediaType.APPLICATION_JSON_VALUE) // mapeia a roda por onde vai entrar os metodos HTTP = localhost:8080/filial
public class FilialController {

    @Autowired
    private FilialService filialService; // Cria uma variável do tipo FilialService que é minha camada de comunicação entre a entidade e o banco de dados através do objeto

    @PostMapping // configuração dos métodos HTTP: Método Post
    @ResponseBody // Indica que o retorno do método (criar) deve ser um corpo de solicitação HTTP (json no caso)
    public FilialDTO criar(@RequestBody FilialDTO filialDTO) throws JsonProcessingException { //@RequestBody indica que o spring boot deve converter o json recebido em um objeto filialDTO
        return  filialService.criar(filialDTO);
    }

    @PutMapping("/{nomeFilial}")
    @ResponseBody
    public FilialDTO atualizar(@PathVariable("nomeFilial") String nomeFilial,
                               @RequestBody FilialDTO filialDTO){
        return filialService.atualizar(filialDTO, nomeFilial);
    }

    @GetMapping
    @ResponseBody
    public List<FilialDTO> getALL(){
        return filialService.getALL();
    }

    @DeleteMapping("/{filialId}")
    @ResponseBody
    public String deletar(@PathVariable("filialId") UUID filialId){
        return filialService.delete(filialId);
    }


}
