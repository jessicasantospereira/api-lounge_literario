package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.dto.TrocaDTO;
import com.fatec.les.loungeliterarioapi.model.SolicitacaoTroca;

public interface TrocaService {

    SolicitacaoTroca solicitarTroca(TrocaDTO trocaDTO);
}
