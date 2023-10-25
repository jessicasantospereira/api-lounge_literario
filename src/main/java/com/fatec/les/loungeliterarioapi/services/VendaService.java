package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.dto.VendaDTO;
import com.fatec.les.loungeliterarioapi.model.Venda;

import java.util.List;

public interface VendaService {

    Venda salvarVenda(VendaDTO venda);

    long getItens();

    List<Venda> listarVendasPorCliente(Long id);

    List<Venda> buscarTodasVendas();
}
