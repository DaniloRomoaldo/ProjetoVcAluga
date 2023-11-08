package com.example.projetoAluguel.model.transacao;

import com.example.projetoAluguel.model.cliente.Cliente;
import com.example.projetoAluguel.model.filial.Filial;
import com.example.projetoAluguel.model.locacao.Locacao;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_filial", referencedColumnName = "id", nullable = false, updatable = false)
    private Filial filial;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_locacao", referencedColumnName = "id", nullable = false, updatable = false)
    private Locacao locacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false, updatable = false)
    private Cliente cliente;

    @Column(name = "valor_total", updatable = false, nullable = false)
    private float valor_total;

    @Column(name = "dt_pagamento", nullable = false, updatable = false)
    private OffsetDateTime dtPagamento;

    @Column(name = "forma_pagamento", nullable = false, updatable = false)
    private String formaPagamento;

    @Column(name = "num_nota_fiscal", nullable = false, updatable = false)
    private int numNotaFiscal;

    @Column(name = "status", nullable = false, updatable = true)
    private String status;

    @Column(name = "cod_transacao", nullable = false, updatable = false)
    private String codTransacao;


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

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public OffsetDateTime getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(OffsetDateTime dtPagamento) {
        this.dtPagamento = dtPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getNumNotaFiscal() {
        return numNotaFiscal;
    }

    public void setNumNotaFiscal(int numNotaFiscal) {
        this.numNotaFiscal = numNotaFiscal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodTransacao() {
        return codTransacao;
    }

    public void setCodTransacao(String codTransacao) {
        this.codTransacao = codTransacao;
    }
}
