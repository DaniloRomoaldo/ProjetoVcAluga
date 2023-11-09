package com.example.projetoAluguel.domains.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Cliente findByCpfCnpj(String cpfCnpj);
}
