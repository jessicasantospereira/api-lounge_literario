package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.ResponseVendaDTO;
import com.fatec.les.loungeliterarioapi.dto.VendaDTO;
import com.fatec.les.loungeliterarioapi.model.Produto;
import com.fatec.les.loungeliterarioapi.model.StatusVenda;
import com.fatec.les.loungeliterarioapi.model.Venda;
import com.fatec.les.loungeliterarioapi.repository.ItemVendaRepository;
import com.fatec.les.loungeliterarioapi.repository.ProdutoRepository;
import com.fatec.les.loungeliterarioapi.services.VendaService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final VendaService service;
    private final ItemVendaRepository itemVendaRepository;
    private final ProdutoRepository produtoRepository;

    public VendasController(VendaService service, ItemVendaRepository itemVendaRepository, ProdutoRepository produtoRepository) {
        this.service = service;
        this.itemVendaRepository = itemVendaRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping(consumes = "application/json")
    @Transactional
    public ResponseEntity<?> realizarVenda(@RequestBody VendaDTO venda){
        Venda novaVenda = service.salvarVenda(venda);

        venda.getItens().forEach(iv -> {
            iv.setVenda(novaVenda);
            Optional<Produto> produtoExistente = produtoRepository.findById(iv.getProduto().getId());
            Produto prod = produtoExistente.get();
            int qtde = prod.getQtdeEstoque() - iv.getQuantidade();
            prod.setQtdeEstoque(qtde);
            produtoRepository.save(prod);
        });

        itemVendaRepository.saveAll(novaVenda.getItens());
        return new ResponseEntity<>(novaVenda.getCupomTroca(), null, HttpStatus.CREATED);

    }
    @GetMapping
    public Page<ResponseVendaDTO> getVendas(Pageable pageable){
        log.info("Listar todas as vendas");
        return service.buscarTodasVendas(pageable);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarVenda(@PathVariable Long id, @RequestBody String status){
        ResponseVendaDTO venda = service.atualizarVenda(id, status);

        return new ResponseEntity<>(venda, null, HttpStatus.CREATED);
    }

}
