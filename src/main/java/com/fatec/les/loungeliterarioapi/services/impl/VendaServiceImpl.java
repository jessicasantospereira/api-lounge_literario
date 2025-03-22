package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.*;
import com.fatec.les.loungeliterarioapi.mapper.VendaMapper;
import com.fatec.les.loungeliterarioapi.model.*;
import com.fatec.les.loungeliterarioapi.repository.*;

import java.time.LocalDate;
import java.util.*;

import com.fatec.les.loungeliterarioapi.services.VendaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class VendaServiceImpl implements VendaService {

    private final VendaRepository repository;
    private final VendaMapper mapper;
    private final CupomRepository cupomRepository;
    private final CupomTrocaRepository cupomTrocaRepository;
    private final SolicitacaoTrocaRepository solicitacaoTrocaRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    public Venda salvarVenda(VendaDTO venda) {
        log.info("Salvando venda {}", venda.getEnderecoEntrega());
        Venda novaVenda = mapper.toEntity(venda);

        if (venda.getTemCupom() != null && venda.getTemCupom()) {
            novaVenda.setCupom(cupomRepository.findByCodigo(venda.getCupom()));
        }
        if(venda.getTemTroca() != null && venda.getTemTroca()){
            log.info("Tem troca {}", venda.getCupomTroca());
            CupomTroca cupomTroca = cupomTrocaRepository.findByCodigo(venda.getCupomTroca());
            cupomTroca.setUtilizado(true);
            SolicitacaoTroca troca = solicitacaoTrocaRepository.findById(cupomTroca.getSolicitacaoTroca().getIdSolicitacao()).get();
            troca.setStatusSolicitacao(StatusSolicitacaoTroca.TROCA_EFETUADA);
            solicitacaoTrocaRepository.save(cupomTroca.getSolicitacaoTroca());
            cupomTrocaRepository.save(cupomTroca);
            novaVenda.setCupomTroca(cupomTroca);
        }

        novaVenda.setStatusVenda(StatusVenda.EM_PROCESSAMENTO);
        novaVenda.setDataVenda(LocalDate.now());
        novaVenda.setEnderecoEntrega(venda.getEnderecoEntrega());
        return repository.save(novaVenda);

    }
    @Override
    public long getItens() {
        return repository.count();
    }

    @Override
    public List<Venda> listarVendasPorCliente(Long id) {
       return repository.findAllByIdCliente(id).get();
    }

    @Override
    public Page<ResponseVendaDTO> buscarTodasVendas(Pageable pageable) {
        Page<Venda> vendas =  repository.findAll(pageable);
        return vendas.map(ResponseVendaDTO::new);
    }

    @Override
    public ResponseVendaDTO atualizarVenda(Long id, String status) {
       Venda venda = repository.findById(id).get();
       venda.setStatusVenda(StatusVenda.getStatusVenda(status));
       repository.save(venda);
        return new ResponseVendaDTO(venda);
    }

}
