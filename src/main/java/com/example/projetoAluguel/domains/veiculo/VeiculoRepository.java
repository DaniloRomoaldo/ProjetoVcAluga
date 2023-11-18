package com.example.projetoAluguel.domains.veiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {

    Veiculo findByPlaca(String placa);
    List<Veiculo> findByStatus(String status);
    List<Veiculo> findByCategoria(String Categoria);
}
