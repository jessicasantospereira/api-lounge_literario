package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.dto.ProdutoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProdutoService {
    ProdutoDTO salvarProduto(ProdutoDTO produto);
    List<ProdutoDTO> buscarTodos();
    ResponseEntity<ProdutoDTO> buscarPorId(Long id);
    long getItens();
}
