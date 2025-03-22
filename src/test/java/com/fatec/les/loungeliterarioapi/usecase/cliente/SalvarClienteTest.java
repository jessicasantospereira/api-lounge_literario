package com.fatec.les.loungeliterarioapi.usecase.cliente;

import com.fatec.les.loungeliterarioapi.Utils;
import com.fatec.les.loungeliterarioapi.exceptions.DomainException;
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
class SalvarClienteTest {

    @Autowired
    private SalvarCliente salvarCliente;

    @Test
    @DisplayName("Deve salvar um cliente")
    void deveSalvarUmCliente(){
        var clienteDTO = Utils.criarClienteDTO();
        clienteDTO.setIdCliente(null);
        var result = salvarCliente.execute(clienteDTO);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deve atualizar um cliente")
    void deveAtualizarUmCliente(){
        var clienteDTO = Utils.criarClienteDTO();
        clienteDTO.setIdCliente(10L);
        clienteDTO.setNome("Novo nome");
        var result = salvarCliente.execute(clienteDTO);
        assertNotNull(result);
        assertEquals("Novo nome", result.getNome());
    }

    @Test
    @DisplayName("Deve lançar uma exceção caso não encontre o cliente")
    void deveLancarUmaExcecao(){
        var clienteDTO = Utils.criarClienteDTO();
        clienteDTO.setIdCliente(25L);
        assertThrows(DomainException.class, () -> salvarCliente.execute(clienteDTO));
    }

}