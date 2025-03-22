package com.fatec.les.loungeliterarioapi.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaPorMesDTO {

    private String mes;

    private Long quantidade;

}
