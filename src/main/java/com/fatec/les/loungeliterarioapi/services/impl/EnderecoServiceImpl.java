package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import com.fatec.les.loungeliterarioapi.exceptions.DomainException;
import com.fatec.les.loungeliterarioapi.exceptions.Error;
import com.fatec.les.loungeliterarioapi.mapper.EnderecoMapper;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.repository.EnderecoRepository;
import com.fatec.les.loungeliterarioapi.services.ClienteService;
import com.fatec.les.loungeliterarioapi.services.EnderecoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository repository;

    private final ClienteService clienteService;

    private final EnderecoMapper enderecoMapper;

    @Autowired
    public EnderecoServiceImpl(EnderecoRepository repository, EnderecoMapper enderecoMapper, ClienteService clienteService) {
        this.repository = repository;
        this.enderecoMapper = enderecoMapper;
        this.clienteService = clienteService;
    }

    @Override
    public List<Endereco> buscarEnderecoPorIdCliente(Long id) {
        log.info("Buscando endereço por id do cliente");
        return this.repository.findByCliente(id).orElseThrow(() -> new DomainException(Error.NOT_FOUND, "Endereço não encontrado"));
    }

    @Override
    public ResponseEntity<EnderecoDTO> salvarEndereco(EnderecoDTO endereco) {
        log.info("Salvando endereço");
        Cliente c1 = clienteService.buscarPorIdDoCliente(endereco.getIdCliente());
        Endereco end = enderecoMapper.toEntity(endereco);
        end.setCliente(c1);
        if(endereco.getIdEndereco() != null){
            end.setIdEndereco(endereco.getIdEndereco());
            Endereco existente = repository.save(end);
            return new ResponseEntity<>(enderecoMapper.toDto(existente), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(enderecoMapper.toDto(this.repository.save(end)), HttpStatus.CREATED);
    }

    @Override
    public void deletarEndereco(Long idEnd) {
        log.info("Deletando endereço {} ", idEnd);

        this.repository.deleteByIdEndereco(idEnd);

    }

    @Override
    public Endereco buscarEnderecoPorId(Long idEnd) {
        log.info("Buscando endereço por id {} ", idEnd);

        return this.repository.findByIdEndereco(idEnd).get();
    }
}
