package com.fatec.les.loungeliterarioapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "contatos")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContato;
    private String ddd;
    private String telefone;
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contato")
    private TipoTelefone tipoContato;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente", foreignKey = @ForeignKey(name = "fk_idContato"))
    @JsonBackReference
    private Cliente cliente;
}
