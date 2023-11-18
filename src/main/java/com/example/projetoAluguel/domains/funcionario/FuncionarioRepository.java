package com.example.projetoAluguel.domains.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

    Funcionario findByNome(String nomeFuncionario);

    Funcionario findByCodFuncionario(int id);

    Funcionario findByCodFuncionarioAndNome(int codFuncionario, String nomeFuncionario);

    List<Funcionario> findByNomeContains(String nome);

    List<Funcionario> findByFuncao(String funcao);
}
