package com.fatec.les.loungeliterarioapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cupom_troca")
public class CupomTroca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_troca")
    private Long idCupomTroca;

    @Column(columnDefinition = "VARCHAR(255)")
    private String codigo;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @OneToOne
    @JoinColumn(name = "solicitacao_troca_id")
    private SolicitacaoTroca solicitacaoTroca;

    private boolean utilizado;

    private BigDecimal valor;

}
