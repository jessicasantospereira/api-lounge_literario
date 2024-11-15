package com.fatec.les.loungeliterarioapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="item_venda")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
