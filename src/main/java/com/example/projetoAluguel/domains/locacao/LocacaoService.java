package com.example.projetoAluguel.domains.locacao;

import com.example.projetoAluguel.domains.cliente.Cliente;
import com.example.projetoAluguel.domains.cliente.ClienteRepository;
import com.example.projetoAluguel.domains.filial.Filial;
import com.example.projetoAluguel.domains.filial.FilialRepository;
import com.example.projetoAluguel.domains.funcionario.Funcionario;
import com.example.projetoAluguel.domains.funcionario.FuncionarioRepository;
import com.example.projetoAluguel.domains.motorista.Motorista;
import com.example.projetoAluguel.domains.motorista.MotoristaRepository;
import com.example.projetoAluguel.domains.veiculo.Veiculo;
import com.example.projetoAluguel.domains.veiculo.VeiculoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LocacaoService {
    @Autowired
    private LocacaoRepository repository;

    @Autowired
    private MotoristaRepository repositoryMotrista;

    @Autowired
    private ClienteRepository repositoryCliente;

    @Autowired
    private VeiculoRepository repositoryVeiculo;

    @Autowired
    private FuncionarioRepository repositoryFuncionario;

    @Autowired
    private FilialRepository repositoryFilial;

    public LocacaoDTO criar(LocacaoDTO locacaoDTO) throws JsonProcessingException {
        Optional<Motorista> motorista = repositoryMotrista.findByCnh(locacaoDTO.getMotoristaDTO().getCnh());
        Optional<Cliente> cliente = repositoryCliente.findByCpfCnpj(locacaoDTO.getClienteDTO().getCpfCnpj());
        Veiculo veiculo = repositoryVeiculo.findByPlaca(locacaoDTO.getVeiculoDTO().getPlaca());
        Funcionario funcionario = repositoryFuncionario.findByCodFuncionario(locacaoDTO.getFuncionarioDTO().getCod_funcionario());
        Filial filial = repositoryFilial.findByNome(locacaoDTO.getFilialDTO().getNome());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        String locacaoDTOJson = objectMapper.writeValueAsString(locacaoDTO);
        Locacao locacao = objectMapper.readValue(locacaoDTOJson, Locacao.class);

        motorista.ifPresent(locacao::setMotorista);
        cliente.ifPresent(locacao::setCliente);
        locacao.setVeiculo(veiculo);
        locacao.setFuncionario(funcionario);
        locacao.setFilial(filial);
        locacao.setEnd_retirada(repositoryFilial.findByNome(locacaoDTO.getFilialDTO().getNome()).getEndereco());
        locacao.setCat_veiculo(repositoryVeiculo.findByPlaca(locacaoDTO.getVeiculoDTO().getPlaca()).getCategoria());
        locacao.setCnh_vinculada(locacaoDTO.getMotoristaDTO().getCnh());
        locacao.setCodLocacao(generateCod());
        repository.save(locacao);
        return  locacaoDTO;

//        locacao.setDt_pedido(locacaoDTO.getDt_pedido());
//        locacao.setDt_inicio(locacaoDTO.getDt_inicio());
//        locacao.setDt_fim(locacaoDTO.getDt_fim());
//        locacao.setEnd_devolucao(locacaoDTO.getEnd_devolucao());
//        locacao.setStatus(locacaoDTO.getStatus());
//        locacao.setPontos_fidelidade(locacaoDTO.getPontos_fidelidade());
//        locacao.setContrato_ass(locacaoDTO.isContrato_ass());

    }

    public LocacaoDTO atualizar(LocacaoDTO locacaoDTO, int codLocacao){ // preciso verificar cada campo se a atualização está passando nulo ou mudando o valor
        Locacao locacaoDatabase = repository.findByCodLocacao(codLocacao);
        if (locacaoDatabase != null){
            if(locacaoDTO.getVeiculoDTO().getPlaca() != null){ // Atualizar informações do veículo
                locacaoDatabase.setVeiculo(repositoryVeiculo.findByPlaca(locacaoDTO.getVeiculoDTO().getPlaca()));
                locacaoDatabase.setCat_veiculo(repositoryVeiculo.findByPlaca(locacaoDTO.getVeiculoDTO().getPlaca()).getCategoria());
                locacaoDatabase.setPontos_fidelidade(locacaoDTO.getPontos_fidelidade());
                if(!Objects.equals(locacaoDatabase.getEnd_retirada(), repositoryVeiculo.findByPlaca(locacaoDTO.getVeiculoDTO().getPlaca()).getFilial().getEndereco())){
                    locacaoDatabase.setEnd_retirada(repositoryVeiculo.findByPlaca(locacaoDTO.getVeiculoDTO().getPlaca()).getFilial().getEndereco());
                }

            }
            if (locacaoDTO.getMotoristaDTO().getCnh() != null){
                Optional<Motorista> motorista = repositoryMotrista.findByCnh(locacaoDTO.getMotoristaDTO().getCnh());
                motorista.ifPresent(locacaoDatabase::setMotorista);
                locacaoDatabase.setCnh_vinculada(locacaoDTO.getMotoristaDTO().getCnh());
            }

            if((locacaoDatabase.getDt_inicio() != locacaoDTO.getDt_inicio()) && (null != locacaoDTO.getDt_inicio())){
                locacaoDatabase.setDt_inicio(locacaoDTO.getDt_inicio());
            }
            if((locacaoDatabase.getDt_fim() != locacaoDTO.getDt_fim()) && (locacaoDTO.getDt_fim() != null) ){
                locacaoDatabase.setDt_fim(locacaoDTO.getDt_fim());
            }
            if ((!Objects.equals(locacaoDatabase.getEnd_devolucao(), locacaoDTO.getEnd_devolucao())) && (locacaoDTO.getEnd_devolucao() != null)){
                locacaoDatabase.setEnd_devolucao(locacaoDTO.getEnd_devolucao());
            }
            if((!Objects.equals(locacaoDatabase.getStatus(), locacaoDTO.getStatus())) && (locacaoDTO.getStatus() != null)){
                locacaoDatabase.setStatus(locacaoDTO.getStatus());
            }
            if(locacaoDTO.isContrato_ass()){
                locacaoDatabase.setContrato_ass(locacaoDTO.isContrato_ass());
            }
            repository.save(locacaoDatabase);
            return locacaoDTO;
        }else{
            return locacaoDTO;
        }

    }

    private LocacaoDTO converter(Locacao locacao){
        LocacaoDTO result = new LocacaoDTO();

        result.setId(locacao.getId());
        result.getMotoristaDTO().setNome(locacao.getMotorista().getNome());
        result.getMotoristaDTO().setCnh(locacao.getMotorista().getCnh());
        result.getMotoristaDTO().setDt_nascimento(locacao.getMotorista().getDt_nascimento().toString());
        result.getMotoristaDTO().setCpf(locacao.getMotorista().getCpf());

        result.getClienteDTO().setNome(locacao.getCliente().getNome());
        result.getClienteDTO().setCpfCnpj(locacao.getCliente().getCpfCnpj());
        result.getClienteDTO().setTelefone(locacao.getCliente().getTelefone());
        result.getClienteDTO().setTipo(locacao.getCliente().getTipo());
        result.getClienteDTO().setTotal_fidelidade(locacao.getCliente().getTotal_fidelidade());

        result.getVeiculoDTO().setCategoria(locacao.getVeiculo().getCategoria());
        result.getVeiculoDTO().setPlaca(locacao.getVeiculo().getPlaca());

        result.getFuncionarioDTO().setNome(locacao.getFuncionario().getNome());
        result.getFuncionarioDTO().setFuncao(locacao.getFuncionario().getFuncao());

        result.getFilialDTO().setNome(locacao.getFilial().getNome());
        result.getFilialDTO().setCnpj(locacao.getFilial().getCnpj());
        result.getFilialDTO().setEndereco(locacao.getFilial().getEndereco());

        result.setCodLocacao(locacao.getCodLocacao());
        result.setCat_veiculo(locacao.getCat_veiculo());
        result.setCnh_vinculada(locacao.getCnh_vinculada());
        result.setDt_pedido(locacao.getDt_pedido());
        result.setDt_inicio(locacao.getDt_inicio());
        result.setDt_fim(locacao.getDt_fim());
        result.setEnd_retirada(locacao.getEnd_retirada());
        result.setEnd_devolucao(locacao.getEnd_devolucao());
        result.setStatus(locacao.getStatus());
        result.setPontos_fidelidade(locacao.getPontos_fidelidade());
        result.setContrato_ass(locacao.isContrato_ass());
        return  result;
    }

    private LocacaoDTO converterOptional(Optional<Locacao> locacao){
        LocacaoDTO result = new LocacaoDTO();

        locacao.ifPresent(locacao1 -> {
            result.setId(locacao1.getId());
            result.getMotoristaDTO().setNome(locacao1.getMotorista().getNome());
            result.getMotoristaDTO().setCnh(locacao1.getMotorista().getCnh());
            result.getMotoristaDTO().setDt_nascimento(locacao1.getMotorista().getDt_nascimento().toString());
            result.getMotoristaDTO().setCpf(locacao1.getMotorista().getCpf());

            result.getClienteDTO().setNome(locacao1.getCliente().getNome());
            result.getClienteDTO().setCpfCnpj(locacao1.getCliente().getCpfCnpj());
            result.getClienteDTO().setTelefone(locacao1.getCliente().getTelefone());
            result.getClienteDTO().setTipo(locacao1.getCliente().getTipo());
            result.getClienteDTO().setTotal_fidelidade(locacao1.getCliente().getTotal_fidelidade());

            result.getVeiculoDTO().setCategoria(locacao1.getVeiculo().getCategoria());
            result.getVeiculoDTO().setPlaca(locacao1.getVeiculo().getPlaca());

            result.getFuncionarioDTO().setNome(locacao1.getFuncionario().getNome());
            result.getFuncionarioDTO().setFuncao(locacao1.getFuncionario().getFuncao());

            result.getFilialDTO().setNome(locacao1.getFilial().getNome());
            result.getFilialDTO().setCnpj(locacao1.getFilial().getCnpj());
            result.getFilialDTO().setEndereco(locacao1.getFilial().getEndereco());

            result.setCodLocacao(locacao1.getCodLocacao());
            result.setCat_veiculo(locacao1.getCat_veiculo());
            result.setCnh_vinculada(locacao1.getCnh_vinculada());
            result.setDt_pedido(locacao1.getDt_pedido());
            result.setDt_inicio(locacao1.getDt_inicio());
            result.setDt_fim(locacao1.getDt_fim());
            result.setEnd_retirada(locacao1.getEnd_retirada());
            result.setEnd_devolucao(locacao1.getEnd_devolucao());
            result.setStatus(locacao1.getStatus());
            result.setPontos_fidelidade(locacao1.getPontos_fidelidade());
            result.setContrato_ass(locacao1.isContrato_ass());

        });
        return  result;
    }


    public String delete(int codLocacao){
        return "não é possível deletar um registro de locação";
    }


    private int generateCod(){
        Random random = new Random();
        int cod = random.nextInt(Integer.MAX_VALUE);
        while (repository.findByCodLocacao(cod) != null){
            cod = random.nextInt(Integer.MAX_VALUE);
        }
        return cod;
    }


    public LocacaoDTO getCodLocacao(int codLocacao){
        return converter(repository.findByCodLocacao(codLocacao));
    }

    public List<LocacaoDTO> getStatus(String status){
        return repository
                .findByStatus(status)
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public List<LocacaoDTO> getLocacaoCliente(String tipo, String valor){
        if (Objects.equals(tipo, "cpfCnpj")){
            Optional<Cliente> cliente = repositoryCliente.findByCpfCnpj(valor);
           if (cliente.isPresent()){
               Cliente cliente1 = cliente.get();
               return repository
                       .findByCliente(cliente1)
                       .stream()
                       .map(this::converter).collect(Collectors.toList());
           }
        }else {
            List<Cliente> clientes = new ArrayList<>();
            List<Locacao> locacoesClientes = new ArrayList<>();
            clientes = repositoryCliente.findByNomeContains(valor);

            for (Cliente cliente: clientes){
                locacoesClientes.addAll(repository.findByCliente(cliente));
            }

            return locacoesClientes.stream().map(this::converter).collect(Collectors.toList());

//            return repository
//                    .findByCliente(repositoryCliente.findByNomeContains(identificador))
//                    .stream()
//                    .map(this::converter).collect(Collectors.toList());
        }
        return null;
    }

    public List<LocacaoDTO> getALL(){
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }


}
