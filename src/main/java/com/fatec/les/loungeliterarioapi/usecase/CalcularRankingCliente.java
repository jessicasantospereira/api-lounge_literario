package com.fatec.les.loungeliterarioapi.usecase;

import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Venda;
import com.fatec.les.loungeliterarioapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CalcularRankingCliente {

    private final ClienteRepository clienteRepository;

    public Cliente execute(Venda venda) {
        Cliente cliente = venda.getCliente();
        int ranking = 0;
        ranking += venda.getTotal().intValue() / 100;
        for (int i = 0; i < venda.getItens().size(); i++) {
            ranking += venda.getItens().get(i).getQuantidade() * 5;
        }
        ranking += (LocalDate.now().getYear() - cliente.getDataCadastro().getYear()) * 10;
        cliente.setRanking(ranking);
        cliente.setUltimaAtualizacao(LocalDateTime.now());
        clienteRepository.save(cliente);
        return cliente;
    }
}
