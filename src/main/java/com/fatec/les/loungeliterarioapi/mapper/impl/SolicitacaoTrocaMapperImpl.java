package com.fatec.les.loungeliterarioapi.mapper.impl;

import com.fatec.les.loungeliterarioapi.dto.TrocaDTO;
import com.fatec.les.loungeliterarioapi.mapper.SolicitacaoTrocaMapper;
import com.fatec.les.loungeliterarioapi.model.SolicitacaoTroca;
import org.springframework.stereotype.Component;

@Component
public class SolicitacaoTrocaMapperImpl implements SolicitacaoTrocaMapper {

    @Override
    public SolicitacaoTroca toEntity(TrocaDTO trocaDto) {
        return SolicitacaoTroca.builder()
                .motivo(trocaDto.getMotivo())
                .statusSolicitacao(trocaDto.getStatusSolicitacao())
                .quantidade(trocaDto.getQuantidade())
                .valor(trocaDto.getValor())
                .build();
    }

    @Override
    public TrocaDTO toDto(SolicitacaoTroca troca) {
        return null;
    }

}
