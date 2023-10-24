package com.example.projetoAluguel.model.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping(value = "/funcionario", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService; //Cria uma classe do tipo service que é a camada de comunicação entre os dados http e o banco de dados


    @PostMapping // Método HTTP: Post
    @ResponseBody // Indica que a resposta do método deve ser um corpo de solicitação
    public FuncionarioDTO criar(@RequestBody FuncionarioDTO funcionarioDTO){ // indica que o spring deve converter o json recebido em um objeto DTO
    //    funcionarioDTO.setFilialDTO(funcionarioDTO.getFilialDTO());
        return funcionarioService.criar(funcionarioDTO, funcionarioDTO.getFilialDTO().getNome()); //passar um objeto único com Filial dentro dele: Filial {nome}, tentar acessar FuncionarioDTO.Fi
    }

    @PutMapping("/{funcionarioId}") //metodo HTTP: PUT
    @ResponseBody
    public FuncionarioDTO atualizar(@PathVariable("funcionarioId")UUID funcionarioId,
                                    @RequestBody FuncionarioDTO funcionarioDTO){
        return funcionarioService.atualizar(funcionarioDTO, funcionarioId);
    }

    @GetMapping
    @ResponseBody
    public List<FuncionarioDTO> getALL(){
        return funcionarioService.getALL();
    }

    @DeleteMapping("/{funcionarioId}")
    @ResponseBody
    public String deletar(@PathVariable("funcionarioId") UUID funcionarioId){
        return funcionarioService.delete(funcionarioId);
    }

}
