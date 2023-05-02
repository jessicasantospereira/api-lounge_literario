package com.fatec.les.loungeliterarioapi.mapper;

import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.model.Cliente;

public interface ClienteMapper {
    public Cliente toEntity(ClienteDTO clienteDto);

    public ClienteDTO toDto(Cliente cliente);
}
