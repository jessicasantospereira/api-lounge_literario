package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.dto.CartaoDeCreditoDTO;
import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartaoService {

    List<CartaoDeCredito> buscarCartaoPorIdCliente(Long id);

    void deletarCartao(Long idCartao);

    ResponseEntity<CartaoDeCreditoDTO> salvarCartao(CartaoDeCreditoDTO cartao);

    CartaoDeCreditoDTO buscarCartaoPorId(Long idCartao);

}
