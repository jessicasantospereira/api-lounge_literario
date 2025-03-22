package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface EnderecoService {

    List<Endereco> buscarEnderecoPorIdCliente(Long id);

    ResponseEntity<EnderecoDTO> salvarEndereco(EnderecoDTO endereco);

    void deletarEndereco(Long idEnd);

    Endereco buscarEnderecoPorId(Long idEnd);
}
