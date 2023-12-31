package com.example.projetoAluguel.domains.ordem_manutencao;

import com.example.projetoAluguel.domains.funcionario.Funcionario;
import com.example.projetoAluguel.domains.veiculo.Veiculo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "ordem_manutencao")
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_veiculo", referencedColumnName = "id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id", nullable = false)
    private Funcionario funcionario;

    @Column(name = "dt_entrada", nullable = false, updatable = false)
    private OffsetDateTime dt_entrada;

    @Column(name = "dt_previsao", nullable = true)
    private LocalDate dt_previsao;

    @Column(name = "placa", nullable = false, unique = true)
    private String placa;

    @Column(name = "status", nullable = false, updatable = true)
    private String status;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public OffsetDateTime getDt_entrada() {
        return dt_entrada;
    }

    public void setDt_entrada(OffsetDateTime dt_entrada) {
        this.dt_entrada = dt_entrada;
    }

    public LocalDate getDt_previsao() {
        return dt_previsao;
    }

    public void setDt_previsao(LocalDate dt_previsao) {
        this.dt_previsao = dt_previsao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
