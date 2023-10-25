package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.model.Admin;
import com.fatec.les.loungeliterarioapi.repository.ProdutoRepository;
import com.fatec.les.loungeliterarioapi.services.impl.ClienteServiceImpl;
import com.fatec.les.loungeliterarioapi.services.impl.VendaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {
//    @Autowired
//    private VendaServiceImpl vendaService;
//    @Autowired
//    private ClienteServiceImpl clienteService;
//    @Autowired
//    private ProdutoRepository produtoRepository;

    @GetMapping
    public Admin getItens(){
//        long vendas = vendaService.getItens();
//        long clientes = clienteService.getItens();
//        long produtos = produtoRepository.count();

//        return new Admin(produtos, clientes, vendas);
        return null;
    }
}
