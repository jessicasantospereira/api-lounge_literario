package com.fatec.les.loungeliterarioapi;

import com.fatec.les.loungeliterarioapi.dto.*;
import com.fatec.les.loungeliterarioapi.model.*;

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

    public static Cliente criarCliente() {
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
                .genero(Genero.MASCULINO)
                .endereco(Collections.singletonList(criarEndereco()))
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
                .statusVenda(StatusVenda.EM_PROCESSAMENTO)
                .build();
    }

    public static ClienteDTO criarClienteDTO(){

        return ClienteDTO.builder()
                .idCliente(1L)
                .nome("Fulano DTO")
                .cpf("12345678901")
                .codigo("123")
                .isAtivo(true)
                .dataCadastro(LocalDate.of(2025, 10, 1))
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .ddd("11")
                .genero("MASCULINO")
                .telefone("999999999")
                .email("")
                .endereco(List.of(criarEnderecoDTO()))
                .build();
    }

    public static EnderecoDTO criarEnderecoDTO(){
        return EnderecoDTO.builder()
                .logradouro("Rua dos Bobos")
                .numero("0")
                .complemento("")
                .bairro("Centro")
                .cidade("São Paulo")
                .uf("SP")
                .cep("12345678")
                .endEntrega(true)
                .build();
    }

    public static Endereco criarEndereco(){
        return Endereco.builder()
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

    }

    public static Produto criarProduto(){
        return Produto.builder()
                .id(1L)
                .titulo("Livro")
                .descricao("Livro de teste")
                .preco(new BigDecimal(50))
                .build();
    }

    public static ProdutoDTO criarProdutoDTO(){
        return ProdutoDTO.builder()
                .id(1L)
                .titulo("LivroDTO")
                .descricao("Livro de teste DTO")
                .preco(new BigDecimal(50))
                .build();
    }

    public static TrocaDTO criarSolicitacaoTroca(){
        return TrocaDTO.builder()
                .idSolicitacao(1L)
                .statusSolicitacao(StatusSolicitacaoTroca.EM_TROCA)
                .valor(BigDecimal.valueOf(100.0))
                .motivo("Produto com defeito")
                .idCliente(1)
                .quantidade(1)
                .idProduto(1)
                .build();
    }

}
