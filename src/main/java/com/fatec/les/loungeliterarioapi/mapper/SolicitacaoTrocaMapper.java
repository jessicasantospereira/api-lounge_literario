package com.fatec.les.loungeliterarioapi.mapper;

import com.fatec.les.loungeliterarioapi.dto.TrocaDTO;
import com.fatec.les.loungeliterarioapi.model.SolicitacaoTroca;

public interface SolicitacaoTrocaMapper {
    public SolicitacaoTroca toEntity(TrocaDTO trocaDto);

    public TrocaDTO toDto(SolicitacaoTroca troca);
}
