package com.example.projetoAluguel.domains.cliente;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public ClienteDTO criar(ClienteDTO clienteDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String clienteDTOJson = objectMapper.writeValueAsString(clienteDTO);
        Cliente cliente = objectMapper.readValue(clienteDTOJson, Cliente.class);

        repository.save(cliente);
        clienteDTO.setId(cliente.getId());
        return clienteDTO;

//        cliente.setNome(clienteDTO.getNome());
//        cliente.setCpfCnpj(clienteDTO.getCpf_cnpj());
//        cliente.setTipo(clienteDTO.getTipo());
//        cliente.setTelefone(clienteDTO.getTelefone());
//        cliente.setDt_cadastro(clienteDTO.getDt_cadastro());
//        cliente.setTotal_fidelidade(clienteDTO.getTotal_fidelidade());
    }

    public ClienteDTO atualizar(ClienteDTO clienteDTO, String cpfCnpj){
        Cliente clienteDatabase = repository.findByCpfCnpj(cpfCnpj);

        if (clienteDatabase != null){

            if ((!Objects.equals(clienteDatabase.getNome(), clienteDTO.getNome())) && (clienteDTO.getNome() != null)){
                clienteDatabase.setNome(clienteDTO.getNome());
            }

            if ((!Objects.equals(clienteDatabase.getCpfCnpj(), clienteDTO.getCpfCnpj())) && (clienteDTO.getCpfCnpj() != null)){
                clienteDatabase.setCpfCnpj(clienteDTO.getCpfCnpj());
            }

            if ((!Objects.equals(clienteDatabase.getTipo(), clienteDTO.getTipo())) && (clienteDTO.getTipo() != null)){
                clienteDatabase.setTipo(clienteDTO.getTipo());
            }

            if ((!Objects.equals(clienteDatabase.getTelefone(), clienteDTO.getTelefone())) && (clienteDTO.getTelefone() != null)){
                clienteDatabase.setTelefone(clienteDTO.getTelefone());
            }

            if ((clienteDatabase.getTotal_fidelidade() != clienteDTO.getTotal_fidelidade()) && (clienteDTO.getTotal_fidelidade() != 0)){
                clienteDatabase.setTotal_fidelidade(clienteDTO.getTotal_fidelidade());
            }

            repository.save(clienteDatabase);
        }


        return clienteDTO;
    }

    private ClienteDTO converter(Cliente cliente){
        ClienteDTO result = new ClienteDTO();
        result.setId(cliente.getId());
        result.setNome(cliente.getNome());
        result.setCpfCnpj(cliente.getCpfCnpj());
        result.setTipo(cliente.getTipo());
        result.setTelefone(cliente.getTelefone());
        result.setDt_cadastro(cliente.getDt_cadastro());
        result.setTotal_fidelidade(cliente.getTotal_fidelidade());
        return result;
    }



    public String delete(UUID clientId){
        repository.deleteById(clientId);
        return "CLIENTE DELETADO!";
    }


    public List<ClienteDTO> getALL(){
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public ClienteDTO getRegistro(String cpfCnpj){
        return converter(repository.findByCpfCnpj(cpfCnpj));
    }

    public List<ClienteDTO> getTipo(String tipo){
        return repository
                .findByTipoContains(tipo)
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public List<ClienteDTO> getNome(String nome){
        return repository
                .findByNomeContains(nome)
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }
}
