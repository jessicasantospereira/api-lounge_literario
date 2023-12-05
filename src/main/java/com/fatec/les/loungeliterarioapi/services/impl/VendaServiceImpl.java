package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.ResponseVendaDTO;
import com.fatec.les.loungeliterarioapi.dto.VendaDTO;
import com.fatec.les.loungeliterarioapi.dto.VendaPorMesDTO;
import com.fatec.les.loungeliterarioapi.mapper.VendaMapper;
import com.fatec.les.loungeliterarioapi.model.*;
import com.fatec.les.loungeliterarioapi.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
    private final EnderecoRepository enderecoRepository;
    private final CupomTrocaRepository cupomTrocaRepository;
    private final SolicitacaoTrocaRepository solicitacaoTrocaRepository;

    @Override
    public Venda salvarVenda(VendaDTO venda) {
        log.info("Salvando venda {}", venda.getEnderecoEntrega());
        Venda novaVenda = mapper.toEntity(venda);
        UUID uuid = UUID.randomUUID();

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
        Endereco end = enderecoRepository.findByIdEndereco(venda.getEnderecoEntrega().getIdEndereco()).get();
        novaVenda.setStatusVenda(StatusVenda.EM_PROCESSAMENTO);
        novaVenda.setDataVenda(LocalDate.now());
        novaVenda.setEnderecoEntrega(end);
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
        Page<ResponseVendaDTO> responseDTO = vendas.map(venda -> new ResponseVendaDTO(venda));
        return responseDTO;
    }

    @Override
    public ResponseVendaDTO atualizarVenda(Long id, String status) {
       Venda venda = repository.findById(id).get();
       venda.setStatusVenda(StatusVenda.getStatusVenda(status));
       repository.save(venda);
       ResponseVendaDTO responseVendaDTO = new ResponseVendaDTO(venda);
       return responseVendaDTO;
    }

    @Override
    public List<VendaPorMesDTO> buscarVendasPorPeriodo() {
        return repository.findAllByDataVenda();
    }
}
