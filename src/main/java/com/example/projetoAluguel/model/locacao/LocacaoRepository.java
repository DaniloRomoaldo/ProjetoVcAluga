package com.example.projetoAluguel.model.locacao;

import com.example.projetoAluguel.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, UUID> {
    Locacao findByCodLocacao(int codLocacao);
    List<Locacao> findByCliente(Cliente cliente);
}
