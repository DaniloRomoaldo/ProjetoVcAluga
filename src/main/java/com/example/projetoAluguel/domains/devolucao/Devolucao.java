package com.example.projetoAluguel.domains.devolucao;

import com.example.projetoAluguel.domains.cliente.Cliente;
import com.example.projetoAluguel.domains.funcionario.Funcionario;
import com.example.projetoAluguel.domains.locacao.Locacao;
import com.example.projetoAluguel.domains.motorista.Motorista;
import com.example.projetoAluguel.domains.transacao.Transacao;
import com.example.projetoAluguel.domains.veiculo.Veiculo;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "devolucao")
public class Devolucao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_motorista", referencedColumnName = "id", nullable = false, updatable = false)
    private Motorista motorista;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false, updatable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_veiculo", referencedColumnName = "id", nullable = false, updatable = false)
    private Veiculo veiculo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id", nullable = false, updatable = false)
    private Funcionario funcionario;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_locacao", referencedColumnName = "id", nullable = false, updatable = false)
    private Locacao locacao;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_transacao", referencedColumnName = "id", nullable = false, updatable = false)
    private Transacao transacao;

    @Column(name = "dt_devolucao", nullable = false, updatable = false)
    private OffsetDateTime dtDevolucao;

    @Column(name = "status", nullable = false, updatable = true)
    private String status;

    @Column(name = "valormulta", nullable = true, updatable = true)
    private float valorMulta;

    @Column(name = "cod_locacao", nullable = false, updatable = false, unique = true)
    private int codLocacao;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    public OffsetDateTime getDtDevolucao() {
        return dtDevolucao;
    }

    public void setDtDevolucao(OffsetDateTime dtDevolucao) {
        this.dtDevolucao = dtDevolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(float valorMulta) {
        this.valorMulta = valorMulta;
    }

    public int getCodLocacao() {
        return codLocacao;
    }

    public void setCodLocacao(int codLocacao) {
        this.codLocacao = codLocacao;
    }
}
