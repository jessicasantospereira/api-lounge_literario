package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;
import com.fatec.les.loungeliterarioapi.repository.CartaoRepository;
import com.fatec.les.loungeliterarioapi.services.CartaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartaoServiceImpl implements CartaoService {

    private CartaoRepository repository;
    public CartaoServiceImpl(CartaoRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<CartaoDeCredito> buscarCartaoPorIdCliente(Long id) {
        log.info("Buscando cartão por id do cliente");
        List<CartaoDeCredito> cartoes = this.repository.findByCliente(id).get();
        return cartoes;
    }
}
