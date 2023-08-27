package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.ProdutoDTO;
import com.fatec.les.loungeliterarioapi.mapper.ProdutoMapper;
import com.fatec.les.loungeliterarioapi.model.Produto;
import com.fatec.les.loungeliterarioapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/produtos")
@CrossOrigin("*")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    private ProdutoMapper mapper;

    @PostMapping
    public ProdutoDTO salvar(@RequestBody ProdutoDTO produto){
        Produto entidadeProduto = mapper.toEntity(produto);
        repository.save(entidadeProduto);
        return mapper.toDto(entidadeProduto);
    }
    @GetMapping
    public List<ProdutoDTO> getLista(){
        Optional<List<Produto>> produtos = Optional.ofNullable(repository.findAll());
        if(produtos.isEmpty()){
            return null;
        }

        return produtos.get().stream().map(produto -> mapper.toDto(produto)).collect(Collectors.toList());
    }
    @GetMapping("{id}")
    public ResponseEntity<ProdutoDTO> getById(@PathVariable Long id){
        Optional<Produto> produtoExistente = repository.findById(id);
        if(produtoExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var produto = mapper.toDto(produtoExistente.get());
        return ResponseEntity.ok(produto);
    }
}
