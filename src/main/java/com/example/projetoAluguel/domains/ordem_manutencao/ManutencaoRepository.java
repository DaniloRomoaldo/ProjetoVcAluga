package com.example.projetoAluguel.domains.ordem_manutencao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, UUID> {

    Manutencao findByPlaca(String placaVeiculo);

}
