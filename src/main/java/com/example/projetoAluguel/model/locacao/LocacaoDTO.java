package com.example.projetoAluguel.model.locacao;

import com.example.projetoAluguel.model.cliente.ClienteDTO;
import com.example.projetoAluguel.model.filial.FilialDTO;
import com.example.projetoAluguel.model.funcionario.FuncionarioDTO;
import com.example.projetoAluguel.model.motorista.MotoristaDTO;
import com.example.projetoAluguel.model.veiculo.VeiculoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class LocacaoDTO {
    private UUID id;
    private MotoristaDTO motoristaDTO = new MotoristaDTO();
    private ClienteDTO clienteDTO = new ClienteDTO();
    private VeiculoDTO veiculoDTO = new VeiculoDTO();
    private FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
    private FilialDTO filialDTO = new FilialDTO();
    private int codLocacao;
    private String cat_veiculo;
    private String cnh_vinculada;
    private OffsetDateTime dt_pedido = OffsetDateTime.now();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dt_inicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dt_fim;
    private String end_retirada;
    private String end_devolucao;
    private String status;
    private int pontos_fidelidade;
    private boolean contrato_ass;

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


    public FilialDTO getFilialDTO() {
        return filialDTO;
    }

    public void setFilialDTO(FilialDTO filialDTO) {
        this.filialDTO = filialDTO;
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
