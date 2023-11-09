package com.example.projetoAluguel.domains.funcionario;

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
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private FilialRepository repositoryFilial;

    public FuncionarioDTO criar(FuncionarioDTO funcionarioDTO) throws JsonProcessingException {
        Filial filial = repositoryFilial.findByNome(funcionarioDTO.getFilialDTO().getNome());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.registerModule(new JavaTimeModule());
        String funcionarioDTOJson = objectMapper.writeValueAsString(funcionarioDTO);
        Funcionario funcionario = objectMapper.readValue(funcionarioDTOJson, Funcionario.class);

        funcionario.setFilial(filial);
        funcionario.setCodFuncionario(funcionarioDTO.getCod_funcionario()); // precisa reconfigurar campos que tenham valor numérico

        repository.save(funcionario);
        return funcionarioDTO;

//        funcionario.setNome(funcionarioDTO.getNome());
//        funcionario.setCpf(funcionarioDTO.getCpf());
//        funcionario.setFuncao(funcionarioDTO.getFuncao());
//        funcionario.setStatus(funcionarioDTO.getStatus());
    }

    public FuncionarioDTO atualizar(FuncionarioDTO funcionarioDTO, int codFuncionario){
        Funcionario funcionarioDatabase = repository.findByCodFuncionario(codFuncionario);//carrega do banco de dados o funcionário solicitado e armazena em uma entidade

        if (funcionarioDatabase != null){
            if ((!Objects.equals(funcionarioDatabase.getNome(), funcionarioDTO.getNome())) && (funcionarioDTO.getNome() != null)) {
                funcionarioDatabase.setNome(funcionarioDTO.getNome());
            }
            if ((!Objects.equals(funcionarioDatabase.getStatus(), funcionarioDTO.getStatus())) && (funcionarioDTO.getStatus() != null)){
                funcionarioDatabase.setStatus(funcionarioDTO.getStatus()); // altera as informações da entidade com as informações inseridas no objeto DTO

            }
            if ((funcionarioDatabase.getCodFuncionario() != funcionarioDTO.getCod_funcionario() ) && (funcionarioDTO.getCod_funcionario() != 0)){
                funcionarioDatabase.setCodFuncionario(funcionarioDTO.getCod_funcionario());

            }
            if ((!Objects.equals(funcionarioDatabase.getFuncao(), funcionarioDTO.getFuncao())) && (funcionarioDTO.getFuncao() != null)){
                funcionarioDatabase.setFuncao(funcionarioDTO.getFuncao());

            }
            if ((!Objects.equals(funcionarioDatabase.getCpf(), funcionarioDTO.getCpf())) && (funcionarioDTO.getCpf() != null)){
                funcionarioDatabase.setCpf(funcionarioDTO.getCpf());
            }
            if ((funcionarioDatabase.getFilial() != repositoryFilial.findByNome(funcionarioDTO.getFilialDTO().getNome()) && (funcionarioDTO.getFilialDTO().getNome() != null))){
                funcionarioDatabase.setFilial(repositoryFilial.findByNome(funcionarioDTO.getFilialDTO().getNome()));

            }

            repository.save(funcionarioDatabase); // salva a entidade após realizar as alterações

        }

        return funcionarioDTO;
    }

    private FuncionarioDTO converter(Funcionario funcionario){
        FuncionarioDTO result = new FuncionarioDTO();
        result.setId(funcionario.getId());
        result.getFilialDTO().setNome(funcionario.getFilial().getNome());  //primeiro trago meu objeto DTO para acessar os métodos dele e depois insiro o nome utilizando o método do objeto
        result.setNome(funcionario.getNome());
        result.setCpf(funcionario.getCpf());
        result.setFuncao(funcionario.getFuncao());
        result.setCod_funcionario(funcionario.getCodFuncionario());
        result.setStatus(funcionario.getStatus());
        return result;
    }

    public List<FuncionarioDTO> getALL(){
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public  String delete(UUID funcionarioId){
        repository.deleteById(funcionarioId);
        return "Funcionário DELETADO!";
    }

}
