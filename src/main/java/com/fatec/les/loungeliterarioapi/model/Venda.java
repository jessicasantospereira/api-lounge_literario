package com.fatec.les.loungeliterarioapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "venda_cartao",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "cartao_id")
    )
    private List<CartaoDeCredito> cartaoDeCredito;

    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itens;
    @Column
    private BigDecimal total;
    @Column
    private Boolean temCupom;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cupom")
    private Cupom cupom;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_troca")
    private CupomTroca cupomTroca;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_venda")
    private LocalDate dataVenda;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco enderecoEntrega;

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", cartaoDeCredito=" + cartaoDeCredito +
                ", itens=" + itens +
                ", total=" + total +
                ", temCupom=" + temCupom +
                ", cupom=" + cupom +
                ", cupomTroca=" + cupomTroca +
                ", dataVenda=" + dataVenda +
                '}';
    }
}
