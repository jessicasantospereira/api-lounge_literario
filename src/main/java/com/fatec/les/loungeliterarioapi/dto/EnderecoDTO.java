package com.fatec.les.loungeliterarioapi.dto;

import com.fatec.les.loungeliterarioapi.model.TipoEndereco;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnderecoDTO {
    private Long idEndereco;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String bairro;
    private String cidade;
    private String uf;
    private boolean endEntrega;
    private boolean endCobranca;
    private TipoEndereco tipoEndereco;
    private Long idCliente;
    private String nomeEndereco;
}
