package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.ResponseTrocaDTO;
import com.fatec.les.loungeliterarioapi.dto.TrocaDTO;
import com.fatec.les.loungeliterarioapi.exceptions.DomainException;
import com.fatec.les.loungeliterarioapi.mapper.SolicitacaoTrocaMapper;
import com.fatec.les.loungeliterarioapi.model.*;
import com.fatec.les.loungeliterarioapi.repository.ClienteRepository;
import com.fatec.les.loungeliterarioapi.repository.CupomTrocaRepository;
import com.fatec.les.loungeliterarioapi.repository.ProdutoRepository;
import com.fatec.les.loungeliterarioapi.repository.SolicitacaoTrocaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TrocaServiceImplTest {

    @InjectMocks
    TrocaServiceImpl trocaService;

    @Mock
    SolicitacaoTrocaRepository repository;

    @Mock
    SolicitacaoTrocaMapper mapper;

    @Mock
    ClienteRepository clienteRepository;

    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    CupomTrocaRepository cupomTrocaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Deve solicitar uma troca")
    void solicitarTroca() {
        TrocaDTO trocaDTO = new TrocaDTO();
        trocaDTO.setIdCliente(1);
        trocaDTO.setIdProduto(1);

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);

        Produto produto = new Produto();
        produto.setId(1L);

        SolicitacaoTroca solicitacaoTroca = new SolicitacaoTroca();
        solicitacaoTroca.setCliente(cliente);
        solicitacaoTroca.setProduto(produto);
        solicitacaoTroca.setDataSolicitacao(LocalDate.now());

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(mapper.toEntity(trocaDTO)).thenReturn(solicitacaoTroca);
        when(repository.save(solicitacaoTroca)).thenReturn(solicitacaoTroca);

        SolicitacaoTroca response = trocaService.solicitarTroca(trocaDTO);

        assertNotNull(response);
        assertNotNull(response.getCliente());
        assertNotNull(response.getProduto());
        assertNotNull(response.getDataSolicitacao());
    }

    @Test
    @DisplayName("Deve buscar um cupom")
    void buscarCupom() {
        String codigo = "123456";
        CupomTroca cupom = new CupomTroca();
        cupom.setCodigo("CUPOM123");
        cupom.setIdCupomTroca(1L);
        cupom.setDataValidade(LocalDate.parse("2025-12-31"));
        when(cupomTrocaRepository.findByCodigo(codigo)).thenReturn(cupom);
        var response = trocaService.buscarCupom(codigo);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Deve lançar uma exceção caso cupom não encontrado")
    void buscarCupomNaoEncontrado() {
        String codigo = "123456";
        when(cupomTrocaRepository.findByCodigo(codigo)).thenReturn(null);
        assertThrows(DomainException.class, ()-> trocaService.buscarCupom(codigo));
    }

    @Test
    @DisplayName("Deve lançar uma exceção caso cupom já utilizado")
    void buscarCupomJaUtilizado() {
        String codigo = "123456";
        CupomTroca cupom = new CupomTroca();
        cupom.setCodigo("CUPOM123");
        cupom.setIdCupomTroca(1L);
        cupom.setDataValidade(LocalDate.parse("2025-12-31"));
        cupom.setUtilizado(true);
        when(cupomTrocaRepository.findByCodigo(codigo)).thenReturn(cupom);
        assertThrows(DomainException.class, ()-> trocaService.buscarCupom(codigo));
    }

    @Test
    @DisplayName("Deve lançar uma exceção caso cupom expirado")
    void buscarCupomExpirado() {
        String codigo = "123456";
        CupomTroca cupom = new CupomTroca();
        cupom.setCodigo("CUPOM123");
        cupom.setIdCupomTroca(1L);
        cupom.setDataValidade(LocalDate.parse("2024-12-31"));
        cupom.setUtilizado(true);
        when(cupomTrocaRepository.findByCodigo(codigo)).thenReturn(cupom);
        assertThrows(DomainException.class, ()-> trocaService.buscarCupom(codigo));
    }

    @Test
    @DisplayName("Deve buscar trocas por cliente")
    void buscarTrocasPorCliente() {
        Long idCliente = 1L;

        SolicitacaoTroca solicitacaoTroca = new SolicitacaoTroca();
        solicitacaoTroca.setIdSolicitacao(1L);
        solicitacaoTroca.setCliente(Cliente.builder().idCliente(idCliente).build());
        solicitacaoTroca.setProduto(Produto.builder().id(1L).build());
        solicitacaoTroca.setDataSolicitacao(LocalDate.now());
        solicitacaoTroca.setStatusSolicitacao(StatusSolicitacaoTroca.TROCA_EFETUADA);
        solicitacaoTroca.setQuantidade(1);
        solicitacaoTroca.setValor(BigDecimal.valueOf(100.0));
        solicitacaoTroca.setMotivo("Defeito");

        CupomTroca cupomTroca = new CupomTroca();
        cupomTroca.setCodigo("CUPOM123");
        cupomTroca.setSolicitacaoTroca(solicitacaoTroca);

        when(repository.findAllByIdCliente(idCliente)).thenReturn(Collections.singletonList(solicitacaoTroca));
        when(cupomTrocaRepository.findBySolicitacaoTroca(solicitacaoTroca)).thenReturn(cupomTroca);

        ResponseEntity<?> response = trocaService.buscarTrocasPorCliente(idCliente.toString());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<ResponseTrocaDTO> responseBody = (List<ResponseTrocaDTO>) response.getBody();
        assertNotNull(responseBody);
        assertEquals(1, responseBody.size());
        assertEquals("CUPOM123", responseBody.get(0).getCupomTroca().getCodigo());
    }

    @Test
    @DisplayName("Deve buscar trocas por cliente else")
    void buscarTrocasPorClienteElse() {
        Long idCliente = 1L;

        SolicitacaoTroca solicitacaoTroca = new SolicitacaoTroca();
        solicitacaoTroca.setIdSolicitacao(1L);
        solicitacaoTroca.setCliente(Cliente.builder().idCliente(idCliente).build());
        solicitacaoTroca.setProduto(Produto.builder().id(1L).build());
        solicitacaoTroca.setDataSolicitacao(LocalDate.now());
        solicitacaoTroca.setStatusSolicitacao(StatusSolicitacaoTroca.EM_TROCA);
        solicitacaoTroca.setQuantidade(1);
        solicitacaoTroca.setValor(BigDecimal.valueOf(100.0));
        solicitacaoTroca.setMotivo("Defeito");

        CupomTroca cupomTroca = new CupomTroca();
        cupomTroca.setCodigo("CUPOM123");
        cupomTroca.setSolicitacaoTroca(solicitacaoTroca);

        when(repository.findAllByIdCliente(idCliente)).thenReturn(Collections.singletonList(solicitacaoTroca));
        when(cupomTrocaRepository.findBySolicitacaoTroca(solicitacaoTroca)).thenReturn(cupomTroca);

        ResponseEntity<?> response = trocaService.buscarTrocasPorCliente(idCliente.toString());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<ResponseTrocaDTO> responseBody = (List<ResponseTrocaDTO>) response.getBody();
        assertNotNull(responseBody);
        assertEquals(1, responseBody.size());
    }

    @Test
    @DisplayName("Deve buscar trocas por cliente e retornar vazio")
    void buscarTrocasPorClienteVazio() {
        Long idCliente = 1L;
        when(repository.findAllByIdCliente(idCliente)).thenReturn(Collections.emptyList());
        var response = trocaService.buscarTrocasPorCliente(idCliente.toString());
        assertNull(response);
    }

    @Test
    @DisplayName("Deve buscar todas as solicitações de troca")
    void buscarTodasSolicitacoesDeTroca() {
        SolicitacaoTroca solicitacaoTroca = new SolicitacaoTroca();
        when(repository.findAll()).thenReturn(Collections.singletonList(solicitacaoTroca));

        ResponseEntity<List<SolicitacaoTroca>> response = trocaService.buscarTrocas();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        List<SolicitacaoTroca> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(1, responseBody.size());
        assertEquals(solicitacaoTroca, responseBody.get(0));
    }


    @Test
    @DisplayName("Deve atualizar a troca e gerar um cupom se o status for ITENS_RECEBIDOS")
    void atualizarTroca() {
        Long id = 1L;
        String status = "ITENS_RECEBIDOS";

        SolicitacaoTroca solicitacaoTroca = new SolicitacaoTroca();
        solicitacaoTroca.setIdSolicitacao(id);
        solicitacaoTroca.setCliente(Cliente.builder().idCliente(1L).build());
        solicitacaoTroca.setProduto(Produto.builder().id(1L).build());
        solicitacaoTroca.setDataSolicitacao(LocalDate.now());
        solicitacaoTroca.setValor(BigDecimal.valueOf(100.0));
        solicitacaoTroca.setStatusSolicitacao(StatusSolicitacaoTroca.ITENS_RECEBIDOS);

        when(repository.findById(id)).thenReturn(Optional.of(solicitacaoTroca));
        when(cupomTrocaRepository.save(any(CupomTroca.class))).thenReturn(new CupomTroca());

        TrocaDTO response = trocaService.atualizarTroca(id, status);

        assertNotNull(response);
        assertEquals(StatusSolicitacaoTroca.ITENS_RECEBIDOS, response.getStatusSolicitacao());
        verify(cupomTrocaRepository, times(1)).save(any(CupomTroca.class));
        verify(repository, times(1)).save(solicitacaoTroca);

    }
}