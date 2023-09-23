package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import com.fatec.les.loungeliterarioapi.mapper.EnderecoMapper;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.repository.EnderecoRepository;
import com.fatec.les.loungeliterarioapi.services.ClienteService;
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
    private ClienteService clienteService;
    private EnderecoMapper enderecoMapper;
    public EnderecoServiceImpl(EnderecoRepository repository, EnderecoMapper enderecoMapper, ClienteService clienteService) {
        this.repository = repository;
        this.enderecoMapper = enderecoMapper;
        this.clienteService = clienteService;
    }

    @Override
    public List<Endereco> buscarEnderecoPorIdCliente(Long id) {
        log.info("Buscando endereço por id do cliente");
        List<Endereco> enderecos = this.repository.findByCliente(id).get();

        return enderecos;
    }

    @Override
    public ResponseEntity<?> salvarEndereco(EnderecoDTO endereco) {
        log.info("Salvando endereço");
        Cliente c1 = clienteService.buscarPorIdDoCliente(endereco.getIdCliente());
        Endereco end = enderecoMapper.toEntity(endereco);
        end.setCliente(c1);

        return new ResponseEntity<>(this.repository.save(end), HttpStatus.CREATED);
    }

    @Override
    public void deletarEndereco(Endereco end) {
        log.info("Deletando endereço");
        this.repository.delete(end);
    }

    @Override
    public Endereco buscarEnderecoPorId(Long idEnd) {
        return this.repository.findById(idEnd).get();
    }
}
