package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.CartaoDeCreditoDTO;
import com.fatec.les.loungeliterarioapi.mapper.CartaoDeCreditoMapper;
import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.repository.CartaoRepository;
import com.fatec.les.loungeliterarioapi.services.CartaoService;
import com.fatec.les.loungeliterarioapi.services.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartaoServiceImpl implements CartaoService {
    private final ClienteService clienteService;
    private final CartaoRepository repository;

    private final CartaoDeCreditoMapper cartaoMapper;
    public CartaoServiceImpl(CartaoRepository repository, ClienteService clienteService, CartaoDeCreditoMapper cartaoMapper) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.cartaoMapper = cartaoMapper;
    }
    @Override
    public List<CartaoDeCredito> buscarCartaoPorIdCliente(Long id) {
        log.info("Buscando cartão por id do cliente");
        List<CartaoDeCredito> cartoes = this.repository.findByCliente(id).get();
        return cartoes;
    }

    @Override
    public void deletarCartao(Long idCartao) {
        log.info("Deletando cartão");
        this.repository.deleteByIdCartao(idCartao);
    }

    @Override
    public ResponseEntity<?> salvarCartao(CartaoDeCreditoDTO cartao) {
        log.info("Salvando cartão de crédito");
        Cliente c1 = clienteService.buscarPorIdDoCliente(cartao.getIdCliente());
        CartaoDeCredito cartaoDeCredito = cartaoMapper.toEntity(cartao);
        cartaoDeCredito.setCliente(c1);
        CartaoDeCredito cartaoPrincipal = repository.findByClienteAndPrincipal(c1, true);
        log.info("Cartão principal: {}", cartaoPrincipal.isPrincipal());
        // Se o cartão já existir, atualize-o

        if(cartao.getIdCartao() != null){
            cartaoDeCredito.setIdCartao(cartao.getIdCartao());
            // Se o cartão existente for o principal, desmarque-o
            if (cartaoPrincipal != null) {
                log.info("Auiiiiii");
                cartaoPrincipal.setPrincipal(false);
                repository.save(cartaoPrincipal);
            }
            CartaoDeCredito existente = repository.save(cartaoDeCredito);
            return new ResponseEntity<CartaoDeCreditoDTO>(cartaoMapper.toDto(existente), HttpStatus.CREATED);
        }
        // Se já houver um cartão marcado como principal, desmarque-o
        if (cartaoPrincipal != null) {
            cartaoPrincipal.setPrincipal(false);
            repository.save(cartaoPrincipal);
        }
        return new ResponseEntity<>(cartaoMapper.toDto(this.repository.save(cartaoDeCredito)) , HttpStatus.CREATED);
    }

    @Override
    public CartaoDeCreditoDTO buscarCartaoPorId(Long idCartao) {
        CartaoDeCredito cartao = repository.findByIdCartao(idCartao).get();
        return cartaoMapper.toDto(cartao);
    }
}
