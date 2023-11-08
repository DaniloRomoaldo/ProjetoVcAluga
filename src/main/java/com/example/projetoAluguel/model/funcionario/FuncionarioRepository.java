package com.example.projetoAluguel.model.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

    Funcionario findByNome(String nomeFuncionario);

    Funcionario findByCodFuncionario(int id);

    Funcionario findByCodFuncionarioAndNome(int codFuncionario, String nomeFuncionario);
}
