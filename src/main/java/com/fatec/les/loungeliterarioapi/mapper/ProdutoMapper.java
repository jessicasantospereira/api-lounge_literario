package com.fatec.les.loungeliterarioapi.mapper;

import com.fatec.les.loungeliterarioapi.dto.ProdutoDTO;
import com.fatec.les.loungeliterarioapi.model.Produto;

public interface ProdutoMapper {

    public Produto toEntity(ProdutoDTO produtoDto);

    public ProdutoDTO toDto(Produto produto);
}
