package com.example.projetoAluguel.domains.ordem_manutencao;

import com.example.projetoAluguel.domains.veiculo.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, UUID> {
    Manutencao findByPlaca(String placaVeiculo);
    List<Manutencao> findByVeiculo(Optional<Veiculo> veiculo);

}
