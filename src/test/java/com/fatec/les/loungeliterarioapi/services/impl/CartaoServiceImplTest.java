package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.CartaoDeCreditoDTO;
import com.fatec.les.loungeliterarioapi.mapper.CartaoDeCreditoMapper;
import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.repository.CartaoRepository;
import com.fatec.les.loungeliterarioapi.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CartaoServiceImplTest {

    @InjectMocks
    CartaoServiceImpl cartaoService;

    @Mock
    ClienteService clienteService;

    @Mock
    CartaoRepository repository;

    @Mock
    CartaoDeCreditoMapper cartaoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve buscar cartões por id do cliente")
    void buscarCartaoPorIdCliente() {
        List<CartaoDeCredito> cartaoRetorno = List.of(new CartaoDeCredito());
        when(repository.findByCliente(1L)).thenReturn(Optional.of(cartaoRetorno));
        List<CartaoDeCredito> cartoes = cartaoService.buscarCartaoPorIdCliente(1L);
        assertNotNull(cartoes);
    }

    @Test
    @DisplayName("Deve deletar cartão")
    void deletarCartao() {
        assertDoesNotThrow(() -> cartaoService.deletarCartao(1L));
    }

    @Test
    @DisplayName("Deve salvar cartão")
    void salvarCartao() {
        CartaoDeCredito cartao = new CartaoDeCredito();
        when(clienteService.buscarPorIdDoCliente(1L)).thenReturn(new Cliente());
        when(cartaoMapper.toEntity(any())).thenReturn(cartao);
        when(repository.findByClienteAndPrincipal(new Cliente(), true)).thenReturn(new CartaoDeCredito());
        when(repository.save(new CartaoDeCredito())).thenReturn(new CartaoDeCredito());
        assertDoesNotThrow(() -> cartaoService.salvarCartao(new CartaoDeCreditoDTO()));
    }

    @Test
    @DisplayName("Deve atualizar cartão")
    void atualizarCartao() {
        CartaoDeCredito cartao = new CartaoDeCredito();
        cartao.setIdCartao(1L);
        when(clienteService.buscarPorIdDoCliente(1L)).thenReturn(new Cliente());
        when(cartaoMapper.toEntity(any())).thenReturn(cartao);
        when(repository.findByClienteAndPrincipal(new Cliente(), true)).thenReturn(new CartaoDeCredito());
        when(repository.save(new CartaoDeCredito())).thenReturn(new CartaoDeCredito());
        assertDoesNotThrow(() -> cartaoService.salvarCartao(criarCartaoDTO()));
    }

    @Test
    @DisplayName("Deve buscar cartão por id")
    void buscarCartaoPorId() {
        Long idCartao = 1L;
        CartaoDeCredito cartao = new CartaoDeCredito();
        CartaoDeCreditoDTO cartaoDTO = new CartaoDeCreditoDTO();

        when(repository.findByIdCartao(idCartao)).thenReturn(Optional.of(cartao));
        when(cartaoMapper.toDto(cartao)).thenReturn(cartaoDTO);

        CartaoDeCreditoDTO response = cartaoService.buscarCartaoPorId(idCartao);

        assertNotNull(response);
        assertEquals(cartaoDTO, response);
    }

    private CartaoDeCreditoDTO criarCartaoDTO(){
        CartaoDeCreditoDTO cartaoDTO = new CartaoDeCreditoDTO();
        cartaoDTO.setIdCartao(1L);
        cartaoDTO.setIdCliente(1L);
        cartaoDTO.setNumero("1234567890123456");
        cartaoDTO.setNome("Nome do Cliente");
        cartaoDTO.setValidade("12/2023");
        cartaoDTO.setCvv("123");
        cartaoDTO.setPrincipal(true);
        return cartaoDTO;
    }
}