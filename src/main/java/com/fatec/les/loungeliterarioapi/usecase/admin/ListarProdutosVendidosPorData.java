package com.fatec.les.loungeliterarioapi.usecase.admin;

import com.fatec.les.loungeliterarioapi.dto.ProdutoResponseDTO;
import com.fatec.les.loungeliterarioapi.projection.VendasProjection;
import com.fatec.les.loungeliterarioapi.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListarProdutosVendidosPorData {

    private final ProdutoRepository repository;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<ProdutoResponseDTO> execute(String dataInicial, String dataFinal){
        log.info("Iniciando busca de vendas por dataInicial {} e dataFinal {}", dataInicial, dataFinal);
        LocalDate dataInicialConvertida = LocalDate.parse(dataInicial.trim());
        LocalDate dataFinalConvertida = LocalDate.parse(dataFinal.trim());
        List<VendasProjection> vendasPorData = repository.findVendasByDataVenda(dataInicialConvertida, dataFinalConvertida);
        log.info("Vendas encontradas {}", vendasPorData.size());
        List<ProdutoResponseDTO> produtos = new ArrayList<>();
        for(VendasProjection venda : vendasPorData){
            Optional<ProdutoResponseDTO> existingProduct = produtos.stream()
                    .filter(p -> p.getTitulo() != null && p.getTitulo().equals(venda.getNome()))
                    .findFirst();
            if(existingProduct.isPresent()) {
                ProdutoResponseDTO produto = existingProduct.get();
                produto.addSale(venda.getMes().format(FORMATTER), venda.getQuantidade());
            } else {
                ProdutoResponseDTO novoProduto = new ProdutoResponseDTO();
                novoProduto.setTitulo(venda.getNome());
                novoProduto.addSale(venda.getMes().format(FORMATTER), venda.getQuantidade());
                produtos.add(novoProduto);
            }
        }
        log.info("{} produtos encontrados dataInicial {} e dataFinal {}", produtos.size(), dataInicial, dataFinal);
        return produtos;
    }

}
