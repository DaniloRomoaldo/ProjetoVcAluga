package com.example.projetoAluguel.model.veiculo;

import com.example.projetoAluguel.model.filial.FilialDTO;

import java.util.UUID;

public class VeiculoDTO {

    private UUID id;
    private FilialDTO filialDTO = new FilialDTO();
    private String status;
    private String categoria;
    private int km_total;
    private String placa;

    private String nome;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getKm_total() {
        return km_total;
    }

    public void setKm_total(int km_total) {
        this.km_total = km_total;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
