package com.fatec.les.loungeliterarioapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fatec.les.loungeliterarioapi.model.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendaDTO {

    private Long id;

    private Cliente cliente;

    private List<ItemVenda> itens;

    private BigDecimal total;

    private Boolean temCupom;

    private String cupom;

    private Boolean temTroca;

    private String cupomTroca;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVenda = LocalDate.now();

    private List<CartaoDeCredito> cartaoDeCredito;

    private Endereco enderecoEntrega;

    private StatusVenda statusVenda;

    private int parcelas;

    private BigDecimal valorParcela;

}
