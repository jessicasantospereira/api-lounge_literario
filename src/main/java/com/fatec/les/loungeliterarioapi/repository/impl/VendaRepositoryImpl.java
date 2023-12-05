package com.fatec.les.loungeliterarioapi.repository.impl;

import com.fatec.les.loungeliterarioapi.dto.VendaPorMesDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VendaRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<VendaPorMesDTO> findAllByDataVenda(){
        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT MONTH(v.dataVenda), YEAR(v.dataVenda), COUNT(v) " +
                        "FROM Venda v " +
                        "GROUP BY MONTH(v.dataVenda), YEAR(v.dataVenda)",
                Object[].class
        );
        List<Object[]> results = query.getResultList();
        List<VendaPorMesDTO> vendasPorMes = new ArrayList<>();

        for (Object[] result : results) {
            Integer mes = (Integer) result[0];
            Integer ano = (Integer) result[1];
            Long totalVendas = (Long) result[2];

            VendaPorMesDTO vendaPorMesDTO = new VendaPorMesDTO(mes, ano, totalVendas);
            vendasPorMes.add(vendaPorMesDTO);
        }

        return vendasPorMes;
    }

}
