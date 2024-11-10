package com.fatec.les.loungeliterarioapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_validade")
    private LocalDate dataValidade;

    private Double valor;

}
