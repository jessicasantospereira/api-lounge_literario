package com.fatec.les.loungeliterarioapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fatec.les.loungeliterarioapi.model.Tags;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String imagem;
    private List<Tags> categoria;
    private BigDecimal preco;
    private String codigo;
    private int qtdeEstoque;
    private boolean isAtivo;
    private int numeroPaginas;
    private String autor;
    private String editora;
    private String edicao;
    private String ano;
    private String isbn;
    private double altura;
    private double largura;
    private double peso;
    private double profundidade;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCadastro = LocalDate.now();

}
