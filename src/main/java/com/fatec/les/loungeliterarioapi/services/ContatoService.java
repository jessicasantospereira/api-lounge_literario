package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.model.Contato;
import com.fatec.les.loungeliterarioapi.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {
    @Autowired
    ContatoRepository repository;

    public ResponseEntity<?> salvarContato(Contato contato) {
        try {
            return new ResponseEntity<Contato>(this.repository.save(contato), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Dados informados inválidos!", HttpStatus.BAD_REQUEST);
        }
    }
}
