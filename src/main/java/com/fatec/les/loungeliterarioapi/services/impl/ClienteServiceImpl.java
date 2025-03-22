package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.repository.ClienteRepository;
import com.fatec.les.loungeliterarioapi.services.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    @Override
    public Cliente buscarPorIdDoCliente(Long id) {
        return repository.findById(id).get();
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
