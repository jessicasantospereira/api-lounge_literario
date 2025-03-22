package com.fatec.les.loungeliterarioapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "produto")
@Data
@Builder
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

    @PrePersist
    public void prePersist() {
        this.isAtivo = true;
        this.dataCadastro = LocalDate.now();
    }

}
