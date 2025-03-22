package com.fatec.les.loungeliterarioapi.mapper;

import com.fatec.les.loungeliterarioapi.dto.CartaoDeCreditoDTO;
import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartaoDeCreditoMapper {

    CartaoDeCredito toEntity(CartaoDeCreditoDTO cartaoDto);

    CartaoDeCreditoDTO toDto(CartaoDeCredito cartao);

}
