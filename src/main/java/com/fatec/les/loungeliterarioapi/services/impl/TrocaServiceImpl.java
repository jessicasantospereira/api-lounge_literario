package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.ResponseVendaDTO;
import com.fatec.les.loungeliterarioapi.dto.TrocaDTO;
import com.fatec.les.loungeliterarioapi.mapper.SolicitacaoTrocaMapper;
import com.fatec.les.loungeliterarioapi.model.*;
import com.fatec.les.loungeliterarioapi.repository.ClienteRepository;
import com.fatec.les.loungeliterarioapi.repository.CupomTrocaRepository;
import com.fatec.les.loungeliterarioapi.repository.ProdutoRepository;
import com.fatec.les.loungeliterarioapi.repository.SolicitacaoTrocaRepository;
import com.fatec.les.loungeliterarioapi.services.TrocaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
public class TrocaServiceImpl implements TrocaService {

    private final SolicitacaoTrocaRepository repository;
    private final SolicitacaoTrocaMapper mapper;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final CupomTrocaRepository cupomTrocaRepository;
    @Override
    public SolicitacaoTroca solicitarTroca(TrocaDTO trocaDTO) {
        log.info("SERVICE -> Solicitar troca {}: ", trocaDTO);
        SolicitacaoTroca solicitacaoTroca = mapper.toEntity(trocaDTO);
        solicitacaoTroca.setCliente(clienteRepository.findById(Long.valueOf(trocaDTO.getIdCliente())).get());
        solicitacaoTroca.setProduto(produtoRepository.findById(Long.valueOf(trocaDTO.getIdProduto())).get());
        return repository.save(solicitacaoTroca);
    }

    @Override
    public ResponseEntity<?> buscarCupom(String codigo) {
//        Cupom c1 = repository.findByCodigo(codigo);
//        LocalDate dataAtual = LocalDate.now();
//        if(c1 == null){
//            return new ResponseEntity<String>("Cupom não encontrado", null,  HttpStatus.FORBIDDEN);
//        }
//        if(dataAtual.isAfter(c1.getDataValidade())){
//            return new ResponseEntity<Cupom>(c1, null,  HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<Cupom>(c1, null,  HttpStatus.OK);
        return null;
    }

    @Override
    public ResponseEntity<?> buscarTrocasPorCliente(String id) {
        List<SolicitacaoTroca> trocas = repository.findAllByIdCliente(Long.valueOf(id));
        if(trocas.isEmpty()){
            return new ResponseEntity<String>("Nenhuma troca encontrada", null,  HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<List<SolicitacaoTroca>>(trocas, null,  HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> buscarTrocas() {

        return new ResponseEntity<List<SolicitacaoTroca>>(repository.findAll(), null,  HttpStatus.OK);
    }
    @Override
    public TrocaDTO atualizarTroca(Long id, String status) {
        SolicitacaoTroca troca = repository.findById(id).get();
        if(Objects.equals(status, StatusSolicitacaoTroca.TROCA_AUTORIZADA.toString())){
            cupomTrocaRepository.save(CupomTroca.builder()
                    .codigo("CUPOM000" + id)
                    .solicitacaoTroca(troca)
                    .valor(troca.getValor())
                    .utilizado(false)
                    .dataValidade(troca.getDataSolicitacao().plusDays(30))
                    .build());
        }
        troca.setStatusSolicitacao(StatusSolicitacaoTroca.getStatusSolicitacaoTroca(status));
        repository.save(troca);
        return TrocaDTO.builder()
                .idSolicitacao(troca.getIdSolicitacao())
                .idCliente(Math.toIntExact(troca.getCliente().getIdCliente()))
                .idProduto(Math.toIntExact(troca.getProduto().getId()))
                .statusSolicitacao(troca.getStatusSolicitacao())
                .dataSolicitacao(troca.getDataSolicitacao())
                .motivo(troca.getMotivo())
                .build();
    }
}
