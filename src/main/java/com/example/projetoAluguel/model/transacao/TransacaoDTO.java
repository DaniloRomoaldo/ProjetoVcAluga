package com.example.projetoAluguel.model.transacao;

import com.example.projetoAluguel.model.cliente.ClienteDTO;
import com.example.projetoAluguel.model.filial.FilialDTO;
import com.example.projetoAluguel.model.locacao.LocacaoDTO;

import java.time.OffsetDateTime;
import java.util.UUID;

public class TransacaoDTO {
    private UUID id;
    private FilialDTO filialDTO = new FilialDTO();
    private LocacaoDTO locacaoDTO = new LocacaoDTO();
    private ClienteDTO clienteDTO = new ClienteDTO();
    private float valor_total;
    private OffsetDateTime dt_pagamento = OffsetDateTime.now();
    private String forma_pagamento;
    private int num_nota_fiscal;
    private String status;
    private String cod_transacao;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public FilialDTO getFilialDTO() {
        return filialDTO;
    }

    public void setFilialDTO(FilialDTO filialDTO) {
        this.filialDTO = filialDTO;
    }

    public LocacaoDTO getLocacaoDTO() {
        return locacaoDTO;
    }

    public void setLocacaoDTO(LocacaoDTO locacaoDTO) {
        this.locacaoDTO = locacaoDTO;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public OffsetDateTime getDt_pagamento() {
        return dt_pagamento;
    }

    public void setDt_pagamento(OffsetDateTime dt_pagamento) {
        this.dt_pagamento = dt_pagamento;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public int getNum_nota_fiscal() {
        return num_nota_fiscal;
    }

    public void setNum_nota_fiscal(int num_nota_fiscal) {
        this.num_nota_fiscal = num_nota_fiscal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCod_transacao() {
        return cod_transacao;
    }

    public void setCod_transacao(String cod_transacao) {
        this.cod_transacao = cod_transacao;
    }
}
