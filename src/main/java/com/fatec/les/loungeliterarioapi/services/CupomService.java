package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.model.Cupom;
import com.fatec.les.loungeliterarioapi.repository.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CupomService {
    @Autowired
    private CupomRepository repository;

    public Cupom buscarCupom(String codigo){
        return repository.findByCodigo(codigo);
    }

    public Cupom buscarPorId(Long id){
        return repository.findById(id).get();
    }
}
