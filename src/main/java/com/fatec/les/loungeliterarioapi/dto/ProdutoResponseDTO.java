package com.fatec.les.loungeliterarioapi.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ProdutoResponseDTO {

    private String titulo;

    private List<VendaPorMesDTO> sales;

    public ProdutoResponseDTO() {
        this.sales = new ArrayList<>();
    }

    public void addSale(String mes, Long quantidade) {
        VendaPorMesDTO sale = new VendaPorMesDTO();
        sale.setMes(mes);
        sale.setQuantidade(quantidade);
        this.sales.add(sale);
    }

}
