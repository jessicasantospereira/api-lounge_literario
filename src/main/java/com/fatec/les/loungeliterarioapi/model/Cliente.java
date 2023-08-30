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

    @Column(columnDefinition = "VARCHAR(255)")
    private String codigo;

    @Column(name = "is_ativo")
    private boolean isAtivo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String ddd;
    private String telefone;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro = LocalDate.now();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente", foreignKey = @ForeignKey(name = "fk_idEndereco"))
    @JsonManagedReference
    private List<Endereco> endereco;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente", foreignKey = @ForeignKey(name = "fk_idCartao"))
    @JsonManagedReference
    private List<CartaoDeCredito> cartaoDeCredito;

    public void addEndereco(Endereco end) {
        endereco.add(end);
    }

    public void removerEndereco(Endereco end) {
        endereco.remove(end);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", codigo='" + codigo + '\'' +
                ", isAtivo=" + isAtivo +
                ", dataNascimento=" + dataNascimento +
                ", ddd='" + ddd + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", genero=" + genero +
                ", dataCadastro=" + dataCadastro +
                ", endereco=" + endereco +
                ", cartaoDeCredito=" + cartaoDeCredito +
                '}';
    }
}
