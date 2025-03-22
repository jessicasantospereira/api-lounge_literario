package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.exceptions.DomainException;
import com.fatec.les.loungeliterarioapi.model.Cupom;
import com.fatec.les.loungeliterarioapi.repository.CupomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CupomServiceTest {

    @InjectMocks
    private CupomService cupomService;

    @Mock
    private CupomRepository cupomRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve cadastrar um cupom")
    void cadastrarCupom() {
        Cupom cupom = Cupom.builder().idCupom(1L).codigo("TESTE20").valor(20.00).build();
        when(cupomRepository.findByCodigo(cupom.getCodigo())).thenReturn(null);
        when(cupomRepository.save(cupom)).thenReturn(cupom);
        var response = cupomService.cadastrar(cupom);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Deve retornar erro ao tentar cadastrar um cupom já existente")
    void cadastrarCupomExistente() {
        Cupom cupom = Cupom.builder().idCupom(1L).codigo("TESTE20").valor(20.00).build();
        when(cupomRepository.findByCodigo(cupom.getCodigo())).thenReturn(cupom);
        assertThrows(DomainException.class, () -> cupomService.cadastrar(cupom));
    }

    @Test
    @DisplayName("Deve buscar um cupom por id")
    void buscarCupomPorId() {
        Cupom cupom = Cupom.builder().idCupom(1L).codigo("TESTE20").valor(20.00).build();
        when(cupomRepository.findById(cupom.getIdCupom())).thenReturn(Optional.of(cupom));
        var response = cupomService.buscarCupomPorId(cupom.getIdCupom());
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Deve retornar erro ao tentar buscar um cupom por id inexistente")
    void buscarCupomPorIdInexistente() {
        Long idInexistente = 1L;
        when(cupomRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThrows(DomainException.class, () -> cupomService.deletarCupom(idInexistente));
    }

    @Test
    @DisplayName("Deve atualizar um cupom")
    void atualizarCupom() {
        Cupom cupom = Cupom.builder().idCupom(1L).codigo("TESTE20").valor(20.00).build();
        when(cupomRepository.findById(cupom.getIdCupom())).thenReturn(Optional.of(cupom));
        when(cupomRepository.save(cupom)).thenReturn(cupom);
        var response = cupomService.atualizarCupom(cupom);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Deve retornar erro ao tentar atualizar um cupom inexistente")
    void atualizarCupomInexistente() {
        Cupom cupom = Cupom.builder().idCupom(1L).codigo("TESTE20").valor(20.00).build();
        when(cupomRepository.findById(cupom.getIdCupom())).thenReturn(Optional.empty());
        assertThrows(DomainException.class, () -> cupomService.atualizarCupom(cupom));
    }

    @Test
    @DisplayName("Deve deletar um cupom")
    void deletarCupom() {
        Cupom cupom = Cupom.builder().idCupom(1L).codigo("TESTE20").valor(20.00).build();
        when(cupomRepository.findById(cupom.getIdCupom())).thenReturn(Optional.of(cupom));
        assertDoesNotThrow(() -> cupomService.deletarCupom(cupom.getIdCupom()));
    }

    @Test
    @DisplayName("Deve retornar erro ao tentar deletar um cupom inexistente")
    void deletarCupomInexistente() {
        Long idInexistente = 1L;
        when(cupomRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThrows(DomainException.class, () -> cupomService.deletarCupom(idInexistente));
    }

    @Test
    @DisplayName("Deve buscar um cupom por código")
    void buscarCupom() {
        Cupom cupom = Cupom.builder().idCupom(1L).codigo("TESTE20").dataValidade(LocalDate.of(2025,12,25)).valor(20.00).build();
        when(cupomRepository.findByCodigo(cupom.getCodigo())).thenReturn(cupom);
        var response = cupomService.buscarCupom(cupom.getCodigo());
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Deve retornar erro ao tentar buscar um cupom inexistente")
    void buscarCupomInexistente() {
        String codigoInexistente = "TESTE20";
        when(cupomRepository.findByCodigo(codigoInexistente)).thenReturn(null);
        assertThrows(DomainException.class, () -> cupomService.buscarCupom(codigoInexistente));
    }

    @Test
    @DisplayName("Deve retornar erro ao tentar buscar um cupom com data de validade expirada")
    void buscarCupomDataExpirada() {
        Cupom cupom = Cupom.builder().idCupom(1L).codigo("TESTE20").dataValidade(LocalDate.of(2021,12,25)).valor(20.00).build();
        when(cupomRepository.findByCodigo(cupom.getCodigo())).thenReturn(cupom);
        try {
            cupomService.buscarCupom(cupom.getCodigo());
        } catch (DomainException e) {
            assertEquals(HttpStatusCode.valueOf(400), e.getStatus());
            assertEquals("Cupom expirado", e.getMessage());
        }
    }

    @Test
    @DisplayName("Deve buscar todos os cupons")
    void buscarCupons() {
        when(cupomRepository.findAll()).thenReturn(null);
        var response = cupomService.buscarCupons();
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    @DisplayName("Deve buscar cupom por id")
    void buscarPorId() {
        Cupom cupom = Cupom.builder().idCupom(1L).codigo("TESTE20").valor(20.00).build();
        when(cupomRepository.findById(cupom.getIdCupom())).thenReturn(Optional.of(cupom));
        var response = cupomService.buscarPorId(cupom.getIdCupom());
        assertNotNull(response);
    }
}