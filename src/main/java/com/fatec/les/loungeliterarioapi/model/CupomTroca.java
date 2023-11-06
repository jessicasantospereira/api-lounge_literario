package com.fatec.les.loungeliterarioapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

}
