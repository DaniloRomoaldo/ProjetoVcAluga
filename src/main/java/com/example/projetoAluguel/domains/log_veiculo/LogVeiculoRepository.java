package com.example.projetoAluguel.domains.log_veiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LogVeiculoRepository extends JpaRepository<LogVeiculo, UUID> {
    LogVeiculo findByPlaca (String placaVeiculo);
}
