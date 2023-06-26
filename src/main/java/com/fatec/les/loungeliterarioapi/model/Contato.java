package com.fatec.les.loungeliterarioapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "contatos")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contato")
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

    @Override
    public String toString() {
        return "Contato{" +
                "idContato=" + idContato +
                ", ddd='" + ddd + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", tipoContato=" + tipoContato +
                ", cliente=" + cliente +
                '}';
    }
}
