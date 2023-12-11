package com.fatec.les.loungeliterarioapi.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ProdutoResponseDTO {
    private String productName;
    private List<VendaPorMesDTO> sales;

    public ProdutoResponseDTO() {
        this.sales = new ArrayList<>();
    }

    public void addSale(String month, Long quantity) {
        VendaPorMesDTO sale = new VendaPorMesDTO();
        sale.setMonth(month);
        sale.setQuantity(quantity);
        this.sales.add(sale);
    }

}
