package com.fatec.les.loungeliterarioapi.repository;

import com.fatec.les.loungeliterarioapi.dto.ProdutoResponseDTO;
import com.fatec.les.loungeliterarioapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

        @Query("SELECT p.titulo as productName, DATE_FORMAT(v.dataVenda, '%Y-%m') as month, SUM(iv.quantidade) as quantity " +
                "FROM ItemVenda iv " +
                "JOIN iv.venda v " +
                "JOIN iv.produto p " +
                "WHERE v.dataVenda BETWEEN :startDate AND :endDate " +
                "GROUP BY p.titulo, DATE_FORMAT(v.dataVenda, '%Y-%m')")
        List<Object[]> findSalesDataByMonth(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


        @Query("SELECT p.titulo as productName, DATE_FORMAT(v.dataVenda, '%Y-%m') as month, SUM(iv.quantidade) as quantity " +
                "FROM ItemVenda iv " +
                "JOIN iv.venda v " +
                "JOIN iv.produto p " +
                "WHERE v.dataVenda BETWEEN :startDate AND :endDate " +
                "GROUP BY p.titulo, DATE_FORMAT(v.dataVenda, '%Y-%m')")
        List<ProdutoResponseDTO> findQuantidadeVendidaPorMesDTO(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
