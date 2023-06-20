package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.mapper.ClienteMapper;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Contato;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.repository.ClienteRepository;
import com.fatec.les.loungeliterarioapi.services.ClienteService;
import com.fatec.les.loungeliterarioapi.services.ContatoService;
import com.fatec.les.loungeliterarioapi.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    private ClienteRepository repository;
    private EnderecoService enderecoService;
    private ContatoService contatoService;
    private ClienteMapper clienteMapper;
    public ClienteServiceImpl(ClienteRepository repository, EnderecoService enderecoService, ContatoService contatoService, ClienteMapper clienteMapper) {
        this.repository = repository;
        this.enderecoService = enderecoService;
        this.contatoService = contatoService;
        this.clienteMapper = clienteMapper;
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
            List<Endereco> enderecos = dados.getEndereco();
            List<Contato> contatos = dados.getContato();
            for (Endereco endereco : enderecos) {
                endereco.setCliente(cliente);
                enderecoService.salvarEndereco(endereco);
            }
            for (Contato contato : contatos) {
                contato.setCliente(cliente);
                contatoService.salvarContato(contato);
            }
            return new ResponseEntity<ClienteDTO>(clienteMapper.toDto(cliente), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Dados informados inválidos!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Cliente buscarPorIdDoCliente(Long id) {
        Cliente cliente = repository.findById(id).orElse(null);
        if(cliente != null){
            return cliente;
        }
        return null;
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
}
