package com.fatec.les.loungeliterarioapi.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="venda")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    private int parcelas;

    private BigDecimal valorParcela;

    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itens;

    @Column
    private BigDecimal total;

    @Column
    private Boolean temCupom;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cupom")
    private Cupom cupom;

    @Column
    private Boolean temTroca;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_troca")
    private CupomTroca cupomTroca;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_venda")
    private LocalDate dataVenda;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco enderecoEntrega;

    @Column(name = "status_venda")
    @Enumerated(EnumType.STRING)
    private StatusVenda statusVenda;

}
