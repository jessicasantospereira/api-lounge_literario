package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.ProdutoDTO;
import com.fatec.les.loungeliterarioapi.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/produtos")
@CrossOrigin("*")
public class ProdutoController {
    private final ProdutoService service;
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }
    @PostMapping
    public ProdutoDTO salvar(@RequestBody ProdutoDTO produto){
        return service.salvarProduto(produto);
    }
    @GetMapping
    public List<ProdutoDTO> getLista(){
        return service.buscarTodos();
    }
    @GetMapping("{id}")
    public ResponseEntity<ProdutoDTO> getById(@PathVariable Long id){
        return service.buscarPorId(id);
    }
}
