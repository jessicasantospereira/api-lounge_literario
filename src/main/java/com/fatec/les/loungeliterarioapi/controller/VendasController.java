package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.VendaDTO;
import com.fatec.les.loungeliterarioapi.model.Produto;
import com.fatec.les.loungeliterarioapi.model.Venda;
import com.fatec.les.loungeliterarioapi.repository.ItemVendaRepository;
import com.fatec.les.loungeliterarioapi.repository.ProdutoRepository;
import com.fatec.les.loungeliterarioapi.services.VendaService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/api/vendas")
@CrossOrigin("*")
public class VendasController {
    @Autowired
    private VendaService service;
    @Autowired
    private ItemVendaRepository itemVendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> realizarVenda(@RequestBody VendaDTO venda){
        Venda novaVenda = service.salvarVenda(venda);

        venda.getItens().stream().forEach(iv -> {
            iv.setVenda(novaVenda);
            Optional<Produto> produtoExistente = produtoRepository.findById(iv.getProduto().getId());
            Produto prod = produtoExistente.get();
            int qtde = prod.getQtdeEstoque() - iv.getQuantidade();
            prod.setQtdeEstoque(qtde);
            produtoRepository.save(prod);
        });

        itemVendaRepository.saveAll(novaVenda.getItens());
        return new ResponseEntity(novaVenda.getCupomTroca(), null, HttpStatus.CREATED);

    }
    @GetMapping("{id}")
    public List<Venda> getVendas(@PathVariable Long id){
        log.info("Listar vendas por idCliente {}", id);
        List<Venda> vendas = service.listarVendasPorCliente(id);
        for (Venda venda: vendas
             ) {
            log.info("Venda {}", venda);
        }

        return null;
    }

}
