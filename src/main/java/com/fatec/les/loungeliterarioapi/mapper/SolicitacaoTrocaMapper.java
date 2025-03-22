package com.fatec.les.loungeliterarioapi.mapper;

import com.fatec.les.loungeliterarioapi.dto.TrocaDTO;
import com.fatec.les.loungeliterarioapi.model.SolicitacaoTroca;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SolicitacaoTrocaMapper {

    SolicitacaoTroca toEntity(TrocaDTO trocaDto);

    TrocaDTO toDto(SolicitacaoTroca troca);

}
