package com.fatec.les.loungeliterarioapi.dto;

import com.fatec.les.loungeliterarioapi.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVendaDTO {
    private Long id;

    private Cliente cliente;

    private List<ItemVenda> itens;

    private BigDecimal total;
    private List<CartaoDeCredito> cartaoDeCredito;

    private Endereco enderecoEntrega;

    private StatusVenda statusVenda;

    public ResponseVendaDTO(Venda venda) {
        this.id = venda.getId();
        this.cliente = venda.getCliente();
        this.itens = venda.getItens();
        this.total = venda.getTotal();
        this.cartaoDeCredito = venda.getCartaoDeCredito();
        this.enderecoEntrega = venda.getEnderecoEntrega();
        this.statusVenda = venda.getStatusVenda();
    }
}
