package com.fatec.les.loungeliterarioapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento")
    private FormaPagamento formaPagamento;
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

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", formaPagamento=" + formaPagamento +
                ", itens=" + itens +
                ", total=" + total +
                ", temCupom=" + temCupom +
                ", cupom=" + cupom +
                '}';
    }
}
