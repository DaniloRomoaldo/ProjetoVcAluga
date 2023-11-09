package com.example.projetoAluguel.domains.transacao;

import com.example.projetoAluguel.domains.cliente.Cliente;
import com.example.projetoAluguel.domains.cliente.ClienteRepository;
import com.example.projetoAluguel.domains.filial.Filial;
import com.example.projetoAluguel.domains.filial.FilialRepository;
import com.example.projetoAluguel.domains.locacao.Locacao;
import com.example.projetoAluguel.domains.locacao.LocacaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private FilialRepository repositoryFilial;

    @Autowired
    private LocacaoRepository repositoryLocacao;

    @Autowired
    private ClienteRepository repositoryCliente;

    public TransacaoDTO criar(TransacaoDTO transacaoDTO) throws JsonProcessingException {
        Filial filial = repositoryFilial.findByNome(transacaoDTO.getFilialDTO().getNome());
        Locacao locacao = repositoryLocacao.findByCodLocacao(transacaoDTO.getLocacaoDTO().getCodLocacao());
        Cliente cliente = repositoryCliente.findByCpfCnpj(transacaoDTO.getClienteDTO().getCpfCnpj());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        String transacaoDTOJson = objectMapper.writeValueAsString(transacaoDTO);
        Transacao transacao = objectMapper.readValue(transacaoDTOJson, Transacao.class);

        transacao.setFilial(filial);
        transacao.setLocacao(locacao);
        transacao.setCliente(cliente);
        transacao.setNumNotaFiscal(generateNumFiscal());
        transacao.setCodTransacao(generateCod());
        repository.save(transacao);
        return transacaoDTO;

//        transacao.setValor_total(transacaoDTO.getValor_total());
//        transacao.setFormaPagamento(transacaoDTO.getForma_pagamento());
//        transacao.setStatus(transacaoDTO.getStatus());
//        transacao.setDtPagamento(transacaoDTO.getDt_pagamento());
    }

    public TransacaoDTO atualizar(TransacaoDTO transacaoDTO, String codTransacao){ // precisa validar os campos de entrada
        Transacao transacaoDatabase = repository.findByCodTransacao(codTransacao);
        if (transacaoDatabase != null){
            transacaoDatabase.setFilial(repositoryFilial.findByNome(transacaoDTO.getFilialDTO().getNome()));
            transacaoDatabase.setLocacao(repositoryLocacao.findByCodLocacao(transacaoDTO.getLocacaoDTO().getCodLocacao()));
            transacaoDatabase.setCliente(repositoryCliente.findByCpfCnpj(transacaoDTO.getClienteDTO().getCpfCnpj()));

            if ((!Objects.equals(transacaoDatabase.getStatus(), transacaoDTO.getStatus())) && (transacaoDTO.getStatus() != null)){
                transacaoDatabase.setStatus(transacaoDTO.getStatus());
            }
            if ((!Objects.equals(transacaoDatabase.getFormaPagamento(), transacaoDTO.getForma_pagamento())) && (transacaoDTO.getForma_pagamento() != null) ){
                transacaoDatabase.setFormaPagamento(transacaoDTO.getForma_pagamento());
            }

            repository.save(transacaoDatabase);
        }else {
            return transacaoDTO;
        }

        return transacaoDTO;

    }

    public TransacaoDTO converter(Transacao transacao){
        TransacaoDTO result = new TransacaoDTO();

        result.setId(transacao.getId());

        result.getLocacaoDTO().getFilialDTO().setNome(transacao.getLocacao().getFilial().getNome());
        result.getLocacaoDTO().getFilialDTO().setCnpj(transacao.getFilial().getCnpj());

        result.getLocacaoDTO().getClienteDTO().setNome(transacao.getLocacao().getCliente().getNome());
        result.getLocacaoDTO().getClienteDTO().setCpfCnpj(transacao.getLocacao().getCliente().getCpfCnpj());
        result.getLocacaoDTO().getClienteDTO().setTelefone(transacao.getLocacao().getCliente().getTelefone());

        result.getLocacaoDTO().setCat_veiculo(transacao.getLocacao().getCat_veiculo());
        result.getLocacaoDTO().setDt_pedido(transacao.getLocacao().getDt_pedido());
        result.getLocacaoDTO().setDt_inicio(transacao.getLocacao().getDt_inicio());
        result.getLocacaoDTO().setDt_fim(transacao.getLocacao().getDt_fim());

        result.getLocacaoDTO().setCat_veiculo(transacao.getLocacao().getCat_veiculo());
        result.getLocacaoDTO().setPontos_fidelidade(transacao.getLocacao().getPontos_fidelidade());

        result.setDt_pagamento(transacao.getDtPagamento());
        result.setForma_pagamento(transacao.getFormaPagamento());
        result.setValor_total(transacao.getValor_total());
        result.setStatus(transacao.getStatus());
        result.setNum_nota_fiscal(transacao.getNumNotaFiscal());
        result.setCod_transacao(transacao.getCodTransacao());

        return result;
    }

    public List<TransacaoDTO> getALL(){
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }


    public String delete(String codLocacao){
        return "Não é possível deletetar um registro de transação";
    }




    // Gerar um número aleatório único para código nota fiscal
    private int generateNumFiscal(){
        Random random = new Random();
        int num = random.nextInt(Integer.MAX_VALUE);
        while (repository.findByNumNotaFiscal(num) != null){
            num = random.nextInt(Integer.MAX_VALUE);
        }
        return num;
    }


    //gerar um código único de locação com string e int
    private String generateCod(){
        Random random = new Random();
        String letras= "abcdefghijklmnopqrstuvwxyz";
        StringBuilder str = new StringBuilder();

        for (int i=0; i<3; i++){
            int indice = random.nextInt(letras.length());
            char letra = letras.charAt(indice);
            str.append(letra);
        }

        String codString = str.toString();
        int cod = random.nextInt(Integer.MAX_VALUE);

        while (repository.findByCodTransacao(codString + String.valueOf(cod)) != null){

            for (int i=0; i<3; i++){
                int indice = random.nextInt(letras.length());
                char letra = letras.charAt(indice);
                str.append(letra);
            }

            codString = str.toString();
            cod = random.nextInt(Integer.MAX_VALUE);
        }

        return codString + String.valueOf(cod);
    }

}
