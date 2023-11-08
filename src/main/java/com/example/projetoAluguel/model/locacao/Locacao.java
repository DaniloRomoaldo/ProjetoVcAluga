package com.example.projetoAluguel.model.locacao;

import com.example.projetoAluguel.model.cliente.Cliente;
import com.example.projetoAluguel.model.filial.Filial;
import com.example.projetoAluguel.model.funcionario.Funcionario;
import com.example.projetoAluguel.model.motorista.Motorista;
import com.example.projetoAluguel.model.veiculo.Veiculo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "locacao")
public class Locacao {
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
    @JoinColumn(name = "id_veiculo", referencedColumnName = "id", nullable = false, updatable = true)
    private Veiculo veiculo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id", nullable = false, updatable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_filial", referencedColumnName = "id", nullable = false, updatable = false)
    private Filial filial;

    @Column(name = "cod_locacao")
    private int codLocacao;

    @Column(name = "cat_veiculo", nullable = false, updatable = false)
    private String cat_veiculo;

    @Column(name = "cnh_vinculada", nullable = false, updatable = false)
    private String cnh_vinculada;

    @Column(name = "dt_pedido", nullable = false, updatable = true)
    private OffsetDateTime dt_pedido;

    @Column(name = "dt_inicio", nullable = false, updatable = true)
    private LocalDate dt_inicio;

    @Column(name = "dt_fim", nullable = false, updatable = true)
    private LocalDate dt_fim;

    @Column(name = "end_retirada", nullable = false, updatable = true)
    private String end_retirada;

    @Column(name = "end_devolucao", nullable = false, updatable = true)
    private String end_devolucao;

    @Column(name = "status", nullable = false, updatable = true)
    private String status;

    @Column(name = "pontos_fidelidade", nullable = false,updatable = true) // se uma categoria sofrer upgrade pode alterar o valor dos pontos
    private int pontos_fidelidade;

    @Column(name = "contrato_ass", nullable = false, updatable = true)
    private boolean contrato_ass;


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


    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public int getCodLocacao() {
        return codLocacao;
    }

    public void setCodLocacao(int codLocacao) {
        this.codLocacao = codLocacao;
    }

    public String getCat_veiculo() {
        return cat_veiculo;
    }

    public void setCat_veiculo(String cat_veiculo) {
        this.cat_veiculo = cat_veiculo;
    }

    public String getCnh_vinculada() {
        return cnh_vinculada;
    }

    public void setCnh_vinculada(String cnh_vinculada) {
        this.cnh_vinculada = cnh_vinculada;
    }

    public OffsetDateTime getDt_pedido() {
        return dt_pedido;
    }

    public void setDt_pedido(OffsetDateTime dt_pedido) {
        this.dt_pedido = dt_pedido;
    }

    public LocalDate getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(LocalDate dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public LocalDate getDt_fim() {
        return dt_fim;
    }

    public void setDt_fim(LocalDate dt_fim) {
        this.dt_fim = dt_fim;
    }

    public String getEnd_retirada() {
        return end_retirada;
    }

    public void setEnd_retirada(String end_retirada) {
        this.end_retirada = end_retirada;
    }

    public String getEnd_devolucao() {
        return end_devolucao;
    }

    public void setEnd_devolucao(String end_devolucao) {
        this.end_devolucao = end_devolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPontos_fidelidade() {
        return pontos_fidelidade;
    }

    public void setPontos_fidelidade(int pontos_fidelidade) {
        this.pontos_fidelidade = pontos_fidelidade;
    }

    public boolean isContrato_ass() {
        return contrato_ass;
    }

    public void setContrato_ass(boolean contrato_ass) {
        this.contrato_ass = contrato_ass;
    }
}
