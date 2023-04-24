package com.fatec.les.loungeliterarioapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Contato;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long idCliente;
    private String nome;
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;
    private List<Contato> contato;
    private List<Endereco> endereco;

    public Cliente toModel(){
        return new Cliente(idCliente, nome, cpf, dataNascimento, dataCadastro, contato, endereco);
    }

    public static ClienteDTO fromModel(Cliente cliente){
        return new ClienteDTO(cliente.getIdCliente(), cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(),cliente.getDataNascimento(), cliente.getContato(), cliente.getEndereco());
    }
}
