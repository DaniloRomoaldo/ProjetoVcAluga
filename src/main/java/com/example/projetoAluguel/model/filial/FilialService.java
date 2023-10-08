package com.example.projetoAluguel.model.filial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FilialService { //É a classe responsável por interagir com o banco de dados comunicando a Entidade com o Objeto e trafegando os dados
    @Autowired
    private FilialRepository repository;

    public FilialDTO criar(FilialDTO filialDTO){ // Os metodos são so serviços de comunicação utilizando o Objeto DTO e a Entidade
        Filial filial = new Filial();
        filial.setNome(filialDTO.getNome());
        filial.setCnpj(filialDTO.getCnpj());
        filial.setEndereco(filialDTO.getEndereco());
        repository.save(filial);
        filialDTO.setId(filial.getId());
        return filialDTO;
    }

    //Método público que retorna um Objeto do tipo FilialDTO
    public FilialDTO atualizar(FilialDTO filialDTO, UUID filialId){
        Filial filialDatabase = repository.getReferenceById(filialId); // coleta do banco de dados a informação através do ID e transfere os dados para uma entidade
        filialDatabase.setNome(filialDTO.getNome());
        filialDatabase.setCnpj(filialDTO.getCnpj());
        filialDatabase.setEndereco(filialDTO.getEndereco());
        repository.save(filialDatabase);
        return filialDTO;
    }

    private FilialDTO converter(Filial filial){ // metodo da classe para converte uma Entidade em DTO (objeto)
        FilialDTO result = new FilialDTO();
        result.setId(filial.getId());
        result.setNome(filial.getNome());
        result.setCnpj(filial.getCnpj());
        result.setEndereco(filial.getEndereco());
        return result;
    }

    public List<FilialDTO> getALL(){ // metodo que pega todos os registros do banco, utiliza o converter para trafegar a informação
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public String delete(UUID filialId){
        repository.deleteById(filialId);
        return "DELETED REGISTER";
    }
}
