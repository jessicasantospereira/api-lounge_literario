package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.dto.ProdutoResponseDTO;
import com.fatec.les.loungeliterarioapi.dto.ResponseVendaDTO;
import com.fatec.les.loungeliterarioapi.dto.VendaDTO;
import com.fatec.les.loungeliterarioapi.model.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VendaService {

    Venda salvarVenda(VendaDTO venda);

    long getItens();

    List<Venda> listarVendasPorCliente(Long id);

    Page<ResponseVendaDTO> buscarTodasVendas(Pageable pageable);

    ResponseVendaDTO atualizarVenda(Long id, String status);

    List<ProdutoResponseDTO> buscarVendasPorData(String dataInicial, String dataFinal);
}
