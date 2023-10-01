package com.fatec.les.loungeliterarioapi.mapper.impl;

import com.fatec.les.loungeliterarioapi.dto.VendaDTO;
import com.fatec.les.loungeliterarioapi.mapper.VendaMapper;
import com.fatec.les.loungeliterarioapi.model.Venda;
import org.springframework.stereotype.Component;

@Component
public class VendaMapperImpl implements VendaMapper {
    @Override
    public Venda toEntity(VendaDTO vendaDto) {
        Venda venda = new Venda();
        venda.setCliente(vendaDto.getCliente());
        venda.setItens(vendaDto.getItens());
        venda.setCartaoDeCredito(vendaDto.getCartaoDeCredito());
        venda.setTemCupom(vendaDto.getTemCupom());
        venda.setTotal(vendaDto.getTotal());

        return venda;
    }

    @Override
    public VendaDTO toDto(Venda venda) {
        return null;
    }
}
