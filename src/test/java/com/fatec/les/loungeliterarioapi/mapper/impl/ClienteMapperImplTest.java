package com.fatec.les.loungeliterarioapi.mapper.impl;

import com.fatec.les.loungeliterarioapi.Utils;
import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.mapper.ClienteMapper;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteMapperImplTest {

    @Autowired
    private ClienteMapper clienteMapper;

    @Test
    @DisplayName("Deve converter para Entity")
    void deveConverterParaEntity() {
        ClienteDTO cliente = Utils.criarClienteDTO();
        Cliente clienteEntity = clienteMapper.toEntity(cliente);
        assertNotNull(clienteEntity);
        assertEquals(cliente.getNome(), clienteEntity.getNome());
    }

    @Test
    @DisplayName("Deve converter para DTO")
    void deveConverterParaDTO() {
        Cliente cliente = Utils.criarCliente();
        ClienteDTO clienteDTO = clienteMapper.toDto(cliente);
        assertNotNull(clienteDTO);
        assertEquals(cliente.getNome(), clienteDTO.getNome());
    }

    @Test
    @DisplayName("Deve retornar null ao converter para DTO")
    void deveRetornarNullAoConverterParaDTO() {
        ClienteDTO clienteDTO = clienteMapper.toDto(null);
        assertNull(clienteDTO);
    }


// TODO: Implementar testes para os métodos updateFromDTO e updateFromEntity
//    @Test
//    @DisplayName("Deve atualizar a entidade com os dados do DTO")
//    public void deveAtualizarEntidadeComDadosDoDTO() {
//        Cliente cliente = Utils.criarCliente();
//        ClienteDTO clienteDTO = Utils.criarClienteDTO();
//        clienteMapper.updateFromDTO(cliente, clienteDTO);
//        assertEquals(clienteDTO.getNome(), cliente.getNome());
//    }

}