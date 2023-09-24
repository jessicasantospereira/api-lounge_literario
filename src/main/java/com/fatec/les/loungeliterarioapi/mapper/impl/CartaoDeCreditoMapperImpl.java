package com.fatec.les.loungeliterarioapi.mapper.impl;

import com.fatec.les.loungeliterarioapi.dto.CartaoDeCreditoDTO;
import com.fatec.les.loungeliterarioapi.mapper.CartaoDeCreditoMapper;
import com.fatec.les.loungeliterarioapi.model.Bandeiras;
import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;
import org.springframework.stereotype.Component;

@Component
public class CartaoDeCreditoMapperImpl implements CartaoDeCreditoMapper {
    @Override
    public CartaoDeCredito toEntity(CartaoDeCreditoDTO cartaoDto) {
        CartaoDeCredito cartao = new CartaoDeCredito();
        cartao.setNome(cartaoDto.getNome());
        cartao.setNumero(cartaoDto.getNumero());
        cartao.setCvv(cartaoDto.getCvv());
        cartao.setValidade(cartaoDto.getValidade());
        cartao.setBandeira(Bandeiras.valueOf(cartaoDto.getBandeira()));
        return cartao;
    }

    @Override
    public CartaoDeCreditoDTO toDto(CartaoDeCredito cartao) {
        if(cartao == null){
            return null;
        }
        CartaoDeCreditoDTO cartaoDto = new CartaoDeCreditoDTO();
        cartaoDto.setIdCartao(cartao.getIdCartao());
        cartaoDto.setIdCliente(cartao.getCliente().getIdCliente());
        cartaoDto.setNome(cartao.getNome());
        cartaoDto.setNumero(cartao.getNumero());
        cartaoDto.setCvv(cartao.getCvv());
        cartaoDto.setValidade(cartao.getValidade());
        cartaoDto.setBandeira(cartao.getBandeira().toString());


        return cartaoDto;
    }
}
