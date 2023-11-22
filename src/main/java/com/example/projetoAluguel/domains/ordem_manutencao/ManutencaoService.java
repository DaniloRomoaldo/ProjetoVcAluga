package com.example.projetoAluguel.domains.ordem_manutencao;

import com.example.projetoAluguel.domains.funcionario.Funcionario;
import com.example.projetoAluguel.domains.funcionario.FuncionarioRepository;
import com.example.projetoAluguel.domains.veiculo.Veiculo;
import com.example.projetoAluguel.domains.veiculo.VeiculoRepository;
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
        Funcionario funcionario = repositoryFuncionario.findByNome(manutencaoDTO.getFuncionarioDTO().getNome()); //!!!!#pegar o codFuncionario do cookie da sessão
        Veiculo veiculo = repositoryVeiculo.findByPlaca(manutencaoDTO.getVeiculoDTO().getPlaca()); //a tabela de veículo precisa ser refatorada, adicionar pelo menos a plcada do veículo

        Manutencao manutencao = new Manutencao();
        manutencao.setFuncionario(funcionario);
        manutencao.setVeiculo(veiculo);
        manutencao.setDt_entrada(manutencaoDTO.getDt_entrada()); //
        manutencao.setDt_previsao(manutencaoDTO.getDt_previsao()); // Não é verboso o suficiente para utilizar objcet mapping
        manutencao.setPlaca(manutencao.getVeiculo().getPlaca());
        manutencao.setStatus(manutencaoDTO.getStatus());
        repository.save(manutencao);
        return manutencaoDTO;
    }

    public ManutencaoDTO atualizar(ManutencaoDTO manutencaoDTO, String placaVeiculo){
        Manutencao manutencaoDatabase = repository.findByPlaca(placaVeiculo);
        if(manutencaoDatabase != null){

            if((manutencaoDatabase.getDt_previsao() != manutencaoDTO.getDt_previsao()) && (manutencaoDTO.getDt_previsao() != null)){
                manutencaoDatabase.setDt_previsao(manutencaoDTO.getDt_previsao());
            }

            if ((manutencaoDatabase.getStatus() != manutencaoDTO.getStatus()) && (manutencaoDTO.getStatus() != null)){
                manutencaoDatabase.setStatus(manutencaoDTO.getStatus());
            }


            repository.save(manutencaoDatabase);
        }

        return manutencaoDTO;
    }

    private ManutencaoDTO converter(Manutencao manutencao){
        ManutencaoDTO result = new ManutencaoDTO();
        result.setId(manutencao.getId());
        result.getVeiculoDTO().setNome(manutencao.getVeiculo().getNome());
        result.getVeiculoDTO().setStatus(manutencao.getVeiculo().getStatus());
        result.getVeiculoDTO().setCategoria(manutencao.getVeiculo().getCategoria());
        result.getVeiculoDTO().getFilialDTO().setNome(manutencao.getVeiculo().getFilial().getNome());
        result.getVeiculoDTO().getFilialDTO().setEndereco(manutencao.getVeiculo().getFilial().getEndereco());
        result.getVeiculoDTO().setPlaca(manutencao.getVeiculo().getPlaca());
        result.setStatus(manutencao.getStatus());
        result.getFuncionarioDTO().setNome(manutencao.getFuncionario().getNome());
        result.getFuncionarioDTO().setFuncao(manutencao.getFuncionario().getFuncao());
        result.getFuncionarioDTO().getFilialDTO().setNome(manutencao.getFuncionario().getFilial().getNome());
        result.getFuncionarioDTO().getFilialDTO().setEndereco(manutencao.getFuncionario().getFilial().getEndereco());
        result.setDt_entrada(manutencao.getDt_entrada());
        result.setDt_previsao(manutencao.getDt_previsao());
        result.setPlaca(manutencao.getPlaca());
        return result;
    }



    public String delete(String placaVeiculo){
        repository.deleteById(repository.findByPlaca(placaVeiculo).getId());
        return "Registro de Manutenção Deletado";
    }

    public List<ManutencaoDTO> getALL(){
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public List<ManutencaoDTO> getManutencao(UUID veiculoId){

        return repository
                .findByVeiculo(repositoryVeiculo.findById(veiculoId)) // primeiro busca a entidade referente ao id, depois envia a entidade para o repository
                .stream()
                .map(this::converter).collect(Collectors.toList()); // busca as manutenções referentes a entidade

    }

}
