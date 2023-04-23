package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.ProdutoDTO;
import com.fatec.les.loungeliterarioapi.model.Produto;
import com.fatec.les.loungeliterarioapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/produtos")
@CrossOrigin("*")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    public ProdutoDTO salvar(@RequestBody ProdutoDTO produto){
        Produto entidadeProduto = produto.toModel();
        repository.save(entidadeProduto);
        return ProdutoDTO.fromModel(entidadeProduto);
    }
    @GetMapping
    public List<ProdutoDTO> getLista(){
        return repository.findAll().stream().map(ProdutoDTO::fromModel).collect(Collectors.toList());
    }
}
