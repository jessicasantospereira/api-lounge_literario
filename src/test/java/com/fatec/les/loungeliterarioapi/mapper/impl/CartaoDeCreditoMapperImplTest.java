package com.fatec.les.loungeliterarioapi.mapper.impl;

import com.fatec.les.loungeliterarioapi.dto.CartaoDeCreditoDTO;
import com.fatec.les.loungeliterarioapi.mapper.CartaoDeCreditoMapper;
import com.fatec.les.loungeliterarioapi.model.Bandeiras;
import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartaoDeCreditoMapperImplTest {

    private final CartaoDeCreditoMapper cartaoDeCreditoMapper;

    public CartaoDeCreditoMapperImplTest() {
        this.cartaoDeCreditoMapper = new CartaoDeCreditoMapperImpl();
    }

    @Test
    @DisplayName("Deve converter para Entity")
    public void deveConverterParaEntity() {
        CartaoDeCreditoDTO cartao = cartaoCreditoDTO();
        CartaoDeCredito cartaoDeCredito = cartaoDeCreditoMapper.toEntity(cartao);
        assertNotNull(cartaoDeCredito);
        assertEquals(cartao.getNome(), cartaoDeCredito.getNome());
    }

    @Test
    @DisplayName("Deve converter para DTO")
    public void deveConverterParaDTO() {
        CartaoDeCredito cartao = cartaoCredito();
        CartaoDeCreditoDTO cartaoDeCreditoDTO = cartaoDeCreditoMapper.toDto(cartao);
        assertNotNull(cartaoDeCreditoDTO);
        assertEquals(cartao.getNome(), cartaoDeCreditoDTO.getNome());
    }

    @Test
    @DisplayName("Deve retornar null ao converter para DTO")
    public void deveRetornarNullAoConverterParaDTO() {
        CartaoDeCreditoDTO cartaoDeCreditoDTO = cartaoDeCreditoMapper.toDto(null);
        assertNull(cartaoDeCreditoDTO);
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

    private CartaoDeCredito cartaoCredito() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNome("Joao da Silva");
        CartaoDeCredito cartao = new CartaoDeCredito();
        cartao.setNome("Cartao Teste");
        cartao.setNumero("1234567891234567");
        cartao.setCvv("123");
        cartao.setValidade("12/2022");
        cartao.setBandeira(Bandeiras.DINERS);
        cartao.setPrincipal(true);
        cartao.setCliente(cliente);
        return cartao;
    }

}