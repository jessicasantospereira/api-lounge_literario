package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.ProdutoDTO;
import com.fatec.les.loungeliterarioapi.model.Produto;
import com.fatec.les.loungeliterarioapi.model.Venda;
import com.fatec.les.loungeliterarioapi.repository.ItemVendaRepository;
import com.fatec.les.loungeliterarioapi.repository.ProdutoRepository;
import com.fatec.les.loungeliterarioapi.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/vendas")
@CrossOrigin("*")
public class VendasController {
    @Autowired
    private VendaRepository repository;
    @Autowired
    private ItemVendaRepository itemVendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public void realizarVenda(@RequestBody Venda venda){
        repository.save(venda);
        System.out.println(venda.getId());
        venda.getItens().stream().forEach(iv -> {
            iv.setVenda(venda);
            Optional<Produto> produtoExistente = produtoRepository.findById(iv.getProduto().getId());
            Produto prod = produtoExistente.get();
            int qtde = prod.getQtdeEstoque() - iv.getQuantidade();
            prod.setQtdeEstoque(qtde);
            produtoRepository.save(prod);
        });

        itemVendaRepository.saveAll(venda.getItens());
    }
}
