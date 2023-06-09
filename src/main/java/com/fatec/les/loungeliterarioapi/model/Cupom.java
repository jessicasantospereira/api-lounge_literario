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
@Table(name = "cupom")
public class Cupom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cupom")
    private Long idCupom;

    private String codigo;
    @Column(name = "data_validade")
    private LocalDate dataValidade;

    private Double valor;

}
