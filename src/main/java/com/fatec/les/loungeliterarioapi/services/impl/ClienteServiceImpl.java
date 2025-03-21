package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.mapper.ClienteMapper;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.repository.ClienteRepository;
import com.fatec.les.loungeliterarioapi.services.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository repository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository repository,ClienteMapper clienteMapper) {
        this.repository = repository;
        this.clienteMapper = clienteMapper;
    }
    @Override
    @Transactional
    public ResponseEntity<?> salvarCliente(ClienteDTO dados) {
        try {
            if(dados.getIdCliente() != null){
                Cliente existente = repository.findById(dados.getIdCliente()).orElse(null);
                if (existente != null) {
                    // Atualize o cliente existente com os dados do DTO
                    clienteMapper.updateFromDTO(existente, clienteMapper.toEntity(dados));

                    // Salve o cliente atualizado
                    Cliente clienteAtualizado = repository.save(existente);

                    return new ResponseEntity<ClienteDTO>(clienteMapper.toDto(clienteAtualizado), HttpStatus.CREATED);
                }
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



}
