package com.fatec.les.loungeliterarioapi.repository;

import com.fatec.les.loungeliterarioapi.dto.VendaPorMesDTO;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.model.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    @Query(" SELECT v FROM Venda v LEFT JOIN Cliente c ON c.idCliente = v.cliente.idCliente WHERE v.cliente.idCliente = :id")
    Optional<List<Venda>> findAllByIdCliente(Long id);

    Page<Venda> findAll(Pageable pageable);

}
