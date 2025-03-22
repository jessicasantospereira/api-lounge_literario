package com.fatec.les.loungeliterarioapi.mapper;

import com.fatec.les.loungeliterarioapi.Utils;
import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnderecoMapperTest {

    @Autowired
    EnderecoMapper enderecoMapper;

    @Test
    @DisplayName("Deve converter para Entity")
    void deveConverterParaEntity() {
        EnderecoDTO endereco = Utils.criarEnderecoDTO();
        Endereco enderecoEntity = enderecoMapper.toEntity(endereco);
        assertNotNull(enderecoEntity);
        assertEquals(endereco.getLogradouro(), enderecoEntity.getLogradouro());
    }

    @Test
    @DisplayName("Deve converter para DTO")
    void deveConverterParaDTO() {
        Endereco endereco = Utils.criarEndereco();
        EnderecoDTO enderecoDTO = enderecoMapper.toDto(endereco);
        assertNotNull(enderecoDTO);
        assertEquals(endereco.getLogradouro(), enderecoDTO.getLogradouro());
    }

    @Test
    @DisplayName("Deve retornar null ao converter para DTO")
    void deveRetornarNullAoConverterParaDTO() {
        EnderecoDTO enderecoDTO = enderecoMapper.toDto(null);
        assertNull(enderecoDTO);
    }

}