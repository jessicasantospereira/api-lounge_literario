package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.ProdutoResponseDTO;
import com.fatec.les.loungeliterarioapi.dto.VendaPorMesDTO;
import com.fatec.les.loungeliterarioapi.model.Admin;
import com.fatec.les.loungeliterarioapi.model.Venda;
import com.fatec.les.loungeliterarioapi.services.ClienteService;
import com.fatec.les.loungeliterarioapi.services.ProdutoService;
import com.fatec.les.loungeliterarioapi.services.VendaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AdminController {

    private final VendaService vendaService;

    private final ClienteService clienteService;

    private final ProdutoService produtoService;

    @GetMapping
    public Admin getItens(){
        long vendas = vendaService.getItens();
        long clientes = clienteService.getItens();
        long produtos = produtoService.getItens();
        return new Admin(produtos, clientes, vendas);
    }
    @GetMapping("/vendas/por-data")
    public ResponseEntity<?> buscarVendasPorData(@RequestParam String dataInicial, @RequestParam String dataFinal){
        log.info("Data inicial {}", dataInicial);
        log.info("Data final {}", dataFinal);
        List<ProdutoResponseDTO> vendas = vendaService.buscarVendasPorData(dataInicial, dataFinal);

        return ResponseEntity.ok(vendas);
    }
}
