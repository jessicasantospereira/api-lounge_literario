package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.ProdutoDTO;
import com.fatec.les.loungeliterarioapi.mapper.ProdutoMapper;
import com.fatec.les.loungeliterarioapi.model.Produto;
import com.fatec.les.loungeliterarioapi.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class ProdutoServiceImplTest {

    @InjectMocks
    ProdutoServiceImpl produtoService;

    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    ProdutoMapper produtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve salvar um produto")
    void salvarProduto() {
        ProdutoDTO produtoDTO = ProdutoDTO.builder().titulo("Novo Livro").build();
        when(produtoRepository.save(any())).thenReturn(new Produto());
        when(produtoMapper.toDto(any())).thenReturn(produtoDTO);
        ProdutoDTO produtoSalvo = produtoService.salvarProduto(produtoDTO);
        assertNotNull(produtoSalvo);
        assertEquals(produtoDTO.getTitulo(), produtoSalvo.getTitulo());
    }

    @Test
    @DisplayName("Deve buscar todos os produtos")
    void buscarTodos() {
        Produto produto = new Produto();
        ProdutoDTO produtoDTO = new ProdutoDTO();
        when(produtoRepository.findAll()).thenReturn(Collections.singletonList(produto));
        when(produtoMapper.toDto(produto)).thenReturn(produtoDTO);
        List<ProdutoDTO> response = produtoService.buscarTodos();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(produtoDTO, response.get(0));
    }

    @Test
    @DisplayName("Deve buscar um produto por id")
    void buscarPorId() {
        Produto produto = new Produto();
        ProdutoDTO produtoDTO = new ProdutoDTO();
        when(produtoRepository.findById(any())).thenReturn(java.util.Optional.of(produto));
        when(produtoMapper.toDto(produto)).thenReturn(produtoDTO);
        var response = produtoService.buscarPorId(1L);
        assertNotNull(response);
        assertEquals(produtoDTO, response.getBody());
    }

    @Test
    @DisplayName("Deve retornar a quantidade de itens")
    void getItens() {
        when(produtoRepository.count()).thenReturn(1L);
        long response = produtoService.getItens();
        assertEquals(1L, response);
    }
}