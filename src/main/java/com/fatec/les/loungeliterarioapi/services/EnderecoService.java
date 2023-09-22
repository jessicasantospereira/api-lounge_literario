package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.dto.EnderecoDTO;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EnderecoService {

   List<Endereco> buscarEnderecoPorIdCliente(Long id);

}
