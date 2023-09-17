package com.fatec.les.loungeliterarioapi.dto;

import com.fatec.les.loungeliterarioapi.model.Bandeiras;
import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;
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

    public CartaoDeCredito toEntity() {
        CartaoDeCredito cartao = new CartaoDeCredito();
        cartao.setIdCartao(this.getIdCartao());
        cartao.setNumero(this.getNumero());
        cartao.setNome(this.getNome());
        cartao.setValidade(this.getValidade());
        cartao.setCvv(this.getCvv());
        cartao.setBandeira(Bandeiras.valueOf(this.getBandeira()));
        return cartao;
    }
}
