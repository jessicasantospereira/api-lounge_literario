package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.dto.ResponseTrocaDTO;
import com.fatec.les.loungeliterarioapi.dto.TrocaDTO;
import com.fatec.les.loungeliterarioapi.model.CupomTroca;
import com.fatec.les.loungeliterarioapi.model.SolicitacaoTroca;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrocaService {

    SolicitacaoTroca solicitarTroca(TrocaDTO trocaDTO);

    ResponseEntity<CupomTroca> buscarCupom(String codigo);

    ResponseEntity<List<ResponseTrocaDTO>> buscarTrocasPorCliente(String id);

    ResponseEntity<List<SolicitacaoTroca>> buscarTrocas();

    TrocaDTO atualizarTroca(Long id, String status);
}
