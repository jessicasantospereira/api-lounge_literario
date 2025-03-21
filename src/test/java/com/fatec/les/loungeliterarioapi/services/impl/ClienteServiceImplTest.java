package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteServiceImplTest {

    @Autowired
    private ClienteServiceImpl clienteService;

    @Test
    @DisplayName("Deve salvar um cliente")
    void salvarCliente() {
         var clienteDTO = Utils.criarClienteDTO();
         var result = clienteService.salvarCliente(clienteDTO);
         System.out.println(result);
         assertNotNull(result);
         assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    @DisplayName("Deve buscar um cliente por id")
    void buscarPorIdDoCliente() {
        var result = clienteService.buscarPorIdDoCliente(1L);
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
    @SqlGroup({
        @Sql(value = "classpath:db/cliente/insert-cliente.sql",
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "classpath:db/cliente/delete-cliente.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
    })
    void getItens() {
        var result = clienteService.getItens();
        assertEquals(3, result);
    }
    // TODO: Fazer novo teste após refatorar classe ClienteServiceImpl
//    @Test
//    @DisplayName("Deve deletar um cliente")
//    void deletarCliente() {
//        var cliente = clienteService.buscarPorIdDoCliente(1L);
//        clienteService.deletarCliente(cliente);
//        var result = clienteService.buscarPorIdDoCliente(1L);
//        System.out.println(result);
//    }

}