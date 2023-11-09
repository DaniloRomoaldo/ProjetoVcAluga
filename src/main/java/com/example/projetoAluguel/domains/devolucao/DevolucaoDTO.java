package com.example.projetoAluguel.domains.devolucao;

import com.example.projetoAluguel.domains.cliente.ClienteDTO;
import com.example.projetoAluguel.domains.funcionario.FuncionarioDTO;
import com.example.projetoAluguel.domains.locacao.LocacaoDTO;
import com.example.projetoAluguel.domains.motorista.MotoristaDTO;
import com.example.projetoAluguel.domains.transacao.TransacaoDTO;
import com.example.projetoAluguel.domains.veiculo.VeiculoDTO;

import java.time.OffsetDateTime;
import java.util.UUID;

public class DevolucaoDTO {
    private UUID id;
    private MotoristaDTO motoristaDTO = new MotoristaDTO();
    private ClienteDTO clienteDTO = new ClienteDTO();
    private VeiculoDTO veiculoDTO = new VeiculoDTO();
    private FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
    private LocacaoDTO locacaoDTO = new LocacaoDTO();
    private TransacaoDTO transacaoDTO = new TransacaoDTO();
    private OffsetDateTime dt_devolucao = OffsetDateTime.now();
    private String status;
    private float valorMulta;
    private int codLocacao;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MotoristaDTO getMotoristaDTO() {
        return motoristaDTO;
    }

    public void setMotoristaDTO(MotoristaDTO motoristaDTO) {
        this.motoristaDTO = motoristaDTO;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }

    public VeiculoDTO getVeiculoDTO() {
        return veiculoDTO;
    }

    public void setVeiculoDTO(VeiculoDTO veiculoDTO) {
        this.veiculoDTO = veiculoDTO;
    }

    public FuncionarioDTO getFuncionarioDTO() {
        return funcionarioDTO;
    }

    public void setFuncionarioDTO(FuncionarioDTO funcionarioDTO) {
        this.funcionarioDTO = funcionarioDTO;
    }

    public LocacaoDTO getLocacaoDTO() {
        return locacaoDTO;
    }

    public void setLocacaoDTO(LocacaoDTO locacaoDTO) {
        this.locacaoDTO = locacaoDTO;
    }

    public TransacaoDTO getTransacaoDTO() {
        return transacaoDTO;
    }

    public void setTransacaoDTO(TransacaoDTO transacaoDTO) {
        this.transacaoDTO = transacaoDTO;
    }

    public OffsetDateTime getDt_devolucao() {
        return dt_devolucao;
    }

    public void setDt_devolucao(OffsetDateTime dt_devolucao) {
        this.dt_devolucao = dt_devolucao;
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
