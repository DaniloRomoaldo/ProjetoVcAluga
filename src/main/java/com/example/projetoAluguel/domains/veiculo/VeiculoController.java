package com.example.projetoAluguel.domains.veiculo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public VeiculoDTO atualizar(@RequestBody VeiculoDTO veiculoDTO){
        return veiculoService.atualizar(veiculoDTO);
    }

    @GetMapping
    @ResponseBody
    public List<VeiculoDTO> getALL(){
        return veiculoService.getALL();
    }

    @DeleteMapping("/{veiculoId}")
    @ResponseBody
    public String deletar(@PathVariable("veiculoId") UUID veiculoId){
        return veiculoService.delete(veiculoId);
    }

}
