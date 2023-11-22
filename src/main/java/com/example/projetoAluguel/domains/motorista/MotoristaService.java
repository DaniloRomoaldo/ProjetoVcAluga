package com.example.projetoAluguel.domains.motorista;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MotoristaService {
    @Autowired
    private MotoristaRepository repository;

    public MotoristaDTO criar(MotoristaDTO motoristaDTO) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String motoristaDTOJson = objectMapper.writeValueAsString(motoristaDTO);
       Motorista motorista = objectMapper.readValue(motoristaDTOJson, Motorista.class);

        repository.save(motorista);
        motoristaDTO.setId(motorista.getId());
        return motoristaDTO;

//        Motorista motorista = new Motorista();
//        motorista.setNome(motoristaDTO.getNome());
//        motorista.setCpf(motoristaDTO.getCpf());
//        motorista.setCnh(motoristaDTO.getCnh());
//        motorista.setDt_nascimento(motoristaDTO.getDt_nascimento());
//        motorista.setStatus(motoristaDTO.getStatus());
    }

    public MotoristaDTO atualizar(MotoristaDTO motoristaDTO, String cnh){
        Optional<Motorista> motorista = repository.findByCnh(cnh);

            motorista.ifPresent(motoristaDatabase -> {

                if ((!Objects.equals(motoristaDatabase.getNome(), motoristaDTO.getNome())) && (motoristaDTO.getNome() != null)) {
                    motoristaDatabase.setNome(motoristaDTO.getNome());

                }
                if ((!Objects.equals(motoristaDatabase.getCnh(), motoristaDTO.getCnh())) && (motoristaDTO.getCnh() != null)) {
                    motoristaDatabase.setCnh(motoristaDTO.getCnh());

                }
                if ((!Objects.equals(motoristaDatabase.getCpf(), motoristaDTO.getCpf())) && (motoristaDTO.getCpf() != null)) {
                    motoristaDatabase.setCpf(motoristaDTO.getCpf());

                }
                if ((motoristaDatabase.getDt_nascimento() != motoristaDTO.getDt_nascimento()) && (motoristaDTO.getDt_nascimento() != null)) {
                    motoristaDatabase.setDt_nascimento(motoristaDTO.getDt_nascimento());

                }
                if ((!Objects.equals(motoristaDatabase.getStatus(), motoristaDTO.getStatus())) && (motoristaDTO.getStatus() != null)) {
                    motoristaDatabase.setStatus(motoristaDTO.getStatus());
                }

                repository.save(motoristaDatabase);
            });

        return motoristaDTO;
    }

    private MotoristaDTO converter(Motorista motorista){
        MotoristaDTO result = new MotoristaDTO();
        result.setId(motorista.getId());
        result.setNome((motorista.getNome()));
        result.setCpf(motorista.getCpf());
        result.setCnh(motorista.getCnh());
        result.setDt_nascimentoOffsetDateTime(motorista.getDt_nascimento());
        result.setStatus(motorista.getStatus());
        return result;
    }

    private MotoristaDTO converterOptional(Optional<Motorista> motorista){
        MotoristaDTO result = new MotoristaDTO();

        motorista.ifPresent(converter ->{
            result.setId(converter.getId());
            result.setNome((converter.getNome()));
            result.setCpf(converter.getCpf());
            result.setCnh(converter.getCnh());
            result.setDt_nascimentoOffsetDateTime(converter.getDt_nascimento());
            result.setStatus(converter.getStatus());
        });

        return result;
    }


    public String delete(UUID motoristaId){
        repository.deleteById(motoristaId);
        return "Motorista Deletado!";
    }


    public MotoristaDTO getCnh(String cnh){
        return converterOptional(repository.findByCnh(cnh));
    }

    public List<MotoristaDTO> getMotorista(String nome){
        return repository
                .findByNomeContains(nome)
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }


    public List<MotoristaDTO> getALL(){
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

}
