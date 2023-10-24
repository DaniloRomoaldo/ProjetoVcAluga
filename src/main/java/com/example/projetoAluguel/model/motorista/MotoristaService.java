package com.example.projetoAluguel.model.motorista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MotoristaService {
    @Autowired
    private MotoristaRepository repository;

    public MotoristaDTO criar(MotoristaDTO motoristaDTO){
        Motorista motorista = new Motorista();
        motorista.setNome(motoristaDTO.getNome());
        motorista.setCpf(motoristaDTO.getCpf());
        motorista.setCnh(motoristaDTO.getCnh());
        motorista.setDt_nascimento(motoristaDTO.getDt_nascimento());
        repository.save(motorista);
        motoristaDTO.setId(motorista.getId());
        return motoristaDTO;
    }

    public MotoristaDTO atualizar(MotoristaDTO motoristaDTO, UUID motoristaId){
        Motorista motoristaDatabase = repository.getReferenceById(motoristaId);
        motoristaDatabase.setNome(motoristaDTO.getNome());
        motoristaDatabase.setCpf(motoristaDTO.getCpf());
        motoristaDatabase.setCnh(motoristaDTO.getCnh());
        motoristaDatabase.setDt_nascimento(motoristaDTO.getDt_nascimento());
        repository.save(motoristaDatabase);
        return motoristaDTO;
    }

    private MotoristaDTO converter(Motorista motorista){
        MotoristaDTO result = new MotoristaDTO();
        result.setId(motorista.getId());
        result.setNome((motorista.getNome()));
        result.setCpf(motorista.getCpf());
        result.setCnh(motorista.getCnh());
        result.setDt_nascimentoOffsetDateTime(motorista.getDt_nascimento());
        return result;
    }

    public List<MotoristaDTO> getALL(){
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public String delete(UUID motoristaId){
        repository.deleteById(motoristaId);
        return "Motorista Deletado!";
    }

}
