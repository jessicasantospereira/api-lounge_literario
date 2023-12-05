package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.TrocaDTO;
import com.fatec.les.loungeliterarioapi.mapper.SolicitacaoTrocaMapper;
import com.fatec.les.loungeliterarioapi.model.SolicitacaoTroca;
import com.fatec.les.loungeliterarioapi.repository.ClienteRepository;
import com.fatec.les.loungeliterarioapi.repository.ProdutoRepository;
import com.fatec.les.loungeliterarioapi.repository.SolicitacaoTrocaRepository;
import com.fatec.les.loungeliterarioapi.services.TrocaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrocaServiceImpl implements TrocaService {

    private final SolicitacaoTrocaRepository repository;
    private final SolicitacaoTrocaMapper mapper;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    @Override
    public SolicitacaoTroca solicitarTroca(TrocaDTO trocaDTO) {
        log.info("SERVICE -> Solicitar troca {}: ", trocaDTO);
        SolicitacaoTroca solicitacaoTroca = mapper.toEntity(trocaDTO);
        solicitacaoTroca.setCliente(clienteRepository.findById(Long.valueOf(trocaDTO.getIdCliente())).get());
        solicitacaoTroca.setProduto(produtoRepository.findById(Long.valueOf(trocaDTO.getIdProduto())).get());
        return repository.save(solicitacaoTroca);
    }
}
