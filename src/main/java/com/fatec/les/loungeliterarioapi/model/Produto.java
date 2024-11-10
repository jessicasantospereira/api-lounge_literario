package com.fatec.les.loungeliterarioapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "VARCHAR(255)")
    private String descricao;

    private String imagem;

    private String codigo;

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

    @Enumerated(EnumType.STRING)
    private List<Tags> categoria;
    @Column(name = "preco", precision = 16, scale = 2)
    private BigDecimal preco;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="data_cadastro")
    private LocalDate dataCadastro;

    private int qtdeEstoque;

    public Produto(String titulo, String descricao, String imagem, Tags categoria, BigDecimal preco, int qtde, String codigo, boolean isAtivo, int numeroPaginas, String autor, String editora, String edicao, String ano, String isbn, double altura, double largura, double peso, double profundidade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem = imagem;
        this.categoria.add(categoria);
        this.preco = preco;
        this.qtdeEstoque = qtde;
        this.codigo = codigo;
        this.isAtivo = isAtivo;
        this.numeroPaginas = numeroPaginas;
        this.autor = autor;
        this.editora = editora;
        this.edicao = edicao;
        this.ano = ano;
        this.isbn = isbn;
        this.altura = altura;
        this.largura = largura;
        this.peso = peso;
        this.profundidade = profundidade;
        setDataCadastro(LocalDate.now());
    }

}
