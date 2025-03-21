package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.model.Cupom;
import com.fatec.les.loungeliterarioapi.services.CupomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/cupons")
@CrossOrigin("*")
public class CupomController {

    @Autowired
    private CupomService service;

    @GetMapping("/{codigo}")
    public ResponseEntity<?> getCuponsByCodigo(@PathVariable("codigo") String codigo){
        return service.buscarCupom(codigo);
    }

    @GetMapping
    public ResponseEntity<?> getAllCupons(){
        return service.buscarCupons();
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCupom(@RequestBody Cupom cupom){
        return service.cadastrar(cupom);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") long id){
        log.info("Buscando cupom por id: {}", id);
        return service.buscarCupomPorId(id);
    }

    @PutMapping
    public ResponseEntity<?> atualizarCupom(@RequestBody Cupom cupom){
        return service.atualizarCupom(cupom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCupom(@PathVariable("id") long id){
        return service.deletarCupom(id);
    }

}
