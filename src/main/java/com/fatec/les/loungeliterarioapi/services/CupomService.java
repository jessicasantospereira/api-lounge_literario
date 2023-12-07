package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.model.Cupom;
import com.fatec.les.loungeliterarioapi.repository.CupomRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CupomService {
    @Autowired
    private CupomRepository repository;

    public ResponseEntity<?> buscarCupom(String codigo){
        Cupom c1 = repository.findByCodigo(codigo);
        LocalDate dataAtual = LocalDate.now();
        if(c1 == null){
            return new ResponseEntity<String>("Cupom não encontrado", null,  HttpStatus.FORBIDDEN);
        }
        if(dataAtual.isAfter(c1.getDataValidade())){
            return new ResponseEntity<Cupom>(c1, null,  HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Cupom>(c1, null,  HttpStatus.OK);
    }

    public Cupom buscarPorId(Long id){
        return repository.findById(id).get();
    }

    public ResponseEntity<?> buscarCupons() {
        return new ResponseEntity<>(repository.findAll(), null, HttpStatus.OK);
    }

    public ResponseEntity<?> cadastrar(Cupom cupom) {
        Cupom c1 = repository.findByCodigo(cupom.getCodigo());
        if(c1 != null){
            return new ResponseEntity<String>("Cupom já cadastrado", null,  HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Cupom>(repository.save(cupom), null,  HttpStatus.OK);
    }

    public ResponseEntity<?> buscarCupomPorId(long id) {
        Cupom c1 = repository.findById(id).get();
        Optional.ofNullable(c1).orElseThrow(() -> new RuntimeException("Cupom não encontrado"));

        return new ResponseEntity<Cupom>(c1, null,  HttpStatus.OK);
    }

    public ResponseEntity<?> atualizarCupom(Cupom cupom) {
        Cupom c1 = repository.findById(cupom.getIdCupom()).get();
        Optional.ofNullable(c1).orElseThrow(() -> new RuntimeException("Cupom não encontrado"));
        c1.setCodigo(cupom.getCodigo());
        c1.setValor(cupom.getValor());
        c1.setDataValidade(cupom.getDataValidade());
        return new ResponseEntity<Cupom>(repository.save(c1), null,  HttpStatus.OK);
    }
}
