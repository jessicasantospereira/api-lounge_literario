package com.fatec.les.loungeliterarioapi.mapper.impl;

import com.fatec.les.loungeliterarioapi.dto.CartaoDeCreditoDTO;
import com.fatec.les.loungeliterarioapi.mapper.CartaoDeCreditoMapper;
import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartaoDeCreditoMapperImplTest {

    private final CartaoDeCreditoMapper cartaoDeCreditoMapper;

    public CartaoDeCreditoMapperImplTest() {
        this.cartaoDeCreditoMapper = new CartaoDeCreditoMapperImpl();
    }

    @Test
    public void deveConverterParaEntity() {
        CartaoDeCreditoDTO cartao = cartaoCreditoDTO();
        CartaoDeCredito cartaoDeCredito = cartaoDeCreditoMapper.toEntity(cartao);
        assertNotNull(cartaoDeCredito);
        assertEquals(cartao.getNome(), cartaoDeCredito.getNome());
    }

    private CartaoDeCreditoDTO cartaoCreditoDTO() {
        CartaoDeCreditoDTO cartao = new CartaoDeCreditoDTO();
        cartao.setNome("Cartao Teste");
        cartao.setNumero("1234567891234567");
        cartao.setCvv("123");
        cartao.setValidade("12/2022");
        cartao.setBandeira("VISA");
        cartao.setPrincipal(true);
        return cartao;
    }

}