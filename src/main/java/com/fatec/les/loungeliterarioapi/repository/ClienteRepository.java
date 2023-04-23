package com.fatec.les.loungeliterarioapi.repository;

import com.fatec.les.loungeliterarioapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
