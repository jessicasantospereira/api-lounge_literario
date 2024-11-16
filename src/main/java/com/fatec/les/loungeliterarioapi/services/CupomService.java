package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.model.Cupom;
import com.fatec.les.loungeliterarioapi.repository.CupomRepository;

import java.time.LocalDate;
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
        Optional<Cupom> cupomOptional = repository.findById(id);
        if (cupomOptional.isPresent()) {
            return ResponseEntity.ok(cupomOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cupom não encontrado");
        }
    }

    public ResponseEntity<?> atualizarCupom(Cupom cupom) {
        Optional<Cupom> c1 = repository.findById(cupom.getIdCupom());
        if(c1.isEmpty()){
            return new ResponseEntity<String>("Cupom não encontrado", null,  HttpStatus.NOT_FOUND);
        }
        c1.get().setCodigo(cupom.getCodigo());
        c1.get().setValor(cupom.getValor());
        c1.get().setDataValidade(cupom.getDataValidade());
        return new ResponseEntity<Cupom>(repository.save(c1.get()), null,  HttpStatus.OK);
    }

    public ResponseEntity<?> deletarCupom(long id) {
        Optional<Cupom> c1 = repository.findById(id);
        if(c1.isEmpty()){
            return new ResponseEntity<String>("Cupom não encontrado", null,  HttpStatus.NOT_FOUND);
        }
        repository.delete(c1.get());
        return new ResponseEntity<>("Excluído com sucesso", null,  HttpStatus.OK);
    }
}
