package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.VendaPorMesDTO;
import com.fatec.les.loungeliterarioapi.model.Admin;
import com.fatec.les.loungeliterarioapi.model.Venda;
import com.fatec.les.loungeliterarioapi.services.ClienteService;
import com.fatec.les.loungeliterarioapi.services.ProdutoService;
import com.fatec.les.loungeliterarioapi.services.VendaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/vendas")
    public ResponseEntity<?> buscarVendasPorPeriodo(){
        List<VendaPorMesDTO> vendas = vendaService.buscarVendasPorPeriodo();

        return ResponseEntity.ok(vendas);
    }
}
