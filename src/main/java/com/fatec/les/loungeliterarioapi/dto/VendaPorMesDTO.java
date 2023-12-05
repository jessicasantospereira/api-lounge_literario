package com.fatec.les.loungeliterarioapi.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaPorMesDTO {
    private Integer mes;
    private Integer ano;
    private Long totalVendas;
}
