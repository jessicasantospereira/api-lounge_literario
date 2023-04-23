package com.fatec.les.loungeliterarioapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String nome;
    private String cpf;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente", foreignKey = @ForeignKey(name = "fk_idContato"))
    @JsonManagedReference
    private List<Contato> contato;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente", foreignKey = @ForeignKey(name = "fk_idEndereco"))
    @JsonManagedReference
    private List<Endereco> endereco;
}
