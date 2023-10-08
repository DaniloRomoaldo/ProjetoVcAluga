package com.example.projetoAluguel.model.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public ClienteDTO criar(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf_cnpj(clienteDTO.getCpf_cnpj());
        cliente.setTipo(clienteDTO.getTipo());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setDt_cadastro(clienteDTO.getDt_cadastro());
        cliente.setTotal_fidelidade(clienteDTO.getTotal_fidelidade());
        repository.save(cliente);
        clienteDTO.setId(cliente.getId());
        return clienteDTO;
    }

    public ClienteDTO atualizar(ClienteDTO clienteDTO, UUID clientId){
        Cliente clienteDatabase = repository.getReferenceById(clientId);
        clienteDatabase.setNome(clienteDTO.getNome());
        clienteDatabase.setCpf_cnpj(clienteDTO.getCpf_cnpj());
        clienteDatabase.setTipo(clienteDTO.getTipo());
        clienteDatabase.setTelefone(clienteDTO.getTelefone());
//        clienteDatabase.setDt_cadastro(clienteDTO.getDt_cadastro()); // a data de cadastro não é atualizada por enquanto
        clienteDatabase.setTotal_fidelidade(clienteDTO.getTotal_fidelidade());
        repository.save(clienteDatabase);
        return clienteDTO;
    }

    private ClienteDTO converter(Cliente cliente){
        ClienteDTO result = new ClienteDTO();
        result.setId(cliente.getId());
        result.setNome(cliente.getNome());
        result.setCpf_cnpj(cliente.getCpf_cnpj());
        result.setTipo(cliente.getTipo());
        result.setTelefone(cliente.getTelefone());
        result.setDt_cadastro(cliente.getDt_cadastro());
        result.setTotal_fidelidade(cliente.getTotal_fidelidade());
        return result;
    }

    public List<ClienteDTO> getALL(){
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public String delete(UUID clientId){
        repository.deleteById(clientId);
        return "CLIENTE DELETADO!";
    }
}
