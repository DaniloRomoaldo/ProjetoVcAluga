package com.example.projetoAluguel.model.veiculo;

import com.example.projetoAluguel.model.filial.Filial;
import com.example.projetoAluguel.model.filial.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository repository;

    @Autowired
    private FilialRepository repositoryFilial;

    public VeiculoDTO criar(VeiculoDTO veiculoDTO , String nomeFilial){
        Veiculo veiculo = new Veiculo();
        Filial filial = repositoryFilial.findByNome(nomeFilial);
        veiculo.setFilial(filial);
        veiculo.setCategoria(veiculoDTO.getCategoria());
        veiculo.setStatus(veiculoDTO.getStatus());
        veiculo.setKm_total(veiculoDTO.getKm_total());
        repository.save(veiculo);
        return veiculoDTO;
    }

    public VeiculoDTO atualizar(VeiculoDTO veiculoDTO, UUID veiculoId){
        Veiculo veiculoDatabase = repository.getReferenceById(veiculoId);
        veiculoDatabase.setStatus(veiculoDTO.getStatus());
        veiculoDatabase.setKm_total(veiculoDTO.getKm_total());
        veiculoDatabase.setFilial(repositoryFilial.findByNome(veiculoDTO.getFilialDTO().getNome()));
        veiculoDatabase.setCategoria(veiculoDTO.getCategoria());
        repository.save(veiculoDatabase);
        return veiculoDTO;
    }

    public VeiculoDTO converter(Veiculo veiculo){
        VeiculoDTO result = new VeiculoDTO();
        result.setId(veiculo.getId());
        result.getFilialDTO().setNome(veiculo.getFilial().getNome());
        result.setStatus(veiculo.getStatus());
        result.setCategoria(veiculo.getCategoria());
        result.setKm_total(veiculo.getKm_total());
        return result;
    }

    public List<VeiculoDTO> getALL(){
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public String delete(UUID veiculoId){
        repository.deleteById(veiculoId);
        return "Veiculo DELETADO";
    }

}
