package com.fatec.les.loungeliterarioapi.repository;

import com.fatec.les.loungeliterarioapi.model.Produto;
import com.fatec.les.loungeliterarioapi.projection.VendasProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

        @Query("SELECT p.titulo, v.dataVenda as mes, SUM(iv.quantidade) as quantidade " +
                "FROM ItemVenda iv " +
                "JOIN iv.venda v " +
                "JOIN iv.produto p " +
                "WHERE v.dataVenda BETWEEN :dataInicial AND :dataFinal " +
                "GROUP BY p.titulo, v.dataVenda")
        List<Object[]> findSalesDataByMonth(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);

        @Query("SELECT p.titulo as nome, v.dataVenda as mes, SUM(iv.quantidade) as quantidade " +
                "FROM ItemVenda iv " +
                "JOIN iv.venda v " +
                "JOIN iv.produto p " +
                "WHERE v.dataVenda BETWEEN :dataInicial AND :dataFinal " +
                "GROUP BY p.titulo, v.dataVenda")
        List<VendasProjection> findVendasByDataVenda(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);


}
