package com.fatec.les.loungeliterarioapi.mapper;

import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    public Cliente toEntity(ClienteDTO clienteDto);

    public ClienteDTO toDto(Cliente cliente);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateFromDTO(@MappingTarget Cliente clienteExistente, Cliente novo);

}
