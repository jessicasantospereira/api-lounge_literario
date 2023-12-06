package com.fatec.les.loungeliterarioapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProdutoResponseDTO {
    private String productName;
    private VendaPorMesDTO sales;
}
