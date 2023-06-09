package com.fatec.les.loungeliterarioapi.mapper.impl;

import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.mapper.ClienteMapper;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toEntity(ClienteDTO clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDto.getNome());
        cliente.setCpf(clienteDto.getCpf());
        cliente.setDataCadastro(clienteDto.getDataCadastro());
        cliente.setDataNascimento(clienteDto.getDataNascimento());

        return cliente;
    }

    @Override
    public ClienteDTO toDto(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        ClienteDTO dto = new ClienteDTO();
        dto.setNome(cliente.getNome());
        dto.setCpf(cliente.getCpf());
        dto.setDataNascimento(cliente.getDataNascimento());
        dto.setDataCadastro(cliente.getDataCadastro());
        dto.setIdCliente(cliente.getIdCliente());
        dto.setContato(cliente.getContato());
        dto.setEndereco(cliente.getEndereco());
        return dto;
    }
}
