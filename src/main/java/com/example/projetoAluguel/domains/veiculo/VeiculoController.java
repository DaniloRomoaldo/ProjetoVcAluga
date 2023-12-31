package com.example.projetoAluguel.domains.veiculo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/veiculo", produces = MediaType.APPLICATION_JSON_VALUE)
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    @ResponseBody
    public VeiculoDTO criar(@RequestBody VeiculoDTO veiculoDTO) throws JsonProcessingException {
        return veiculoService.criar(veiculoDTO);
    }

    @PutMapping("/{veiculoPlaca}")
    @ResponseBody
    public VeiculoDTO atualizar(@PathVariable("veiculoPlaca")String veiculoPlaca,
            @RequestBody VeiculoDTO veiculoDTO){
        return veiculoService.atualizar(veiculoDTO, veiculoPlaca);
    }


    @DeleteMapping("/{veiculoId}")
    @ResponseBody
    public String deletar(@PathVariable("veiculoId") UUID veiculoId){
        return veiculoService.delete(veiculoId);
    }

//    @GetMapping
//    @ResponseBody
//    public List<VeiculoDTO> getALL(){
//        return veiculoService.getALL();
//    }

    @GetMapping // testando multiplos paramentos na mesma rota de requisição
    @ResponseBody
    public List<VeiculoDTO> getVeiculos(
            @RequestParam(value = "placa", required = false) String placa ,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "categoria", required = false)String categoria,
            @RequestParam(value = "veiculoId", required = false)UUID veiculoId,
            @RequestParam(value = "veiculoDisponivel", required = false)String veiculoDisponivel,
            @RequestParam(value = "all", required = false)String all){

        if (placa != null){
            return Collections.singletonList(veiculoService.getVeiculoPlaca(placa));
        } else if (status != null) {
            return veiculoService.getVeiculoStatus(status);
        } else if (categoria != null) {
            return veiculoService.getVeiculoCategoria(categoria);
        } else if (veiculoId != null) {
            return Collections.singletonList(veiculoService.getVeiculoId(veiculoId));
        } else if (veiculoDisponivel != null) {
            return veiculoService.getVeiculosDisponiveis(veiculoDisponivel);
        } else if (all != null) {
            return veiculoService.getALL();
        } else {
            return veiculoService.getALL();
        }

    }


}

