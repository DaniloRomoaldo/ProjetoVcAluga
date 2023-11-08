package com.example.projetoAluguel.model.ordem_manutencao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/manutencao", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManutencaoController {

    @Autowired
    private ManutencaoService manutencaoService;

    @PostMapping
    @ResponseBody
    public ManutencaoDTO criar(@RequestBody ManutencaoDTO manutencaoDTO){
        return manutencaoService.criar(manutencaoDTO); // para criar um json manutencao eu preciso da placa do veículo dentro do VeiculoDTO
    }

    @PutMapping("/{placaVeiculo}")
    @ResponseBody
    public ManutencaoDTO atualizar(@PathVariable("placaVeiculo")String placaVeiculo,
                                    @RequestBody ManutencaoDTO manutencaoDTO){
        return manutencaoService.atualizar(manutencaoDTO, placaVeiculo); // para criar o json eu preciso da placa do veículo dentro do VeiculoDTO
    }

    @GetMapping
    @ResponseBody
    public List<ManutencaoDTO> getALL(){
        return manutencaoService.getALL();
    }

    @DeleteMapping("/{placaVeiculo}")
    @ResponseBody
    public String deletar(@PathVariable("placaVeiculo") String placaVeiculo){
        return manutencaoService.delete(placaVeiculo);
    }

//   public ManutencaoController(ManutencaoService manutencaoService) {
//        this.manutencaoService = manutencaoService;
//    }
}
