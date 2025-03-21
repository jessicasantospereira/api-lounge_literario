package com.fatec.les.loungeliterarioapi.usecase.cliente;

import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.exceptions.DomainException;
import com.fatec.les.loungeliterarioapi.exceptions.Error;
import com.fatec.les.loungeliterarioapi.mapper.ClienteMapper;
import com.fatec.les.loungeliterarioapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalvarCliente {

    private final ClienteRepository repository;

    private final ClienteMapper clienteMapper;

    public Long execute(ClienteDTO dados) {
        log.info("Cliente entrada {} ", dados.toString());
        UUID uuid = UUID.randomUUID();
        if(dados.getIdCliente() != null){
            log.info("Cliente pra atualizar {} ", dados.getIdCliente());
            var existente = repository.findById(dados.getIdCliente()).orElseThrow(() -> new DomainException(Error.UNPROCESSABLE_ENTITY, "Cliente não encontrado"));
            clienteMapper.updateFromDTO(existente, clienteMapper.toEntity(dados));
            var clienteAtualizado = repository.save(existente);
            log.info("Cliente atualizado {} ", clienteAtualizado.getIdCliente());
            return clienteAtualizado.getIdCliente();
        }
        dados.setCodigo(uuid.toString());
        var cliente = repository.save(clienteMapper.toEntity(dados));
        log.info("Cliente salvo {} ", cliente.getIdCliente());
        return cliente.getIdCliente();
    }

}
