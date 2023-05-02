package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    public ResponseEntity<?> salvarEndereco(Endereco endereco) {
        try {
            return new ResponseEntity<Endereco>(this.repository.save(endereco), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Dados informados inválidos!", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> buscarEnderecoPorIdCliente(Long id) {
        Optional<List<Endereco>> record = this.repository.findAllByIdEndereco(id);

        if (record.orElseGet(() -> null) != null) {
            return new ResponseEntity<List<Endereco>>(record.get(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("Endereço não localizado", HttpStatus.BAD_REQUEST);
    }
}
