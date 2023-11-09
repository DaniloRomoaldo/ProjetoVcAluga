package com.example.projetoAluguel.domains.funcionario;

import com.example.projetoAluguel.domains.filial.FilialDTO;

import java.util.UUID;

public class FuncionarioDTO { //DTO -> Data Transfer Object, é o meu objeto da Filial, uma cópia da Entidade Filial onde se trafega para o front = é a minha Controller
    private UUID id;
    private FilialDTO filialDTO = new FilialDTO();
    private String nome;
    private String cpf;
    private String funcao;
    private int cod_funcionario;
    private String status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public FilialDTO getFilialDTO() {
        return filialDTO;
    }

    public void setFilialDTO(FilialDTO filialDTO) {
        this.filialDTO = filialDTO;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public int getCod_funcionario() {
        return this.cod_funcionario;
    }

    public void setCod_funcionario(int cod_funcionario) {
        this.cod_funcionario = cod_funcionario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
