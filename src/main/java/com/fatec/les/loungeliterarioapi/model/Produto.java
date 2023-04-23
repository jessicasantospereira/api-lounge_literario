package com.fatec.les.loungeliterarioapi.model;

import com.fatec.les.loungeliterarioapi.dto.ProdutoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

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
    private String descricao;
    private String imagem;

    @Enumerated(EnumType.STRING)
    private Tags tag;
    @Column(name = "preco", precision = 16, scale = 2)
    private BigDecimal preco;
    @Column(name="data_cadastro")
    private LocalDate dataCadastro;

    public Produto(String titulo, String descricao, String imagem, Tags tag, BigDecimal preco) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem = imagem;
        this.tag = tag;
        this.preco = preco;
        setDataCadastro(LocalDate.now());
    }

}
