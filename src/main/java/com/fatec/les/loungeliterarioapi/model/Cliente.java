package com.fatec.les.loungeliterarioapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;
    private String nome;
    private String cpf;
    @Column(name = "is_ativo")
    private boolean isAtivo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro = LocalDate.now();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente", foreignKey = @ForeignKey(name = "fk_idContato"))
    @JsonManagedReference
    private List<Contato> contato;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente", foreignKey = @ForeignKey(name = "fk_idEndereco"))
    @JsonManagedReference
    private List<Endereco> endereco;

    public void addEndereco(Endereco end) {
        endereco.add(end);
    }

    public void removerEndereco(Endereco end) {
        endereco.remove(end);
    }

    public void addContato(Contato ct) {
        contato.add(ct);
    }

    public void removerContato(Contato ct) {
        contato.remove(ct);
    }
}
