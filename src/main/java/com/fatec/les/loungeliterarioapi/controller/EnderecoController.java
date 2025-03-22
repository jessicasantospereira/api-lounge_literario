package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.services.EnderecoService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/clientes/endereco")
public class EnderecoController {

    private final EnderecoService endService;

    public EnderecoController(EnderecoService enderecoService) {
        this.endService = enderecoService;
    }

    @PostMapping()
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@RequestBody EnderecoDTO endereco){
        log.info("Endereco entrada {} ", endereco.toString());
        return endService.salvarEndereco(endereco);

    }

    @GetMapping("/{id}/{idEnd}")
    public ResponseEntity<Endereco> buscarEndereco(@PathVariable("id") Long id, @PathVariable("idEnd") Long idEnd){
        log.info("Buscar endereco cliente id: {} ", id);
        Endereco endereco = endService.buscarEnderecoPorId(idEnd);
        return ResponseEntity.ok(endereco);

    }

    @DeleteMapping("/{id}/{idEnd}")
    @Transactional
    public ResponseEntity<HttpStatus> deletarEndereco(@PathVariable("id") Long id, @PathVariable("idEnd") Long idEnd){
        log.info("Deletar endereco cliente id: {} e endereco id: {} ", id, idEnd);
        Endereco endereco = endService.buscarEnderecoPorIdCliente(id).stream().filter(end -> end.getIdEndereco().equals(idEnd)).findFirst().get();
        log.info("Endereco encontrado {} ", endereco.getLogradouro());
        endService.deletarEndereco(endereco.getIdEndereco());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idEnd}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable("idEnd") Long idEnd, @RequestBody EnderecoDTO endereco){
        log.info("Endereco entrada {} ", endereco.toString());

        return endService.salvarEndereco(endereco);
    }

}
