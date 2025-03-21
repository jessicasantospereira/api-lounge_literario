package com.fatec.les.loungeliterarioapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
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

    private String nomeEndereco;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_endereco")
    private TipoEndereco tipoEndereco;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idCliente", foreignKey = @ForeignKey(name = "fk_idEndereco"))
    @JsonBackReference
    private Cliente cliente;

}
