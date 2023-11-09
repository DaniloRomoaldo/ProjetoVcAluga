package com.example.projetoAluguel.domains.log_veiculo;

import com.example.projetoAluguel.domains.filial.FilialDTO;
import com.example.projetoAluguel.domains.veiculo.VeiculoDTO;

import java.time.OffsetDateTime;
import java.util.UUID;

public class LogVeiculoDTO {
    private UUID id;
    private VeiculoDTO veiculoDTO = new VeiculoDTO();
    private FilialDTO filialDTOorigem = new FilialDTO();
    private FilialDTO filialDTOatual = new FilialDTO();
    private OffsetDateTime dt_modificacao = OffsetDateTime.now();
    private String placa;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public VeiculoDTO getVeiculoDTO() {
        return veiculoDTO;
    }

    public void setVeiculoDTO(VeiculoDTO veiculoDTO) {
        this.veiculoDTO = veiculoDTO;
    }

    public FilialDTO getFilialDTOorigem() {
        return filialDTOorigem;
    }

    public void setFilialDTOorigem(FilialDTO filialDTOorigem) {
        this.filialDTOorigem = filialDTOorigem;
    }

    public FilialDTO getFilialDTOatual() {
        return filialDTOatual;
    }

    public void setFilialDTOatual(FilialDTO filialDTOatual) {
        this.filialDTOatual = filialDTOatual;
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
