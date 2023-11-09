package com.example.projetoAluguel.domains.log_veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historicoVeiculo")
public class LogVeiculoController {
    @Autowired
    private LogVeiculoService logVeiculoService;

    @PostMapping
    @ResponseBody
    public LogVeiculoDTO criar(@RequestBody LogVeiculoDTO logVeiculoDTO){
        return logVeiculoService.criar(logVeiculoDTO);
    }

    @PutMapping("/{placaVeiculo}")
    @ResponseBody
    public LogVeiculoDTO atualizar(@PathVariable("placaVeiculo")String placaVeiculo, // esse parametro é necessário para a rota fazer sentido, pode ser substitu
                                    @RequestBody LogVeiculoDTO logVeiculoDTO){
        return logVeiculoService.atualizar(logVeiculoDTO, placaVeiculo); // precisa passar a placa do veículo na construçao do json
    }

    @GetMapping
    @ResponseBody
    public List<LogVeiculoDTO> getALL(){
        return logVeiculoService.getALL();
    }

    @DeleteMapping("/{placaVeiculo}")
    @ResponseBody
    public String deletar(@PathVariable("placaVeiculo") String placaVeiculo){
        return logVeiculoService.delete(placaVeiculo);
    }

}
