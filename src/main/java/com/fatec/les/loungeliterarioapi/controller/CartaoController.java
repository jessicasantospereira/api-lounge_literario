package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.CartaoDeCreditoDTO;
import com.fatec.les.loungeliterarioapi.services.CartaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/clientes/cartao")
public class CartaoController {

    public final CartaoService service;

    public CartaoController(CartaoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoDeCreditoDTO> buscarCartao(@PathVariable("id") Long id){
        log.info("Buscar cartão cliente id: {} ", id);
        return ResponseEntity.ok(service.buscarCartaoPorId(id));
    }
    @PostMapping()
    public ResponseEntity<CartaoDeCreditoDTO> cadastrarCartao(@RequestBody CartaoDeCreditoDTO cartao){
        log.info("Cartao entrada {} ", cartao.toString());
        return service.salvarCartao(cartao);

    }
    @PutMapping("/{id}")
    public ResponseEntity<CartaoDeCreditoDTO> atualizarCartao(@PathVariable("id") Long id, @RequestBody CartaoDeCreditoDTO cartao){
        log.info("Cartao entrada {} ", cartao.toString());

        return service.salvarCartao(cartao);
    }
    @DeleteMapping("/{id}/{idCartao}")
    public ResponseEntity<HttpStatus> deletarCartao(@PathVariable("id") Long id, @PathVariable("idCartao") Long idCartao){
        log.info("Deletar cartão cliente id: {} e cartão id: {} ", id, idCartao);
        service.deletarCartao(idCartao);

        return ResponseEntity.ok().build();
    }
}
