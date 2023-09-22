package com.fatec.les.loungeliterarioapi.repository;

import com.fatec.les.loungeliterarioapi.model.CartaoDeCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartaoRepository  extends JpaRepository<CartaoDeCredito, Long> {
    @Query(" SELECT cc FROM CartaoDeCredito cc LEFT JOIN Cliente c ON c.idCliente=cc.cliente.idCliente WHERE cc.cliente.idCliente=:id ")
    Optional<List<CartaoDeCredito>> findByCliente(Long id);
}