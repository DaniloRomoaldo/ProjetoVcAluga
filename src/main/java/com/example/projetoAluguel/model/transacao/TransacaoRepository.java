package com.example.projetoAluguel.model.transacao;

import com.example.projetoAluguel.model.locacao.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
    Transacao findByCodTransacao(String codTransacao);
    Transacao findByLocacao(Locacao locacao);
    Transacao findByNumNotaFiscal(int codNotaFiscal);

}
