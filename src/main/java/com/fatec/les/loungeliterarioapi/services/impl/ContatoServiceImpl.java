package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.model.Contato;
import com.fatec.les.loungeliterarioapi.repository.ContatoRepository;
import com.fatec.les.loungeliterarioapi.services.ContatoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class ContatoServiceImpl implements ContatoService {
    private ContatoRepository repository;
    public ContatoServiceImpl(ContatoRepository repository) {
        this.repository = repository;
    }
    @Override
    public ResponseEntity<?> salvarContato(Contato contato) {
        try {
            return new ResponseEntity<Contato>(this.repository.save(contato), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Dados informados inválidos!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Contato> buscarContatoPorIdCliente(Long id) {
        log.info("Buscando contatos por id do cliente");
        List<Contato> contatos = this.repository.findByCliente(id).get();
        return contatos;
    }
}
