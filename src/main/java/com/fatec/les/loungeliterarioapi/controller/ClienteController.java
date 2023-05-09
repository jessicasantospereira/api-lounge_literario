package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Contato;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.repository.ClienteRepository;
import com.fatec.les.loungeliterarioapi.repository.ContatoRepository;
import com.fatec.les.loungeliterarioapi.repository.EnderecoRepository;
import com.fatec.les.loungeliterarioapi.services.ClienteService;
import com.fatec.les.loungeliterarioapi.services.ContatoService;
import com.fatec.les.loungeliterarioapi.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/clientes")
@CrossOrigin("*")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ClienteService service;
    @Autowired
    private EnderecoService endService;
    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public Page<Cliente> getLista(@RequestParam(value="nome", required = false, defaultValue = "") String nome,
                                     @RequestParam(value="cpf", required = false, defaultValue = "") String cpf,
                                     Pageable pageable){
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
//        return repository.buscarPorNomeCpf("%"+nome+"%", "%"+cpf+"%", pageable).map(ClienteDTO::fromModel);
        return service.buscarTodos(nome, cpf, pageable);
    }
    @PostMapping
    public ResponseEntity<?> salvarCliente(@RequestBody Cliente cliente){
        if (cliente.getIdCliente() != null) {
            Cliente existente = service.buscarPorIdDoCliente(cliente.getIdCliente());
            if (cliente.getEndereco() != null) {
                Endereco endereco = null;
                for (Endereco end : cliente.getEndereco()) {
                    existente.addEndereco(end);
                    endereco = end;
                }
                endereco.setCliente(existente);
                return endService.salvarEndereco(endereco);
            }
            if (cliente.getContato() != null) {
                Contato contato = null;
                for (Contato cont : cliente.getContato()) {
                    existente.addContato(cont);
                    contato = cont;
                }
                contato.setCliente(existente);
                return contatoService.salvarContato(contato);
            }
        }
        return service.salvarCliente(cliente);
    }


}
