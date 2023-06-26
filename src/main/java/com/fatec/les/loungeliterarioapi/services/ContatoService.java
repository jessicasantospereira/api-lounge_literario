package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.model.Contato;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ContatoService {

    ResponseEntity<?> salvarContato(Contato contato);

    List<Contato> buscarContatoPorIdCliente(Long id);
}
