package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.dto.CartaoDeCreditoDTO;
import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ClienteService {
    ResponseEntity<?> salvarCliente(ClienteDTO cliente);
    Cliente buscarPorIdDoCliente(Long id);
    Page<Cliente> buscarTodos(String nome, String cpf, Pageable pageable);
    void deletarCliente(Cliente cliente);
    long getItens();

    void salvarCartao(CartaoDeCreditoDTO cartao);

}
