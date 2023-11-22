package com.example.projetoAluguel.domains.motorista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, UUID> {
    Optional<Motorista> findByCnh(String cnhMotorista);
    List<Motorista> findByNomeContains(String nome);
}
