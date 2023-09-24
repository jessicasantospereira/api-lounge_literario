package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.services.CupomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cupons")
@CrossOrigin("*")
public class CupomController {
    @Autowired
    private CupomService service;
    @GetMapping("/{codigo}")
    public ResponseEntity<?> getCupons(@PathVariable("codigo") String codigo){
        return service.buscarCupom(codigo);

    }
}
