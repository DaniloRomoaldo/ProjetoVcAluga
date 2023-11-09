package com.example.projetoAluguel.domains.filial;

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
public class FilialService { //É a classe responsável por interagir com o banco de dados comunicando a Entidade com o Objeto e trafegando os dados
    @Autowired
    private FilialRepository repository;

    public FilialDTO criar(FilialDTO filialDTO) throws JsonProcessingException { // Os metodos são so serviços de comunicação utilizando o Objeto DTO e a Entidade

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String filialDTOJson = objectMapper.writeValueAsString(filialDTO);
        Filial filial = objectMapper.readValue(filialDTOJson, Filial.class);

        repository.save(filial);
        return filialDTO;

//        filialDTO.setId(filial.getId());
//        Filial filial = new Filial();
//        filial.setNome(filialDTO.getNome());
//        filial.setCnpj(filialDTO.getCnpj());
//        filial.setEndereco(filialDTO.getEndereco());
    }

    //Método público que retorna um Objeto do tipo FilialDTO
    public FilialDTO atualizar(FilialDTO filialDTO, String nomeFilial){
        Filial filialDatabase = repository.findByNome(nomeFilial); // coleta do banco de dados a informação através do ID e transfere os dados para uma entidade

       if (filialDatabase != null){

           if ((!Objects.equals(filialDatabase.getNome(), filialDTO.getNome())) && (filialDTO.getNome() != null)){
               filialDatabase.setNome(filialDTO.getNome());
           }
           if ((!Objects.equals(filialDatabase.getCnpj(), filialDTO.getCnpj())) && (filialDTO.getCnpj() != null)){
               filialDatabase.setCnpj(filialDTO.getCnpj());
           }
           if ((!Objects.equals(filialDatabase.getEndereco(), filialDTO.getEndereco())) && (filialDTO.getEndereco() != null)){
               filialDatabase.setEndereco(filialDTO.getEndereco());
           }

           repository.save(filialDatabase);
       }

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
