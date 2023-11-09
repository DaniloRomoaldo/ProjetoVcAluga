package com.example.projetoAluguel.domains.veiculo;

import com.example.projetoAluguel.domains.filial.Filial;
import com.example.projetoAluguel.domains.filial.FilialRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository repository;

    @Autowired
    private FilialRepository repositoryFilial;

    public VeiculoDTO criar(VeiculoDTO veiculoDTO) throws JsonProcessingException {
        Filial filial = repositoryFilial.findByNome(veiculoDTO.getFilialDTO().getNome());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //ignora o erro de mapeamento do objeto e mantém
        objectMapper.registerModule(new JavaTimeModule());
        String veiculoDTOJson = objectMapper.writeValueAsString(veiculoDTO);
        Veiculo veiculo = objectMapper.readValue(veiculoDTOJson, Veiculo.class);
        veiculo.setFilial(filial);
        repository.save(veiculo);
        return veiculoDTO;

//        veiculo.setCategoria(veiculoDTO.getCategoria());
//        veiculo.setStatus(veiculoDTO.getStatus());
//        veiculo.setKm_total(veiculoDTO.getKm_total());
//        veiculo.setPlaca(veiculoDTO.getPlaca());
//        veiculo.setNome(veiculoDTO.getNome());
    }

    public VeiculoDTO atualizar(VeiculoDTO veiculoDTO){
        Veiculo veiculoDatabase = repository.findByPlaca(veiculoDTO.getPlaca());

        if (veiculoDatabase != null){

            if((!Objects.equals(veiculoDatabase.getStatus(), veiculoDTO.getStatus()) && (veiculoDTO.getStatus() != null))){
                veiculoDatabase.setStatus(veiculoDTO.getStatus());

            }
            if((veiculoDatabase.getKm_total() != veiculoDTO.getKm_total()) && (veiculoDTO.getKm_total() != 0)){
                veiculoDatabase.setKm_total(veiculoDTO.getKm_total());

            }
            if ((veiculoDatabase.getFilial() != repositoryFilial.findByNome(veiculoDTO.getFilialDTO().getNome())) && (veiculoDTO.getFilialDTO().getNome() != null)){
                veiculoDatabase.setFilial(repositoryFilial.findByNome(veiculoDTO.getFilialDTO().getNome()));

            }
            if ((!Objects.equals(veiculoDatabase.getCategoria(), veiculoDTO.getCategoria())) && (veiculoDTO.getCategoria() != null)){
                veiculoDatabase.setCategoria(veiculoDTO.getCategoria());

            }
            if ((!Objects.equals(veiculoDatabase.getPlaca(), veiculoDTO.getPlaca())) && (veiculoDTO.getPlaca() != null)){
                veiculoDatabase.setPlaca(veiculoDTO.getPlaca());

            }
            repository.save(veiculoDatabase);

        }

        return veiculoDTO;
    }

    private VeiculoDTO converter(Veiculo veiculo){
        VeiculoDTO result = new VeiculoDTO();
        result.setId(veiculo.getId());
        result.getFilialDTO().setNome(veiculo.getFilial().getNome());
        result.setStatus(veiculo.getStatus());
        result.setCategoria(veiculo.getCategoria());
        result.setKm_total(veiculo.getKm_total());
        result.setPlaca(veiculo.getPlaca());
        result.setNome(veiculo.getNome());
        return result;
    }

    public List<VeiculoDTO> getALL(){
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public String delete(String placa){
        repository.deleteById(repository.findByPlaca(placa).getId());
        return "Veiculo DELETADO";
    }

}
