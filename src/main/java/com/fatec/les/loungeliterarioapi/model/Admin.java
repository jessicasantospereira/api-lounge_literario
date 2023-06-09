package com.fatec.les.loungeliterarioapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private Long produtos;
    private Long clientes;
    private Long vendas;
}
