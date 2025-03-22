package com.fatec.les.loungeliterarioapi.mapper.impl;

import com.fatec.les.loungeliterarioapi.Utils;
import com.fatec.les.loungeliterarioapi.dto.ProdutoDTO;
import com.fatec.les.loungeliterarioapi.mapper.ProdutoMapper;
import com.fatec.les.loungeliterarioapi.model.Produto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProdutoMapperImplTest {

    @Autowired
    private ProdutoMapper produtoMapper;

    @Test
    @DisplayName("Deve converter para Entity")
    void deveConverterParaEntity() {
         ProdutoDTO produto = Utils.criarProdutoDTO();
         Produto produtoEntity = produtoMapper.toEntity(produto);
         assertNotNull(produtoEntity);
         assertEquals(produto.getTitulo(), produtoEntity.getTitulo());
    }

    @Test
    @DisplayName("Deve converter para DTO")
    void deveConverterParaDTO() {
         Produto produto = Utils.criarProduto();
         ProdutoDTO produtoDTO = produtoMapper.toDto(produto);
         assertNotNull(produtoDTO);
         assertEquals(produto.getTitulo(), produtoDTO.getTitulo());
    }

    @Test
    @DisplayName("Deve retornar null ao converter para DTO")
    void deveRetornarNullAoConverterParaDTO() {
         ProdutoDTO produtoDTO = produtoMapper.toDto(null);
         assertNull(produtoDTO);
    }

}