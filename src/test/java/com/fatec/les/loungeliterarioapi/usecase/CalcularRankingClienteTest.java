package com.fatec.les.loungeliterarioapi.usecase;

import com.fatec.les.loungeliterarioapi.Utils;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Venda;
import com.fatec.les.loungeliterarioapi.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CalcularRankingClienteTest {

    @InjectMocks
    private CalcularRankingCliente calcularRankingCliente;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

     @Test
     @DisplayName("Deve calcular o ranking do cliente")
     public void testCalcularRankingCliente() {
        Venda venda = Utils.criarVenda();
        Cliente cliente = calcularRankingCliente.execute(venda);
        assertEquals(60, cliente.getRanking());
        assertNotNull(cliente.getUltimaAtualizacao());
     }
}
