package com.fatec.les.loungeliterarioapi.mapper;

import com.fatec.les.loungeliterarioapi.dto.CartaoDeCreditoDTO;
import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;

public interface CartaoDeCreditoMapper {

    public CartaoDeCredito toEntity(CartaoDeCreditoDTO cartaoDto);

    public CartaoDeCreditoDTO toDto(CartaoDeCredito cartao);
}
