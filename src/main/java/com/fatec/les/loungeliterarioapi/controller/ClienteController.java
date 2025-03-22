package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.services.ClienteService;

import com.fatec.les.loungeliterarioapi.usecase.cliente.SalvarCliente;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/clientes")
public class ClienteController {

    private final SalvarCliente salvarCliente;

    private final ClienteService service;

    @GetMapping
    public Page<Cliente> getLista(@RequestParam(value="nome", required = false, defaultValue = "") String nome,
                                     @RequestParam(value="cpf", required = false, defaultValue = "") String cpf,
                                     Pageable pageable){

        return service.buscarTodos(nome, cpf, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable("id") Long id){
        log.info("Buscar cliente {} ", id);
        Cliente cliente = service.buscarPorIdDoCliente(id);
        if (cliente == null) {
           throw new EntityNotFoundException();
        }
        cliente.setIdCliente(id);
        log.info("Cliente encontrado {} ", cliente.getNome());
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable("id") Long id, @RequestBody ClienteDTO cliente){
        cliente.setIdCliente(id);
        return ResponseEntity.ok(salvarCliente.execute(cliente));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> salvarCliente(@RequestBody ClienteDTO cliente){
        return ResponseEntity.ok(salvarCliente.execute(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletarCliente(@PathVariable("id") Long id){
        Cliente cliente = service.buscarPorIdDoCliente(id);
        if (cliente.getIdCliente() == null) {
            throw new EntityNotFoundException();
        }
        service.deletarCliente(cliente);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }


}
