package com.example.projetoAluguel.model.funcionario;

import com.example.projetoAluguel.model.filial.Filial;
import com.example.projetoAluguel.model.filial.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private FilialRepository repositoryFilial;

    public FuncionarioDTO criar(FuncionarioDTO funcionarioDTO, String nomeFilial){
        Funcionario funcionario = new Funcionario(); // startando uma entidade que se comunica com o banco de dados
        Filial filial = repositoryFilial.findByNome(nomeFilial);
        funcionario.setFilial(filial); //buscando pelo nome da filial usando o médoto do repositório
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCpf(funcionarioDTO.getCpf());
        funcionario.setFuncao(funcionarioDTO.getFuncao());
        funcionario.setCodFuncionario(funcionarioDTO.getCod_funcionario());
        funcionario.setStatus(funcionarioDTO.getStatus());
        repository.save(funcionario);
        return funcionarioDTO;
    }

    public FuncionarioDTO atualizar(FuncionarioDTO funcionarioDTO, UUID funcionarioId){
        Funcionario funcionarioDatabase = repository.getReferenceById(funcionarioId); //carrega do banco de dados o funcionário solicitado e armazena em uma entidade
        funcionarioDatabase.setNome(funcionarioDTO.getNome());
        funcionarioDatabase.setStatus(funcionarioDTO.getStatus()); // altera as informações da entidade com as informações inseridas no objeto DTO
        funcionarioDatabase.setCodFuncionario(funcionarioDTO.getCod_funcionario());
        funcionarioDatabase.setFuncao(funcionarioDTO.getFuncao());
        funcionarioDatabase.setCpf(funcionarioDTO.getCpf());
        funcionarioDatabase.setFilial(repositoryFilial.findByNome(funcionarioDTO.getFilialDTO().getNome()));
        repository.save(funcionarioDatabase); // salva a entidade após realizar as alterações
        return funcionarioDTO;
    }

    private FuncionarioDTO converter(Funcionario funcionario){
        FuncionarioDTO result = new FuncionarioDTO();
        result.setId(funcionario.getId());
        result.getFilialDTO().setNome(funcionario.getFilial().getNome());  //primeiro trago meu objeto DTO para acessar os métodos dele e depois insiro o nome utilizando o método do objeto
        result.getFilialDTO().setCnpj(funcionario.getFilial().getCnpj());
        result.setNome(funcionario.getNome());
        result.setCpf(funcionario.getCpf());
        result.setFuncao(funcionario.getFuncao());
        result.setCod_funcionario(funcionario.getCodFuncionario());
        result.setStatus(funcionario.getStatus());
//funcionario.getFilial().getNome();
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
