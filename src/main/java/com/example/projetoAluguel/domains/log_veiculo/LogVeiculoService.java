package com.example.projetoAluguel.domains.log_veiculo;

import com.example.projetoAluguel.domains.filial.Filial;
import com.example.projetoAluguel.domains.filial.FilialRepository;
import com.example.projetoAluguel.domains.veiculo.Veiculo;
import com.example.projetoAluguel.domains.veiculo.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogVeiculoService {
    @Autowired
    private LogVeiculoRepository repository;

    @Autowired
    private VeiculoRepository repositoryVeiculo;

    @Autowired
    private FilialRepository repositoryFilial;

    public LogVeiculoDTO criar(LogVeiculoDTO logVeiculoDTO){
        Veiculo veiculo = repositoryVeiculo.findByPlaca(logVeiculoDTO.getVeiculoDTO().getPlaca());
        Filial filialOrigem = repositoryFilial.findByNome(logVeiculoDTO.getFilialDTOorigem().getNome());
        Filial filialAtual = repositoryFilial.findByNome(logVeiculoDTO.getFilialDTOatual().getNome());

        LogVeiculo logVeiculo = new LogVeiculo();
        logVeiculo.setVeiculo(veiculo);
        logVeiculo.setFilialAtual(filialAtual);
        logVeiculo.setFilialOrigem(filialOrigem);
        logVeiculo.setDt_modificacao(logVeiculoDTO.getDt_modificacao()); // não é verboso o suficiente para usar object mapping
        logVeiculo.setPlaca(logVeiculoDTO.getVeiculoDTO().getPlaca());
        repository.save(logVeiculo);
        return logVeiculoDTO;
    }

    public LogVeiculoDTO atualizar(LogVeiculoDTO logVeiculoDTO, String placaVeiculo){
        LogVeiculo logVeiculoDatabase = repository.findByPlaca(placaVeiculo); // procurando o registro pela placa do veículo

        if (logVeiculoDatabase != null){

            if ((logVeiculoDatabase.getFilialAtual() != repositoryFilial.findByNome(logVeiculoDTO.getFilialDTOatual().getNome()))
                    && (logVeiculoDTO.getFilialDTOatual().getNome() != null)){
                logVeiculoDatabase.setFilialAtual(repositoryFilial.findByNome(logVeiculoDTO.getFilialDTOatual().getNome()));

            }
            if((logVeiculoDatabase.getFilialOrigem() != repositoryFilial.findByNome(logVeiculoDTO.getFilialDTOorigem().getNome()))
                    && (logVeiculoDTO.getFilialDTOorigem().getNome() != null)){
                logVeiculoDatabase.setFilialOrigem(repositoryFilial.findByNome(logVeiculoDTO.getFilialDTOorigem().getNome()));
            }

            repository.save(logVeiculoDatabase);
        }

        return logVeiculoDTO;
    }

    private LogVeiculoDTO converter(LogVeiculo logVeiculo){
        LogVeiculoDTO result = new LogVeiculoDTO();
        result.setId(logVeiculo.getId());

        result.getVeiculoDTO().setPlaca(logVeiculo.getVeiculo().getPlaca());
        result.getVeiculoDTO().setCategoria(logVeiculo.getVeiculo().getCategoria());
        result.getVeiculoDTO().setKm_total(logVeiculo.getVeiculo().getKm_total());
        result.getVeiculoDTO().setNome(logVeiculo.getVeiculo().getNome());


        result.getFilialDTOorigem().setNome(logVeiculo.getFilialOrigem().getNome());
        result.getFilialDTOorigem().setEndereco(logVeiculo.getFilialOrigem().getEndereco());
        result.getFilialDTOorigem().setCnpj(logVeiculo.getFilialOrigem().getCnpj());

        result.getFilialDTOatual().setNome(logVeiculo.getFilialAtual().getNome());
        result.getFilialDTOatual().setEndereco(logVeiculo.getFilialAtual().getEndereco());
        result.getFilialDTOatual().setCnpj(logVeiculo.getFilialAtual().getCnpj());

        result.setPlaca(logVeiculo.getPlaca());
        result.setDt_modificacao(logVeiculo.getDt_modificacao());
        return result;
    }

    public List<LogVeiculoDTO> getALL(){
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public String delete(String placaVeiculo){
        return "Registro de veículo não pode ser deletado";
    }

}
