package com.example.projetoAluguel.domains.log_veiculo;

import com.example.projetoAluguel.domains.filial.Filial;
import com.example.projetoAluguel.domains.veiculo.Veiculo;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "log_veiuclo")
public class LogVeiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_veiculo", referencedColumnName = "id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_filial_origem", referencedColumnName = "id", nullable = false)
    private Filial filialOrigem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_filial_atual", referencedColumnName = "id", nullable = false)
    private Filial filialAtual;

    @Column(name = "dt_modificacao", nullable = false, updatable = false)
    private OffsetDateTime dt_modificacao;

    @Column(name = "placa", nullable = false, unique = true, updatable = true)
    private String placa;

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

    public Filial getFilialOrigem() {
        return filialOrigem;
    }

    public void setFilialOrigem(Filial filialOrigem) {
        this.filialOrigem = filialOrigem;
    }

    public Filial getFilialAtual() {
        return filialAtual;
    }

    public void setFilialAtual(Filial filialAtual) {
        this.filialAtual = filialAtual;
    }

    public OffsetDateTime getDt_modificacao() {
        return dt_modificacao;
    }

    public void setDt_modificacao(OffsetDateTime dt_modificacao) {
        this.dt_modificacao = dt_modificacao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
