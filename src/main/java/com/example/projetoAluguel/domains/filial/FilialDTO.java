package com.example.projetoAluguel.domains.filial;

import java.util.UUID;

public class FilialDTO { //DTO -> Data Transfer Object, é o meu objeto da Filial, uma cópia da Entidade Filial onde se trafega para o front = é a minha Controller
    private UUID id;
    private String nome;
    private String cnpj;
    private String endereco;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
