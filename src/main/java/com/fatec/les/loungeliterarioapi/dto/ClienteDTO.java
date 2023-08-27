package com.fatec.les.loungeliterarioapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteDTO {
    private Long idCliente;
    private String nome;
    private String cpf;
    private boolean isAtivo;
    private String codigo;
    private String ddd;
    private String telefone;
    private String email;
    private String senha;
    private String genero;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCadastro  = LocalDate.now();;

    private List<Endereco> endereco;
}
