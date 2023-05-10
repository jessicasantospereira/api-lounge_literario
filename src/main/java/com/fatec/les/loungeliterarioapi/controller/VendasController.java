package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.model.Venda;
import com.fatec.les.loungeliterarioapi.repository.ItemVendaRepository;
import com.fatec.les.loungeliterarioapi.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendas")
@CrossOrigin("*")
public class VendasController {
    @Autowired
    private VendaRepository repository;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @PostMapping
    @Transactional
    public void realizarVenda(@RequestBody Venda venda){
        repository.save(venda);
        System.out.println(venda.getId());
        venda.getItens().stream().forEach(iv -> iv.setVenda(venda));
        itemVendaRepository.saveAll(venda.getItens());
    }
}
