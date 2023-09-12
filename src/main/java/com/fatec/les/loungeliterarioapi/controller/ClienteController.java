package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.CartaoDeCreditoDTO;
import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.services.ClienteService;
import com.fatec.les.loungeliterarioapi.services.EnderecoService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/clientes")
@CrossOrigin("*")
public class ClienteController {

    private final ClienteService service;
    private final EnderecoService endService;

    public ClienteController(EnderecoService enderecoService, ClienteService clienteService){
        this.service = clienteService;
        this.endService = enderecoService;
    }
    @GetMapping
    public Page<Cliente> getLista(@RequestParam(value="nome", required = false, defaultValue = "") String nome,
                                     @RequestParam(value="cpf", required = false, defaultValue = "") String cpf,
                                     Pageable pageable){

        return service.buscarTodos(nome, cpf, pageable);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCliente(@PathVariable("id") Long id){
        log.info("Buscar cliente {} ", id);
        Cliente cliente = service.buscarPorIdDoCliente(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        cliente.setIdCliente(id);
        log.info("Cliente encontrado {} ", cliente.getNome());
        return ResponseEntity.ok(cliente);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable("id") Long id, @RequestBody ClienteDTO cliente){
        Cliente clienteExistente = service.buscarPorIdDoCliente(id);
        if (clienteExistente.getIdCliente() == null) {
            return ResponseEntity.notFound().build();
        }
        cliente.setIdCliente(id);
        service.salvarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<?> salvarCliente(@RequestBody ClienteDTO cliente){
       log.info("Cliente entrada {} ", cliente.toString());
        UUID uuid = UUID.randomUUID();
        if (cliente.getIdCliente() != null) {
            Cliente existente = service.buscarPorIdDoCliente(cliente.getIdCliente());
//            if (cliente.getEndereco() != null) {
//                Endereco endereco = null;
//                for (EnderecoDTO end : cliente.getEndereco()) {
//                    existente.addEndereco(end);
//                    endereco = end;
//                }
//                endereco.setCliente(existente);
//                return endService.salvarEndereco(endereco);
//            }

        }else {
            cliente.setCodigo(uuid.toString());
        }

        ResponseEntity clienteSalvo = service.salvarCliente(cliente);
        log.info("Cliente saida {} ", clienteSalvo.toString());
        return clienteSalvo;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable("id") Long id){
        Cliente cliente = service.buscarPorIdDoCliente(id);
        if (cliente.getIdCliente() == null) {
            return ResponseEntity.notFound().build();
        }
        service.deletarCliente(cliente);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cartao")
    public ResponseEntity<?> cadastrarCartao(@RequestBody CartaoDeCreditoDTO cartao){
        log.info("Cartao entrada {} ", cartao.toString());
        return null;
    }


}
