package com.fatec.les.loungeliterarioapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cartoes")
public class CartaoDeCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartao")
    private Long idCartao;
    private String numero;
    private String nome;
    private String validade;
    private String cvv;
    @Enumerated(EnumType.STRING)
    @Column(name = "bandeira")
    private Bandeiras bandeira;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente", foreignKey = @ForeignKey(name = "fk_idCartao"))
    @JsonBackReference
    private Cliente cliente;

    private boolean principal;

    @JsonIgnore
    @ManyToMany(mappedBy = "cartaoDeCredito")
    private List<Venda> vendas;

}
