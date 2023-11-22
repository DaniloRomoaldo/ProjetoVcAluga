package com.example.projetoAluguel.domains.motorista;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class MotoristaDTO {
    private UUID id;
    private String nome;
    private String cpf;
    private String cnh;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dt_nascimento;

    private String status;

    public void setDt_nascimento(String dt_nascimento) { //método padrão que converte String do json em OffsetDateTime

        this.dt_nascimento = LocalDate.parse(dt_nascimento, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    public LocalDate getDt_nascimento() {
        return dt_nascimento;
    }


    public void setDt_nascimentoOffsetDateTime(LocalDate dt_nascimento) { //Método que coleta da Entidade a data e insere no DTO
        this.dt_nascimento = dt_nascimento;
    }


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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
