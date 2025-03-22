package com.fatec.les.loungeliterarioapi.mapper;

import com.fatec.les.loungeliterarioapi.dto.ProdutoDTO;
import com.fatec.les.loungeliterarioapi.model.Produto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto toEntity(ProdutoDTO produtoDto);

    ProdutoDTO toDto(Produto produto);

}
