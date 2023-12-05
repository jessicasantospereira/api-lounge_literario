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
        venda.setStatusVenda(vendaDto.getStatusVenda());
        venda.setTemTroca(vendaDto.getTemTroca());
        venda.setParcelas(vendaDto.getParcelas());
        venda.setValorParcela(vendaDto.getValorParcela());
        return venda;
    }

    @Override
    public VendaDTO toDto(Venda venda) {
        VendaDTO dto = new VendaDTO();
        dto.setCliente(venda.getCliente());
        dto.setItens(venda.getItens());
        dto.setCartaoDeCredito(venda.getCartaoDeCredito());
        dto.setTemCupom(venda.getTemCupom());
        dto.setTotal(venda.getTotal());
        dto.setStatusVenda(venda.getStatusVenda());
        dto.setTemTroca(venda.getTemTroca());
        dto.setCupomTroca(String.valueOf(venda.getCupomTroca()));
        dto.setParcelas(venda.getParcelas());
        dto.setValorParcela(venda.getValorParcela());
        return dto;
    }
}
