package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.dto.TrocaDTO;
import com.fatec.les.loungeliterarioapi.model.SolicitacaoTroca;
import org.springframework.http.ResponseEntity;

public interface TrocaService {

    SolicitacaoTroca solicitarTroca(TrocaDTO trocaDTO);

    ResponseEntity<?> buscarCupom(String codigo);

    ResponseEntity<?> buscarTrocasPorCliente(String id);

    ResponseEntity<?> buscarTrocas();

    TrocaDTO atualizarTroca(Long id, String status);
}
