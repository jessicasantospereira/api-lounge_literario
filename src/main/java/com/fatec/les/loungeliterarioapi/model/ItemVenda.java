package com.fatec.les.loungeliterarioapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="item_venda")
public class ItemVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    @Column
    private Integer quantidade;

    @Override
    public String toString() {
        return "ItemVenda{" +
                "id=" + id +
                ", venda=" + venda +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }
}
