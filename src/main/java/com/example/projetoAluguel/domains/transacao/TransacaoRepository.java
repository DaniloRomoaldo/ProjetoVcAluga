package com.example.projetoAluguel.domains.transacao;

import com.example.projetoAluguel.domains.locacao.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
    Transacao findByCodTransacao(String codTransacao);
    Transacao findByLocacao(Locacao locacao);
    Transacao findByNumNotaFiscal(int codNotaFiscal);

}
