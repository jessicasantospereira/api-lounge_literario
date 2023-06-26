package com.fatec.les.loungeliterarioapi.repository;

import com.fatec.les.loungeliterarioapi.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    @Query(" SELECT e FROM Endereco e LEFT JOIN Cliente c ON c.idCliente=e.cliente.idCliente WHERE e.cliente.idCliente=:id ")
    Optional<List<Endereco>> findByCliente(Long id);


}
