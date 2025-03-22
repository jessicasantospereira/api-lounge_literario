package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.ProdutoResponseDTO;
import com.fatec.les.loungeliterarioapi.usecase.admin.ListarProdutosVendidosPorData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ListarProdutosVendidosPorData listarVendasPorData;

    @GetMapping("/vendas/por-data")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarVendasPorData(@RequestParam String dataInicial, @RequestParam String dataFinal){
        var result = listarVendasPorData.execute(dataInicial, dataFinal);
        return ResponseEntity.ok(result);
    }
}
