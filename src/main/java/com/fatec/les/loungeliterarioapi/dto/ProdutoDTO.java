package com.fatec.les.loungeliterarioapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fatec.les.loungeliterarioapi.model.Produto;
import com.fatec.les.loungeliterarioapi.model.Tags;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String imagem;
    private Tags tag;
    private BigDecimal preco;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    public Produto toModel(){
        return new Produto(titulo, descricao, imagem, tag, preco);
    }
    public static ProdutoDTO fromModel(Produto produto){
        return new ProdutoDTO(produto.getId(), produto.getTitulo(), produto.getDescricao(), produto.getImagem(), produto.getTag(), produto.getPreco(), produto.getDataCadastro());
    }
}
