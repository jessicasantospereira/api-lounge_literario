package com.fatec.les.loungeliterarioapi.controller;

import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public Page<ClienteDTO> getLista(@RequestParam(value="nome", required = false, defaultValue = "") String nome,
                                     @RequestParam(value="cpf", required = false, defaultValue = "") String cpf,
                                     Pageable pageable){
        return repository.buscarPorNomeCpf("%"+nome+"%", "%"+cpf+"%", pageable).map(ClienteDTO::fromModel);
    }


}
