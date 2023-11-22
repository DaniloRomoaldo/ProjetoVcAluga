package com.example.projetoAluguel.domains.cliente;

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
        Optional<Cliente> clienteDatabase = repository.findByCpfCnpj(cpfCnpj);

        if (clienteDatabase != null){

            clienteDatabase.ifPresent(database -> {
                        if (!Objects.equals(database.getNome(), clienteDTO.getNome()) && clienteDTO.getNome() != null) {
                            database.setNome(clienteDTO.getNome());
                        }

                        if ((!Objects.equals(database.getCpfCnpj(), clienteDTO.getCpfCnpj())) && (clienteDTO.getCpfCnpj() != null)) {
                            database.setCpfCnpj(clienteDTO.getCpfCnpj());
                        }

                        if ((!Objects.equals(database.getTipo(), clienteDTO.getTipo())) && (clienteDTO.getTipo() != null)) {
                            database.setTipo(clienteDTO.getTipo());
                        }

                        if ((!Objects.equals(database.getTelefone(), clienteDTO.getTelefone())) && (clienteDTO.getTelefone() != null)) {
                            database.setTelefone(clienteDTO.getTelefone());
                        }

                        if ((database.getTotal_fidelidade() != clienteDTO.getTotal_fidelidade()) && (clienteDTO.getTotal_fidelidade() != 0)) {
                            database.setTotal_fidelidade(clienteDTO.getTotal_fidelidade());
                        }

                repository.save(database);
            });

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

    private ClienteDTO converterOptional(Optional<Cliente> cliente){
        ClienteDTO result = new ClienteDTO();

        cliente.ifPresent( converter -> {
            result.setId(converter.getId());
            result.setNome(converter.getNome());
            result.setCpfCnpj(converter.getCpfCnpj());
            result.setTipo(converter.getTipo());
            result.setTelefone(converter.getTelefone());
            result.setDt_cadastro(converter.getDt_cadastro());
            result.setTotal_fidelidade(converter.getTotal_fidelidade());
        });
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
        return converterOptional(repository.findByCpfCnpj(cpfCnpj));
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
