package com.fatec.les.loungeliterarioapi.dto;

import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Contato;
import com.fatec.les.loungeliterarioapi.model.TipoTelefone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContatoDTO {
    private Long idContato;
    private String ddd;
    private String telefone;
    private String email;
    private TipoTelefone tipoContato;
    private Cliente cliente;

//    public ContatoDTO(Long idContato, String ddd, String telefone, String email, TipoTelefone tipoContato) {
//        this.idContato = idContato;
//        this.ddd = ddd;
//        this.telefone = telefone;
//        this.email = email;
//        this.tipoContato = tipoContato;
//    }

    public Contato toModel(){
        return new Contato(idContato, ddd, telefone, email, tipoContato, cliente);
    }

    public ContatoDTO fromModel(Contato contato){
        return new ContatoDTO(getIdContato(), contato.getDdd(), contato.getTelefone(), contato.getEmail(), contato.getTipoContato(), contato.getCliente());
    }
}
