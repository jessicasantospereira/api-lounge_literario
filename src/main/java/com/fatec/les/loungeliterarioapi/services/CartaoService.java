package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;
import com.fatec.les.loungeliterarioapi.model.Endereco;

import java.util.List;

public interface CartaoService {
    List<CartaoDeCredito> buscarCartaoPorIdCliente(Long id);
}
