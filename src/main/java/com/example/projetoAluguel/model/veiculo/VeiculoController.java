package com.example.projetoAluguel.model.veiculo;

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
    public VeiculoDTO criar(@RequestBody VeiculoDTO veiculoDTO){
        veiculoDTO.setFilialDTO(veiculoDTO.getFilialDTO());
        return veiculoService.criar(veiculoDTO, veiculoDTO.getFilialDTO().getNome());
    }

    @PutMapping("/{veiculoId}")
    @ResponseBody
    public VeiculoDTO atualizar(@PathVariable("veiculoId") UUID veiculoId,
                                @RequestBody VeiculoDTO veiculoDTO){
        return veiculoService.atualizar(veiculoDTO, veiculoId);
    }

    @GetMapping
    @ResponseBody
    public List<VeiculoDTO> getALL(){
        return veiculoService.getALL();
    }

    @DeleteMapping("/{veiculoId}")
    @ResponseBody
    public String deletar(@PathVariable("veiculoId")UUID veiculoId){
        return veiculoService.delete(veiculoId);
    }

}
