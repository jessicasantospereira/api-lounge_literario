package com.fatec.les.loungeliterarioapi;

import com.fatec.les.loungeliterarioapi.dto.VendaDTO;
import com.fatec.les.loungeliterarioapi.model.Cliente;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.model.ItemVenda;
import com.fatec.les.loungeliterarioapi.model.Venda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utils {

    public static Venda criarVenda() {
        Cliente cliente = criarCliente();
        return Venda.builder()
                .id(1L)
                .cliente(cliente)
                .enderecoEntrega(cliente.getEndereco().get(0))
                .temCupom(false)
                .temTroca(false)
                .total(new BigDecimal(545))
                .itens(criarItensVenda())
                .build();
    }

    private static Cliente criarCliente() {
        Endereco endereco = Endereco.builder()
                .idEndereco(1L)
                .logradouro("Rua dos Bobos")
                .numero("0")
                .complemento("")
                .bairro("Centro")
                .cidade("São Paulo")
                .uf("SP")
                .cep("12345678")
                .endEntrega(true)
                .build();

        return Cliente.builder()
                .idCliente(1L)
                .nome("Fulano")
                .cpf("12345678901")
                .codigo("123")
                .isAtivo(true)
                .dataCadastro(LocalDate.of(2023, 10, 1))
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .ddd("11")
                .telefone("999999999")
                .email("fulano@teste.com")
                .endereco(Collections.singletonList(endereco))
                .build();
    }

    private static List<ItemVenda> criarItensVenda() {
        ItemVenda itemVenda = ItemVenda.builder()
                .id(1L)
                .quantidade(3)
                .build();

        return Arrays.asList(itemVenda, itemVenda, itemVenda);
    }

    public static VendaDTO criarVendaDTO() {
        return VendaDTO.builder()
                .cliente(criarCliente())
                .enderecoEntrega(criarCliente().getEndereco().get(0))
                .temCupom(false)
                .temTroca(false)
                .itens(criarItensVenda())
                .build();
    }
}
