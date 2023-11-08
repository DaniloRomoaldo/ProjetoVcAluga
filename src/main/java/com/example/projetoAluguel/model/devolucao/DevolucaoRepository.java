package com.example.projetoAluguel.model.devolucao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DevolucaoRepository extends JpaRepository<Devolucao, UUID> {
 Devolucao findByCodLocacao(int codLocacao);
}
