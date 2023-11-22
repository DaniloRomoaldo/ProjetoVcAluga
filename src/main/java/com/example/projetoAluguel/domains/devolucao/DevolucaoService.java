package com.example.projetoAluguel.domains.devolucao;

import com.example.projetoAluguel.domains.cliente.Cliente;
import com.example.projetoAluguel.domains.cliente.ClienteRepository;
import com.example.projetoAluguel.domains.funcionario.Funcionario;
import com.example.projetoAluguel.domains.funcionario.FuncionarioRepository;
import com.example.projetoAluguel.domains.locacao.Locacao;
import com.example.projetoAluguel.domains.locacao.LocacaoRepository;
import com.example.projetoAluguel.domains.motorista.Motorista;
import com.example.projetoAluguel.domains.motorista.MotoristaRepository;
import com.example.projetoAluguel.domains.transacao.Transacao;
import com.example.projetoAluguel.domains.transacao.TransacaoRepository;
import com.example.projetoAluguel.domains.veiculo.Veiculo;
import com.example.projetoAluguel.domains.veiculo.VeiculoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DevolucaoService {
    @Autowired
    private DevolucaoRepository repository;

    @Autowired
    private MotoristaRepository repositoryMotorista;

    @Autowired
    private ClienteRepository repositoryCliente;

    @Autowired
    private VeiculoRepository repositoryVeiculo;

    @Autowired
    private FuncionarioRepository repositoryFuncionario;

    @Autowired
    private LocacaoRepository repositoryLocacao;

    @Autowired
    private TransacaoRepository repositoryTransacao;

    public DevolucaoDTO criar(DevolucaoDTO devolucaoDTO) throws JsonProcessingException {
        Optional<Motorista> motorista = repositoryMotorista.findByCnh(devolucaoDTO.getMotoristaDTO().getCnh());
        Optional<Cliente> cliente = repositoryCliente.findByCpfCnpj(devolucaoDTO.getClienteDTO().getCpfCnpj());
        Veiculo veiculo= repositoryVeiculo.findByPlaca(devolucaoDTO.getVeiculoDTO().getPlaca());
        Funcionario funcionario = repositoryFuncionario.findByCodFuncionario(devolucaoDTO.getFuncionarioDTO().getCod_funcionario());
        Locacao locacao = repositoryLocacao.findByCodLocacao(devolucaoDTO.getLocacaoDTO().getCodLocacao());
        Transacao transacao = repositoryTransacao.findByLocacao(repositoryLocacao.findByCodLocacao(devolucaoDTO.getLocacaoDTO().getCodLocacao()));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        String devolucaoDTOJson = objectMapper.writeValueAsString(devolucaoDTO);
        Devolucao devolucao = objectMapper.readValue(devolucaoDTOJson, Devolucao.class);

        motorista.ifPresent(devolucao::setMotorista);
        cliente.ifPresent(devolucao::setCliente);
        devolucao.setVeiculo(veiculo);
        devolucao.setFuncionario(funcionario);
        devolucao.setLocacao(locacao);
        devolucao.setTransacao(transacao);
        repository.save(devolucao);
        return devolucaoDTO;

//        devolucao.setDtDevolucao(devolucaoDTO.getDt_devolucao());
//        devolucao.setStatus(devolucaoDTO.getStatus());
//        devolucao.setValorMulta(devolucaoDTO.getValorMulta());
//        devolucao.setCodLocacao(locacao.getCodLocacao());
    }

    public DevolucaoDTO atualizar(DevolucaoDTO devolucaoDTO, int codLocacao){
        Devolucao devolucaoDatabase = repository.findByCodLocacao(codLocacao);

        if (devolucaoDatabase != null){
            Optional<Motorista> motorista = repositoryMotorista.findByCnh(devolucaoDTO.getMotoristaDTO().getCnh());
            devolucaoDatabase.setLocacao(repositoryLocacao.findByCodLocacao(devolucaoDTO.getLocacaoDTO().getCodLocacao()));
            motorista.ifPresent(devolucaoDatabase::setMotorista);
            devolucaoDatabase.setVeiculo(repositoryVeiculo.findByPlaca(devolucaoDTO.getVeiculoDTO().getPlaca()));

            if(Objects.equals(devolucaoDTO.getMotoristaDTO().getCnh(), devolucaoDatabase.getMotorista().getCnh())){
                if (!Objects.equals(devolucaoDatabase.getStatus(), devolucaoDTO.getStatus()) && (devolucaoDTO.getStatus() != null )){ //devolução precisa atualizar o Status da locação
                    devolucaoDatabase.setStatus(devolucaoDTO.getStatus());
                }
            }

            repository.save(devolucaoDatabase);
        }else {
            return devolucaoDTO; // deve retornar nulo
        }

        return devolucaoDTO;
    }

    public  DevolucaoDTO converter(Devolucao devolucao){
        DevolucaoDTO result = new DevolucaoDTO();

        result.setId(devolucao.getId());

        result.getMotoristaDTO().setNome(devolucao.getMotorista().getNome());
        result.getMotoristaDTO().setCpf(devolucao.getMotorista().getCpf());
        result.getMotoristaDTO().setCnh(devolucao.getMotorista().getCnh());

        result.getClienteDTO().setNome(devolucao.getCliente().getNome());
        result.getClienteDTO().setCpfCnpj(devolucao.getCliente().getCpfCnpj());

        result.getVeiculoDTO().setPlaca(devolucao.getVeiculo().getPlaca());
        result.getVeiculoDTO().setCategoria(devolucao.getVeiculo().getCategoria());

        result.getFuncionarioDTO().setNome(devolucao.getFuncionario().getNome());
        result.getFuncionarioDTO().setFuncao(devolucao.getFuncionario().getFuncao());

        result.setDt_devolucao(devolucao.getDtDevolucao());
        result.setStatus(devolucao.getStatus());
        result.setValorMulta(devolucao.getValorMulta());
        result.setCodLocacao(devolucao.getCodLocacao());

        return result;
    }

    public List<DevolucaoDTO> getALL(){
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public String delete(int codLocacao){
        return "Não é possível deletar um registro de devolução";
    }


}
