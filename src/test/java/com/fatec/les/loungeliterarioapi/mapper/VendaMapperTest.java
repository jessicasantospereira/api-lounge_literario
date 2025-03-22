package com.fatec.les.loungeliterarioapi.mapper;

import com.fatec.les.loungeliterarioapi.Utils;
import com.fatec.les.loungeliterarioapi.dto.VendaDTO;
import com.fatec.les.loungeliterarioapi.model.Venda;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VendaMapperTest {

    @Autowired
    private VendaMapper vendaMapper;

    @Test
    @DisplayName("Deve converter para Entity")
    void deveConverterParaEntity() {
        VendaDTO venda = Utils.criarVendaDTO();
        Venda vendaEntity = vendaMapper.toEntity(venda);
        assertNotNull(vendaEntity);
        assertEquals(venda.getStatusVenda(), vendaEntity.getStatusVenda());
    }

    @Test
    @DisplayName("Deve converter para DTO")
    void deveConverterParaDTO() {
        Venda venda = Utils.criarVenda();
        VendaDTO vendaDTO = vendaMapper.toDto(venda);
        assertNotNull(vendaDTO);
        assertEquals(venda.getStatusVenda(), vendaDTO.getStatusVenda());
    }
}