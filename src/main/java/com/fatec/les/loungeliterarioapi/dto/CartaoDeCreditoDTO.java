package com.fatec.les.loungeliterarioapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartaoDeCreditoDTO {
    private Long idCartao;
    private String numero;
    private String nome;
    private String validade;
    private String cvv;
    private String bandeira;
    private Long idCliente;
    private boolean principal;

}
