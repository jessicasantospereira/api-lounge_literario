package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SqlGroup({
        @Sql(value = "classpath:db/cliente/insert-cliente.sql",
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "classpath:db/cliente/delete-cliente.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
})
class EnderecoServiceImplTest {

    @Autowired
    private EnderecoServiceImpl enderecoService;

    @Test
    @DisplayName("Deve buscar endereço por id cliente")
    void buscarEnderecoPorIdCliente() {
        var result = enderecoService.buscarEnderecoPorIdCliente(10L);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Deve salvar endereço")
    void salvarEndereco() {
        var result = enderecoService.salvarEndereco(novoEndereco());
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deve buscar endereço por id")
    void buscarEnderecoPorId() {
        var result = enderecoService.buscarEnderecoPorId(16L);
        assertNotNull(result);
    }

    private EnderecoDTO novoEndereco(){
        return EnderecoDTO.builder()
                .idCliente(10L)
                .cep("00000-000")
                .logradouro("Rua Teste")
                .numero("123")
                .complemento("Casa")
                .bairro("Bairro Teste")
                .cidade("Cidade Teste")
                .uf("Estado Teste")
                .build();
    }

}