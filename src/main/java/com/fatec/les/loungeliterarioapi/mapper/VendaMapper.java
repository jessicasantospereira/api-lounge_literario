package com.fatec.les.loungeliterarioapi.mapper;

import com.fatec.les.loungeliterarioapi.dto.VendaDTO;
import com.fatec.les.loungeliterarioapi.model.Venda;

public interface VendaMapper {

    public Venda toEntity(VendaDTO vendaDto);

    public VendaDTO toDto(Venda venda);
}