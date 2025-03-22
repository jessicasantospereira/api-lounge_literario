package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.model.Cupom;
import com.fatec.les.loungeliterarioapi.services.CupomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/cupons")
public class CupomController {

    @Autowired
    private CupomService service;

    @GetMapping("/{codigo}")
    public ResponseEntity<Cupom> getCuponsByCodigo(@PathVariable("codigo") String codigo){
        return service.buscarCupom(codigo);
    }

    @GetMapping
    public ResponseEntity<List<Cupom>> getAllCupons(){
        return service.buscarCupons();
    }

    @PostMapping
    public ResponseEntity<Cupom> cadastrarCupom(@RequestBody Cupom cupom){
        return service.cadastrar(cupom);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Cupom> buscarPorId(@PathVariable("id") long id){
        log.info("Buscando cupom por id: {}", id);
        return service.buscarCupomPorId(id);
    }

    @PutMapping
    public ResponseEntity<Cupom> atualizarCupom(@RequestBody Cupom cupom){
        return service.atualizarCupom(cupom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletarCupom(@PathVariable("id") long id){
            service.deletarCupom(id);
        return ResponseEntity.noContent().build();
    }

}
