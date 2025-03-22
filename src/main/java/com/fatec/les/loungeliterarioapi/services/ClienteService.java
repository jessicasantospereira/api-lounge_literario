package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

    Cliente buscarPorIdDoCliente(Long id);
    Page<Cliente> buscarTodos(String nome, String cpf, Pageable pageable);
    void deletarCliente(Cliente cliente);
    long getItens();

}
