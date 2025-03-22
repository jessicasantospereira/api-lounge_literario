package com.fatec.les.loungeliterarioapi.mapper;

import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEntity(EnderecoDTO enderecoDto);

    EnderecoDTO toDto(Endereco endereco);

}
