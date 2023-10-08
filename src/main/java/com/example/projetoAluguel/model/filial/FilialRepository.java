package com.example.projetoAluguel.model.filial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilialRepository extends JpaRepository<Filial, UUID> {
    /*O método Repository é o que indica que a minha classe (Entidade) Filial é um repositório de dados que
    se comunicará com o DB e realizará operações*/
}
