package com.fatec.les.loungeliterarioapi.dto;

import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.FormaPagamento;
import com.fatec.les.loungeliterarioapi.model.ItemVenda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaDTO {
    private Long id;

    private Cliente cliente;

    private FormaPagamento formaPagamento;

    private List<ItemVenda> itens;

    private BigDecimal total;

    private Boolean temCupom;

    private String cupom;

    private LocalDate dataVenda;
}
