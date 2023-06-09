package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.dto.ClienteDTO;
import com.fatec.les.loungeliterarioapi.mapper.ClienteMapper;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Contato;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ContatoService contatoService;
    @Autowired
    private EnderecoService enderecoService;

    public Cliente buscarPorIdDoCliente(Long id) {
        Optional<Cliente> record = this.repository.findById(id);

        if (record.orElseGet(() -> null) != null) {
//             return new ResponseEntity<Cliente>(record.get(), HttpStatus.OK);
            return (Cliente) record.get();
        }
        // return new ResponseEntity<String>("Cliente não localizado",
        // HttpStatus.BAD_REQUEST);
        return null;
    }

    public Page<Cliente> buscarTodos(String nome, String cpf, Pageable pageable) {
        Page<Cliente> clientes = repository.buscarPorNomeCpf("%"+nome+"%", "%"+cpf+"%", pageable);

        return clientes;
//        try {
//            Collection<Cliente> lista = this.repository.findAll();
//            return new ResponseEntity<Collection<Cliente>>(lista, HttpStatus.OK);
//        } catch (MethodArgumentTypeMismatchException | NumberFormatException e) {
//            e.printStackTrace();
//            return new ResponseEntity<String>(
//                    "Não foi possível encontrar os dados. Verifique se o link digitado está correto.",
//                    HttpStatus.NOT_FOUND);
//        }
    }

    public ResponseEntity<?> salvarCliente(ClienteDTO dados) {

        try {
            if(dados.getIdCliente() != null){
                Cliente cliente = clienteMapper.toEntity(dados);
                cliente.setIdCliente(dados.getIdCliente());
                Cliente existente = repository.save(cliente);
                return new ResponseEntity<ClienteDTO>(clienteMapper.toDto(existente), HttpStatus.CREATED);
            }
            Cliente cliente = repository.save(clienteMapper.toEntity(dados));
            List<Endereco> enderecos = dados.getEndereco();
            List<Contato> contatos = dados.getContato();
            for (Endereco endereco : enderecos) {
                endereco.setCliente(cliente);
                enderecoService.salvarEndereco(endereco);
            }
            for (Contato contato : contatos) {
                contato.setCliente(cliente);
                contatoService.salvarContato(contato);
            }
            return new ResponseEntity<ClienteDTO>(clienteMapper.toDto(cliente), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Dados informados inválidos!", HttpStatus.BAD_REQUEST);
        }
    }

    public void deletarCliente(Cliente cliente) {
        repository.delete(cliente);
    }

    public long getItens() {
        return repository.count();
    }
}
