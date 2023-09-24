package com.fatec.les.loungeliterarioapi.mapper.impl;

import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.mapper.ClienteMapper;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Genero;
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
        cliente.setAtivo(clienteDto.isAtivo());
        cliente.setEndereco(clienteDto.getEndereco());
        cliente.setGenero(Genero.valueOf(clienteDto.getGenero()));
        cliente.setSenha(clienteDto.getSenha());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefone(clienteDto.getTelefone());
        cliente.setDdd(clienteDto.getDdd());
        cliente.setCodigo(clienteDto.getCodigo());
        cliente.setIdCliente(clienteDto.getIdCliente());
        cliente.setCartaoDeCredito(clienteDto.getCartaoDeCredito());

        return cliente;
    }

    @Override
    public ClienteDTO toDto(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        ClienteDTO dto = new ClienteDTO();

        dto.setIdCliente(cliente.getIdCliente());

        dto.setNome(cliente.getNome());
        dto.setCpf(cliente.getCpf());
        dto.setDataNascimento(cliente.getDataNascimento());
        dto.setDataCadastro(cliente.getDataCadastro());
        dto.setIdCliente(cliente.getIdCliente());
        dto.setEndereco(cliente.getEndereco());
        dto.setGenero(cliente.getGenero().toString());
        dto.setSenha(cliente.getSenha());
        dto.setEmail(cliente.getEmail());
        dto.setTelefone(cliente.getTelefone());
        dto.setDdd(cliente.getDdd());
        dto.setCodigo(cliente.getCodigo());

        dto.setAtivo(cliente.isAtivo());
        dto.setCartaoDeCredito(cliente.getCartaoDeCredito());
        return dto;
    }
}
