package com.example.projetoAluguel.model.cliente;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ClienteDTO {
    private UUID id;
    private String nome;
    private String cpf_cnpj;
    private String tipo;
    private String telefone;
    private OffsetDateTime dt_cadastro = OffsetDateTime.now();
    private int total_fidelidade;

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

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public OffsetDateTime getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(OffsetDateTime dt_cadastro) {
        this.dt_cadastro = dt_cadastro.minusHours(3);

    }

    public int getTotal_fidelidade() {
        return total_fidelidade;
    }

    public void setTotal_fidelidade(int total_fidelidade) {
        this.total_fidelidade = total_fidelidade;
    }
}
