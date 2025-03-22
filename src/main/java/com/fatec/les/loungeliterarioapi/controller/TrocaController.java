package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.ResponseTrocaDTO;
import com.fatec.les.loungeliterarioapi.dto.TrocaDTO;
import com.fatec.les.loungeliterarioapi.model.CupomTroca;
import com.fatec.les.loungeliterarioapi.model.SolicitacaoTroca;
import com.fatec.les.loungeliterarioapi.services.TrocaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/trocas")
@RequiredArgsConstructor
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

    @GetMapping("/{codigo}")
    public ResponseEntity<CupomTroca> getCupons(@PathVariable("codigo") String codigo){
        log.info("Buscando cupom pelo código: {}", codigo);
        return service.buscarCupom(codigo);

    }
    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<ResponseTrocaDTO>> buscarTrocasPorCliente(@PathVariable("id") String id){
        return service.buscarTrocasPorCliente(id);
    }
    @GetMapping
    public ResponseEntity<List<SolicitacaoTroca>> getTrocas(){
        return service.buscarTrocas();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrocaDTO> editarTroca(@PathVariable Long id, @RequestBody String status){
        TrocaDTO venda = service.atualizarTroca(id, status);

        return new ResponseEntity<>(venda, null, HttpStatus.CREATED);
    }
}
