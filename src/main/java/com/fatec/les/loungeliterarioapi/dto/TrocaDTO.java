package com.fatec.les.loungeliterarioapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fatec.les.loungeliterarioapi.model.StatusSolicitacaoTroca;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrocaDTO {
    private Long idSolicitacao;

    private Integer idCliente;

    private Integer idProduto;

    private String motivo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataSolicitacao = LocalDate.now();

    private int quantidade;

    private BigDecimal valor;

    private StatusSolicitacaoTroca statusSolicitacao;

    @Override
    public String toString() {
        return "TrocaDTO{" +
                "idCliente=" + idCliente +
                ", idProduto=" + idProduto +
                ", motivo='" + motivo + '\'' +
                ", dataSolicitacao=" + dataSolicitacao +
                ", statusSolicitacao=" + statusSolicitacao +
                '}';
    }
}
