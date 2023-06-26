package com.fatec.les.loungeliterarioapi.repository;

import com.fatec.les.loungeliterarioapi.model.Contato;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    @Query(" SELECT ct FROM Contato ct LEFT JOIN Cliente c ON c.idCliente=ct.cliente.idCliente WHERE ct.cliente.idCliente=:id ")
    Optional<List<Contato>> findByCliente(Long id);
}
