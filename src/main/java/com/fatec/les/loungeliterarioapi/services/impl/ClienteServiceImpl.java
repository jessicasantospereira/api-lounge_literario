package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.CartaoDeCreditoDTO;
import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.mapper.ClienteMapper;
import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.repository.ClienteRepository;
import com.fatec.les.loungeliterarioapi.services.CartaoService;
import com.fatec.les.loungeliterarioapi.services.ClienteService;
import com.fatec.les.loungeliterarioapi.services.EnderecoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {
    private ClienteRepository repository;
    private EnderecoService enderecoService;
    private ClienteMapper clienteMapper;
    private CartaoService cartaoService;

    public ClienteServiceImpl(ClienteRepository repository, EnderecoService enderecoService, ClienteMapper clienteMapper, CartaoService cartaoService) {
        this.repository = repository;
        this.enderecoService = enderecoService;
        this.clienteMapper = clienteMapper;
        this.cartaoService = cartaoService;
    }
    @Override
    public ResponseEntity<?> salvarCliente(ClienteDTO dados) {
        try {
            if(dados.getIdCliente() != null){
                Cliente cliente = clienteMapper.toEntity(dados);
                cliente.setIdCliente(dados.getIdCliente());
                Cliente existente = repository.save(cliente);
                return new ResponseEntity<ClienteDTO>(clienteMapper.toDto(existente), HttpStatus.CREATED);
            }
            Cliente cliente = repository.save(clienteMapper.toEntity(dados));
            return new ResponseEntity<ClienteDTO>(clienteMapper.toDto(cliente), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Dados informados inválidos!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Cliente buscarPorIdDoCliente(Long id) {
        Cliente c1 = repository.findById(id).get();

        List<Endereco> end = enderecoService.buscarEnderecoPorIdCliente(id);
        List<CartaoDeCredito> cartoes = cartaoService.buscarCartaoPorIdCliente(id);
        c1.setEndereco(end);
        c1.setCartaoDeCredito(cartoes);
        return c1;
    }

    @Override
    public Page<Cliente> buscarTodos(String nome, String cpf, Pageable pageable) {
        Page<Cliente> clientes = repository.buscarPorNomeCpf("%"+nome+"%", "%"+cpf+"%", pageable);
        if(clientes != null){
            return clientes;
        }
        return null;
    }

    @Override
    public void deletarCliente(Cliente cliente) {
        repository.delete(cliente);
    }

    @Override
    public long getItens() {
        return repository.count();
    }

    @Override
    public void salvarCartao(CartaoDeCreditoDTO cartao) {
        Cliente cliente = this.buscarPorIdDoCliente(cartao.getIdCliente());
        cliente.addCartaoDeCredito(cartao.toEntity());

        repository.save(cliente);
    }
}
