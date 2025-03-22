package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.exceptions.DomainException;
import com.fatec.les.loungeliterarioapi.exceptions.Error;
import com.fatec.les.loungeliterarioapi.model.Cupom;
import com.fatec.les.loungeliterarioapi.repository.CupomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("java:S116")
public class CupomService {

    @Autowired
    private CupomRepository repository;

    private static final String CUPOM_NAO_ENCONTRADO = "Cupom não encontrado";

    public ResponseEntity<Cupom> buscarCupom(String codigo){
        Cupom c1 = repository.findByCodigo(codigo);
        LocalDate dataAtual = LocalDate.now();
        if(c1 == null){
            throw new DomainException(Error.NOT_FOUND, CUPOM_NAO_ENCONTRADO);
        }
        if(dataAtual.isAfter(c1.getDataValidade())){
            throw new DomainException(Error.BAD_REQUEST, "Cupom expirado");
        }
        return new ResponseEntity<>(c1, null,  HttpStatus.OK);
    }

    public Cupom buscarPorId(Long id){
        return repository.findById(id).get();
    }

    public ResponseEntity<List<Cupom>> buscarCupons() {
        return new ResponseEntity<>(repository.findAll(), null, HttpStatus.OK);
    }

    public ResponseEntity<Cupom> cadastrar(Cupom cupom) {
        Cupom c1 = repository.findByCodigo(cupom.getCodigo());
        if(c1 != null){
            throw new DomainException(Error.UNPROCESSABLE_ENTITY, "Cupom já cadastrado");
        }
        return new ResponseEntity<>(repository.save(cupom), null,  HttpStatus.OK);
    }

    public ResponseEntity<Cupom> buscarCupomPorId(long id) {
        Optional<Cupom> cupomOptional = repository.findById(id);
        if (cupomOptional.isPresent()) {
            return ResponseEntity.ok(cupomOptional.get());
        } else {
            throw new DomainException(Error.NOT_FOUND, CUPOM_NAO_ENCONTRADO);
        }
    }

    public ResponseEntity<Cupom> atualizarCupom(Cupom cupom) {
        Optional<Cupom> c1 = repository.findById(cupom.getIdCupom());
        if(c1.isEmpty()){
            throw new DomainException(Error.NOT_FOUND, CUPOM_NAO_ENCONTRADO);
        }
        c1.get().setCodigo(cupom.getCodigo());
        c1.get().setValor(cupom.getValor());
        c1.get().setDataValidade(cupom.getDataValidade());
        return new ResponseEntity<>(repository.save(c1.get()), null,  HttpStatus.OK);
    }

    public void deletarCupom(long id) {
        Optional<Cupom> c1 = repository.findById(id);
        if(c1.isEmpty()){
            throw new DomainException(Error.NOT_FOUND, CUPOM_NAO_ENCONTRADO);
        }
        repository.delete(c1.get());
    }
}
