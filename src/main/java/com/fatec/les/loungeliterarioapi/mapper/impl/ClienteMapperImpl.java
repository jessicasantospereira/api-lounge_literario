package com.fatec.les.loungeliterarioapi.mapper.impl;

import com.fatec.les.loungeliterarioapi.dto.CartaoDeCreditoDTO;
import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import com.fatec.les.loungeliterarioapi.mapper.CartaoDeCreditoMapper;
import com.fatec.les.loungeliterarioapi.mapper.ClienteMapper;
import com.fatec.les.loungeliterarioapi.mapper.EnderecoMapper;
import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.model.Genero;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteMapperImpl implements ClienteMapper {
    EnderecoMapper enderecoMapper;
    CartaoDeCreditoMapper cartaoMapper;
    @Override
    public Cliente toEntity(ClienteDTO clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDto.getNome());
        cliente.setCpf(clienteDto.getCpf());
        cliente.setDataCadastro(clienteDto.getDataCadastro());
        cliente.setDataNascimento(clienteDto.getDataNascimento());
        cliente.setAtivo(clienteDto.isAtivo());
        cliente.setGenero(Genero.valueOf(clienteDto.getGenero()));
        cliente.setSenha(clienteDto.getSenha());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setTelefone(clienteDto.getTelefone());
        cliente.setDdd(clienteDto.getDdd());
        cliente.setCodigo(clienteDto.getCodigo());
        cliente.setIdCliente(clienteDto.getIdCliente());


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
        dto.setGenero(cliente.getGenero().toString());
        dto.setSenha(cliente.getSenha());
        dto.setEmail(cliente.getEmail());
        dto.setTelefone(cliente.getTelefone());
        dto.setDdd(cliente.getDdd());
        dto.setCodigo(cliente.getCodigo());
        dto.setAtivo(cliente.isAtivo());

        return dto;
    }

    @Override
    public void updateFromDTO(Cliente cliente, ClienteDTO dto) {
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(dto.getSenha());
        cliente.setCpf(dto.getCpf());
        cliente.setDdd(dto.getDdd());
        cliente.setTelefone(dto.getTelefone());
        cliente.setAtivo(dto.isAtivo());
        cliente.setCodigo(dto.getCodigo());
        cliente.setDataCadastro(dto.getDataCadastro());
        cliente.setDataNascimento(dto.getDataNascimento());
        cliente.setGenero(Genero.valueOf(dto.getGenero()));
        List<Endereco> enderecos = cliente.getEndereco();
        List<EnderecoDTO> enderecosDTO = dto.getEndereco();
        List<CartaoDeCredito> cartoes = cliente.getCartaoDeCredito();
        List<CartaoDeCreditoDTO> cartoesDTO = dto.getCartaoDeCredito();

        for (EnderecoDTO enderecoDTO : enderecosDTO) {
            boolean enderecoExistente = false;
            for (Endereco endereco : enderecos) {
                if (endereco.getIdEndereco().equals(enderecoDTO.getIdEndereco())) {
                    // Atualize o endereço existente com os dados do DTO
                    endereco.setLogradouro(enderecoDTO.getLogradouro());
                    endereco.setNumero(enderecoDTO.getNumero());
                    endereco.setCep(enderecoDTO.getCep());
                    endereco.setBairro(enderecoDTO.getBairro());
                    endereco.setComplemento(enderecoDTO.getComplemento());
                    endereco.setCidade(enderecoDTO.getCidade());
                    endereco.setUf(enderecoDTO.getUf());
                    endereco.setEndCobranca(enderecoDTO.isEndCobranca());
                    endereco.setEndEntrega(enderecoDTO.isEndEntrega());
                    enderecoExistente = true;
                    break;
                }
            }
            if (!enderecoExistente) {
                // Crie um novo endereço e adicione à lista
                Endereco novoEndereco = enderecoMapper.toEntity(enderecoDTO);
                enderecos.add(novoEndereco);
            }
        }
        for (CartaoDeCreditoDTO cartaoDTO : cartoesDTO) {
            boolean cartaoExistente = false;
            for (CartaoDeCredito cartao : cartoes) {
                if (cartao.getIdCartao().equals(cartaoDTO.getIdCartao())) {
                    // Atualize o cartão existente com os dados do DTO
                    cartao.setNome(cartaoDTO.getNome());
                    cartao.setNumero(cartaoDTO.getNumero());
                    cartao.setCvv(cartaoDTO.getCvv());
                    cartao.setValidade(cartaoDTO.getValidade());
                    cartao.setPrincipal(cartaoDTO.isPrincipal());
                    cartaoExistente = true;
                    break;
                }
            }
            if (!cartaoExistente) {
                // Crie um novo cartão e adicione à lista
                CartaoDeCredito novoCartao = cartaoMapper.toEntity(cartaoDTO);
                cartoes.add(novoCartao);
            }
        }

    }
}
