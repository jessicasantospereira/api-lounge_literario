package com.fatec.les.loungeliterarioapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;
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

    private UUID codigo;

    @Column(name = "data_validade")
    private LocalDate dataValidade;
}
