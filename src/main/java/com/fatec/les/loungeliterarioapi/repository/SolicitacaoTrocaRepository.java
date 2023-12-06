package com.fatec.les.loungeliterarioapi.repository;

import com.fatec.les.loungeliterarioapi.model.SolicitacaoTroca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SolicitacaoTrocaRepository  extends JpaRepository<SolicitacaoTroca, Long> {
    @Query(" SELECT st FROM SolicitacaoTroca st WHERE st.cliente.idCliente=:id ")
    List<SolicitacaoTroca> findAllByIdCliente(Long id);
}
