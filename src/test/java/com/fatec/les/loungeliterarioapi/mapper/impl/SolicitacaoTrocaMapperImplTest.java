package com.fatec.les.loungeliterarioapi.mapper.impl;

import com.fatec.les.loungeliterarioapi.Utils;
import com.fatec.les.loungeliterarioapi.dto.TrocaDTO;
import com.fatec.les.loungeliterarioapi.mapper.SolicitacaoTrocaMapper;
import com.fatec.les.loungeliterarioapi.model.SolicitacaoTroca;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SolicitacaoTrocaMapperImplTest {

    @Autowired
    private SolicitacaoTrocaMapper solicitacaoTrocaMapper;

    @Test
    @DisplayName("Deve converter para Entity")
    void deveConverterParaEntity() {
        TrocaDTO solicitacaoTroca = Utils.criarSolicitacaoTroca();
         SolicitacaoTroca solicitacaoTrocaEntity = solicitacaoTrocaMapper.toEntity(solicitacaoTroca);
         assertNotNull(solicitacaoTrocaEntity);
         assertEquals(solicitacaoTroca.getStatusSolicitacao(), solicitacaoTrocaEntity.getStatusSolicitacao());
    }

    @Test
    @DisplayName("Deve converter para DTO")
    void deveConverterParaDTO() {
        SolicitacaoTroca solicitacaoTroca = new SolicitacaoTroca();
        TrocaDTO solicitacaoTrocaDTO = solicitacaoTrocaMapper.toDto(solicitacaoTroca);
        assertNull(solicitacaoTrocaDTO);
    }

}