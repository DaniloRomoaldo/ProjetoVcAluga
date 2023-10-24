package com.example.projetoAluguel.model.ordem_manutencao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ManutencaoRepository extends JpaRepository<Manutencao, UUID> {

}
