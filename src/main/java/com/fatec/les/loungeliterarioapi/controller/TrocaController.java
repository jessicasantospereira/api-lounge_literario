package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.TrocaDTO;
import com.fatec.les.loungeliterarioapi.services.TrocaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/trocas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrocaController {
    private final TrocaService service;
    @PostMapping
    public void solicitarTroca(@RequestBody TrocaDTO troca){
        log.info("Solicitar troca {}: ", troca);

        Optional.ofNullable(troca.getIdCliente())
                .filter(id -> id != 0)
                .orElseThrow(() -> new RuntimeException("ID do Cliente não pode ser nulo ou zero"));
        Optional.ofNullable(troca.getIdProduto())
                .filter(id -> id != 0)
                .orElseThrow(() -> new RuntimeException("ID do Produto não pode ser nulo ou zero"));

        service.solicitarTroca(troca);
    }
}
