package com.fatec.les.loungeliterarioapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Cupom;
import com.fatec.les.loungeliterarioapi.model.FormaPagamento;
import com.fatec.les.loungeliterarioapi.model.ItemVenda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
}
