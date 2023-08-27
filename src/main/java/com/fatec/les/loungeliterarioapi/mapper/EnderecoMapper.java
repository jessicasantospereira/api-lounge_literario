package com.fatec.les.loungeliterarioapi.mapper;

import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import com.fatec.les.loungeliterarioapi.model.Endereco;

public interface EnderecoMapper {
    public Endereco toEntity(EnderecoDTO enderecoDto);

    public EnderecoDTO toDto(Endereco endereco);
}
