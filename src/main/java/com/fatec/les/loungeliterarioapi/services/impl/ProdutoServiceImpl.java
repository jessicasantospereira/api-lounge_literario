package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.ProdutoDTO;
import com.fatec.les.loungeliterarioapi.mapper.ProdutoMapper;
import com.fatec.les.loungeliterarioapi.model.Produto;
import com.fatec.les.loungeliterarioapi.repository.ProdutoRepository;
import com.fatec.les.loungeliterarioapi.services.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    private final ProdutoMapper produtoMapper;

    public ProdutoServiceImpl(ProdutoRepository repository, ProdutoMapper produtoMapper) {
        this.repository = repository;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public ProdutoDTO salvarProduto(ProdutoDTO produto) {
            UUID uuid = UUID.randomUUID();
            produto.setCodigo(uuid.toString());
            log.info("Salvando produto {}", produto.getCodigo());
            return produtoMapper.toDto(repository.save(produtoMapper.toEntity(produto)));
    }

    @Override
    public List<ProdutoDTO> buscarTodos() {
            log.info("Buscando todos os produtos");
            Optional<List<Produto>> produtos = Optional.of(repository.findAll());
            return produtos.map(produtoList -> produtoList.stream().map(produtoMapper::toDto)
                    .toList()).orElse(null);

    }

    @Override
    public ResponseEntity<ProdutoDTO> buscarPorId(Long id) {
            log.info("Buscando produto {}", id);
            Optional<Produto> produto = repository.findById(id);
            return produto.map(value -> ResponseEntity.ok(produtoMapper.toDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Override
    public long getItens() {
        return repository.count();
    }
}
