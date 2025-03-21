package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.Utils;
import com.fatec.les.loungeliterarioapi.model.StatusVenda;
import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SqlGroup({
        @Sql(value = "classpath:db/venda/insert-venda.sql",
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "classpath:db/venda/delete-venda.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
})
class VendaServiceImplTest {

    @Autowired
    private VendaServiceImpl vendaService;

//    @Test
//    @DisplayName("Deve salvar uma venda")
//    void salvarVenda() {
//        var vendaDTO = Utils.criarVendaDTO();
//        var result = vendaService.salvarVenda(vendaDTO);
//        assertNotNull(result);
//    }

    @Test
    @DisplayName("Deve retornar a quantidade de vendas")
    void getItens() {
        var result = vendaService.getItens();
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Deve listar as vendas por cliente")
    void listarVendasPorCliente() {
        var result = vendaService.listarVendasPorCliente(1L);
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Deve buscar todas as vendas")
    void buscarTodasVendas() {
        var pageable = PageRequest.of(0, 10);
        var result = vendaService.buscarTodasVendas(pageable);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    @DisplayName("Deve atualizar status de uma venda")
    void atualizarStatusVenda() {
        var result = vendaService.atualizarVenda(1L, "CANCELADO");
        assertEquals(StatusVenda.CANCELADO, result.getStatusVenda());
    }

}