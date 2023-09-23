package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.services.EnderecoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/clientes/endereco")
public class EnderecoController {
    private final EnderecoService endService;
    public EnderecoController(EnderecoService enderecoService) {
        this.endService = enderecoService;
    }
    @PostMapping()
    public ResponseEntity<?> cadastrarEndereco(@RequestBody EnderecoDTO endereco){
        log.info("Endereco entrada {} ", endereco.toString());
        return endService.salvarEndereco(endereco);

    }
    @GetMapping("/{id}/{idEnd}")
    public ResponseEntity<?> buscarEndereco(@PathVariable("id") Long id, @PathVariable("idEnd") Long idEnd){
        log.info("Buscar endereco cliente id: {} ", id);
        Endereco endereco = endService.buscarEnderecoPorId(idEnd);
        return ResponseEntity.ok(endereco);

    }
    @DeleteMapping("/{id}/{idEnd}")
    public ResponseEntity<?> deletarEndereco(@PathVariable("id") Long id, @PathVariable("idEnd") Long idEnd){
        log.info("Deletar endereco cliente id: {} ", id);

        return ResponseEntity.ok().build();
    }
}
