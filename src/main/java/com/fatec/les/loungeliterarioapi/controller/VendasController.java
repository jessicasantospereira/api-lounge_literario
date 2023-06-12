package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.VendaDTO;
import com.fatec.les.loungeliterarioapi.model.Produto;
import com.fatec.les.loungeliterarioapi.model.Venda;
import com.fatec.les.loungeliterarioapi.repository.ItemVendaRepository;
import com.fatec.les.loungeliterarioapi.repository.ProdutoRepository;
import com.fatec.les.loungeliterarioapi.services.VendaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public void realizarVenda(@RequestBody VendaDTO venda){
        System.out.println(venda.getCupom());
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

    }
}
