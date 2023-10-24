package com.example.projetoAluguel.model.funcionario;

import com.example.projetoAluguel.model.filial.Filial;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "funcionario")
public class Funcionario { //Classe que representa a minha Entidade, faz parte do Model que mapeia a classe para o banco, o repository criará a comnicação
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_filial", referencedColumnName = "id", nullable = false)
    private Filial filial;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "funcao", nullable = false)
    private String funcao;

    @Column(name = "cod_funcionario", nullable = false)
    private int codFuncionario;

    @Column(name = "status", nullable = false)
    private String status;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public int getCodFuncionario() {
        return this.codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
