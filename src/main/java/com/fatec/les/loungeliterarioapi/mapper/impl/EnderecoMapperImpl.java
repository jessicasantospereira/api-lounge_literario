package com.fatec.les.loungeliterarioapi.mapper.impl;

import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import com.fatec.les.loungeliterarioapi.mapper.EnderecoMapper;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapperImpl implements EnderecoMapper {

    @Override
    public Endereco toEntity(EnderecoDTO enderecoDto) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDto.getLogradouro());
        endereco.setNumero(enderecoDto.getNumero());
        endereco.setComplemento(enderecoDto.getComplemento());
        endereco.setCep(enderecoDto.getCep());
        endereco.setBairro(enderecoDto.getBairro());
        endereco.setCidade(enderecoDto.getCidade());
        endereco.setUf(enderecoDto.getUf());
        endereco.setTipoEndereco(enderecoDto.getTipoEndereco());
        endereco.setEndCobranca(enderecoDto.isEndCobranca());
        endereco.setEndEntrega(enderecoDto.isEndEntrega());
        endereco.setNomeEndereco(enderecoDto.getNomeEndereco());

        return endereco;
    }

    @Override
    public EnderecoDTO toDto(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        EnderecoDTO dto = new EnderecoDTO();
        dto.setIdEndereco(endereco.getIdEndereco());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setCep(endereco.getCep());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setUf(endereco.getUf());
        dto.setTipoEndereco(endereco.getTipoEndereco());
        dto.setEndCobranca(endereco.isEndCobranca());
        dto.setEndEntrega(endereco.isEndEntrega());
        dto.setNomeEndereco(endereco.getNomeEndereco());

        return dto;
    }

}
