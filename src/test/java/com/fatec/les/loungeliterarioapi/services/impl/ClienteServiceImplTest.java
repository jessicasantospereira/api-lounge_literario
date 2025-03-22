package com.fatec.les.loungeliterarioapi.services.impl;

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
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS),
        @Sql(value = "classpath:db/cliente/delete-cliente.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS),
})
class ClienteServiceImplTest {

    @Autowired
    private ClienteServiceImpl clienteService;

    @Test
    @DisplayName("Deve buscar um cliente por id")
    void buscarPorIdDoCliente() {
        var result = clienteService.buscarPorIdDoCliente(10L);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deve buscar todos os clientes")
    void buscarTodos() {
        var result = clienteService.buscarTodos("", "", null);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deve retornar a quantidade de clientes")
    void getItens() {
        var result = clienteService.getItens();
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Deve deletar um cliente")
    void deletarCliente() {
        var cliente = clienteService.buscarPorIdDoCliente(11L);
        assertDoesNotThrow(() -> clienteService.deletarCliente(cliente));
    }

}