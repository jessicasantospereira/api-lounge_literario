package com.fatec.les.loungeliterarioapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "solicitacao_troca")
public class SolicitacaoTroca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao")
    private Long idSolicitacao;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Column(name = "motivo")
    private String motivo;

    private int quantidade;

    private BigDecimal valor;

    @Column(name = "data_solicitacao")
    private LocalDate dataSolicitacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_solicitacao")
    private StatusSolicitacaoTroca statusSolicitacao;

}
