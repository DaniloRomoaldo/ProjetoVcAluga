package com.example.projetoAluguel.model.veiculo;

import com.example.projetoAluguel.model.filial.Filial;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_filial", referencedColumnName = "id", nullable = false)
    private Filial filial;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "km_total", nullable = false)
    private int km_total;

    @Column(name = "placa", nullable = false, unique = true, updatable = true)
    private String placa;

    @Column(name = "nome", nullable = false)
    private String nome;


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
