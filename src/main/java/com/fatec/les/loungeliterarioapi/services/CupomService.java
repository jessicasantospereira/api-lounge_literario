package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.model.Cupom;
import com.fatec.les.loungeliterarioapi.repository.CupomRepository;

import java.time.LocalDate;
import java.util.Date;

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
}
