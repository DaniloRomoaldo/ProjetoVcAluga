package com.example.projetoAluguel.domains.locacao;

import com.example.projetoAluguel.domains.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, UUID> {
    Locacao findByCodLocacao(int codLocacao);
    List<Locacao> findByCliente(Cliente cliente);
    List<Locacao> findByStatus (String status);
}
