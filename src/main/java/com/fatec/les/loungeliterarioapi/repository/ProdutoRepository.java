package com.fatec.les.loungeliterarioapi.repository;

import com.fatec.les.loungeliterarioapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
