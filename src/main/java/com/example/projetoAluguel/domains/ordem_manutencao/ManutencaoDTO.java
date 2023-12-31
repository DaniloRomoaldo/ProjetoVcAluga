package com.example.projetoAluguel.domains.ordem_manutencao;

import com.example.projetoAluguel.domains.funcionario.FuncionarioDTO;
import com.example.projetoAluguel.domains.veiculo.VeiculoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ManutencaoDTO {

    private UUID id;

    private FuncionarioDTO funcionarioDTO = new FuncionarioDTO();

    private VeiculoDTO veiculoDTO = new VeiculoDTO();

    private OffsetDateTime dt_entrada = OffsetDateTime.now();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dt_previsao;

    private String placa;

    private String status;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public FuncionarioDTO getFuncionarioDTO() {
        return funcionarioDTO;
    }

    public void setFuncionarioDTO(FuncionarioDTO funcionarioDTO) {
        this.funcionarioDTO = funcionarioDTO;
    }

    public VeiculoDTO getVeiculoDTO() {
        return veiculoDTO;
    }

    public void setVeiculoDTO(VeiculoDTO veiculoDTO) {
        this.veiculoDTO = veiculoDTO;
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
