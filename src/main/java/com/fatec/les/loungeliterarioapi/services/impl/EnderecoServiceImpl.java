package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import com.fatec.les.loungeliterarioapi.mapper.EnderecoMapper;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.repository.EnderecoRepository;
import com.fatec.les.loungeliterarioapi.services.EnderecoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EnderecoServiceImpl implements EnderecoService {
    private EnderecoRepository repository;

    private EnderecoMapper enderecoMapper;
    public EnderecoServiceImpl(EnderecoRepository repository, EnderecoMapper enderecoMapper) {
        this.repository = repository;
        this.enderecoMapper = enderecoMapper;
    }

    @Override
    public List<Endereco> buscarEnderecoPorIdCliente(Long id) {
        log.info("Buscando endereço por id do cliente");
        List<Endereco> enderecos = this.repository.findByCliente(id).get();

        return enderecos;
    }
}
