package com.example.projetoAluguel.model.ordem_manutencao;

import com.example.projetoAluguel.model.funcionario.Funcionario;
import com.example.projetoAluguel.model.funcionario.FuncionarioRepository;
import com.example.projetoAluguel.model.veiculo.Veiculo;
import com.example.projetoAluguel.model.veiculo.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ManutencaoService {

    @Autowired
    private ManutencaoRepository repository;

    @Autowired
    private VeiculoRepository repositoryVeiculo;

    @Autowired
    private FuncionarioRepository repositoryFuncionario;


    public ManutencaoDTO criar(ManutencaoDTO manutencaoDTO){
        Funcionario funcionario = repositoryFuncionario.findByCodFuncionario(manutencaoDTO.getFuncionarioDTO().getCod_funcionario());
        Veiculo veiculo = repositoryVeiculo.getReferenceById(manutencaoDTO.getVeiculoDTO().getId()); //a tabela de veículo precisa ser refatorada, adicionar pelo menos a plcada do veículo

        Manutencao manutencao = new Manutencao();
        manutencao.setFuncionario(funcionario);
        manutencao.setVeiculo(veiculo);
        manutencao.setDt_entrada(manutencaoDTO.getDt_entrada());
        manutencao.setDt_previsao(manutencaoDTO.getDt_previsao());
        repository.save(manutencao);
        return manutencaoDTO;
    }

    public ManutencaoDTO atualizar(ManutencaoDTO manutencaoDTO, UUID manutencaoId){
        Manutencao manutencaoDatabase = repository.getReferenceById(manutencaoId);
        manutencaoDatabase.setFuncionario(repositoryFuncionario.findByCodFuncionario(manutencaoDTO.getFuncionarioDTO().getCod_funcionario()));
        manutencaoDatabase.setVeiculo(repositoryVeiculo.getReferenceById(manutencaoDTO.getVeiculoDTO().getId()));
        manutencaoDatabase.setDt_entrada(manutencaoDTO.getDt_entrada());
        manutencaoDatabase.setDt_previsao(manutencaoDTO.getDt_previsao());
        repository.save(manutencaoDatabase);
        return manutencaoDTO;
    }

    public ManutencaoDTO converter(Manutencao manutencao){
        ManutencaoDTO result = new ManutencaoDTO();
        result.setId(manutencao.getId());
        result.getVeiculoDTO().setStatus(manutencao.getVeiculo().getStatus());
        result.getVeiculoDTO().setCategoria(manutencao.getVeiculo().getCategoria());
        result.getFuncionarioDTO().setNome(manutencao.getFuncionario().getNome());
        result.setDt_entrada(manutencao.getDt_entrada());
        result.setDt_previsao(manutencao.getDt_previsao());
        return result;
    }

    public List<ManutencaoDTO> getALL(){
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public String delete(UUID manutencaoId){
        repository.deleteById(manutencaoId);
        return "Registro de Manutenção Deletado";
    }

}
