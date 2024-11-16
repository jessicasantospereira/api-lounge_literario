package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.*;
import com.fatec.les.loungeliterarioapi.mapper.VendaMapper;
import com.fatec.les.loungeliterarioapi.model.*;
import com.fatec.les.loungeliterarioapi.repository.*;

import java.time.LocalDate;
import java.util.*;

import com.fatec.les.loungeliterarioapi.services.VendaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class VendaServiceImpl implements VendaService {

    private final VendaRepository repository;
    private final VendaMapper mapper;
    private final CupomRepository cupomRepository;
    private final EnderecoRepository enderecoRepository;
    private final CupomTrocaRepository cupomTrocaRepository;
    private final SolicitacaoTrocaRepository solicitacaoTrocaRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    public Venda salvarVenda(VendaDTO venda) {
        log.info("Salvando venda {}", venda.getEnderecoEntrega());
        Venda novaVenda = mapper.toEntity(venda);

        if (venda.getTemCupom() != null && venda.getTemCupom()) {
            novaVenda.setCupom(cupomRepository.findByCodigo(venda.getCupom()));
        }
        if(venda.getTemTroca() != null && venda.getTemTroca()){
            log.info("Tem troca {}", venda.getCupomTroca());
            CupomTroca cupomTroca = cupomTrocaRepository.findByCodigo(venda.getCupomTroca());
            cupomTroca.setUtilizado(true);
            SolicitacaoTroca troca = solicitacaoTrocaRepository.findById(cupomTroca.getSolicitacaoTroca().getIdSolicitacao()).get();
            troca.setStatusSolicitacao(StatusSolicitacaoTroca.TROCA_EFETUADA);
            solicitacaoTrocaRepository.save(cupomTroca.getSolicitacaoTroca());
            cupomTrocaRepository.save(cupomTroca);
            novaVenda.setCupomTroca(cupomTroca);
//            var novoValor = troca.getValor().subtract(venda.getItens().stream().map(item -> {
//                return BigDecimal.valueOf(item.getQuantidade()).multiply(item.getProduto().getPreco());
//            });
//            TrocaDTO novaTroca = TrocaDTO.builder().motivo("Sobra de cupom").idCliente(Math.toIntExact(troca.getCliente().getIdCliente())).idProduto(Math.toIntExact(troca.getProduto().getId())).quantidade(troca.getQuantidade()).valor(novoValor).build();
        }

        Endereco end = enderecoRepository.findByIdEndereco(venda.getEnderecoEntrega().getIdEndereco()).get();
        novaVenda.setStatusVenda(StatusVenda.EM_PROCESSAMENTO);
        novaVenda.setDataVenda(LocalDate.now());
        novaVenda.setEnderecoEntrega(end);
        return repository.save(novaVenda);

    }
    @Override
    public long getItens() {
        return repository.count();
    }

    @Override
    public List<Venda> listarVendasPorCliente(Long id) {
       return repository.findAllByIdCliente(id).get();
    }

    @Override
    public Page<ResponseVendaDTO> buscarTodasVendas(Pageable pageable) {
        Page<Venda> vendas =  repository.findAll(pageable);
        Page<ResponseVendaDTO> responseDTO = vendas.map(ResponseVendaDTO::new);
        return responseDTO;
    }

    @Override
    public ResponseVendaDTO atualizarVenda(Long id, String status) {
       Venda venda = repository.findById(id).get();
       venda.setStatusVenda(StatusVenda.getStatusVenda(status));
       repository.save(venda);
       ResponseVendaDTO responseVendaDTO = new ResponseVendaDTO(venda);
       return responseVendaDTO;
    }

    @Override
    public List<ProdutoResponseDTO> buscarVendasPorData(String dataInicial, String dataFinal) {
        LocalDate dataInicialConvertida = LocalDate.parse(dataInicial.trim());
        LocalDate dataFinalConvertida = LocalDate.parse(dataFinal.trim());

        List<Object[]> salesData = produtoRepository.findSalesDataByMonth(dataInicialConvertida, dataFinalConvertida);

        List<ProdutoResponseDTO> produtos = new ArrayList<>();

        for (Object[] row : salesData) {
            String productName = (String) row[0];
            String month = String.valueOf(row[1]);
            Long quantity = (Long) row[2];

            // Verifica se o produto já está na lista de produtos
            Optional<ProdutoResponseDTO> existingProduct = produtos.stream()
                    .filter(p -> p.getProductName().equals(productName))
                    .findFirst();

            if (existingProduct.isPresent()) {
                // Adiciona a venda ao produto existente na lista
                ProdutoResponseDTO product = existingProduct.get();
                product.addSale(month, quantity);
            } else {
                // Cria um novo produto na lista
                ProdutoResponseDTO newProduct = new ProdutoResponseDTO();
                newProduct.setProductName(productName);
                newProduct.addSale(month, quantity);
                produtos.add(newProduct);
            }
        }

        return produtos;
    }
}
