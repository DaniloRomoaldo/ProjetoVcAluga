package com.example.projetoAluguel.model.cliente;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;


@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf_cnpj", nullable = false, unique = true)
    private String cpf_cnpj;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "dt_cadastro", nullable = false, updatable = false)
    private OffsetDateTime dt_cadastro;

    @Column(name = "total_fidelidade")
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
        this.dt_cadastro = dt_cadastro;
    }

    public int getTotal_fidelidade() {
        return total_fidelidade;
    }

    public void setTotal_fidelidade(int total_fidelidade) {
        this.total_fidelidade = total_fidelidade;
    }
}
