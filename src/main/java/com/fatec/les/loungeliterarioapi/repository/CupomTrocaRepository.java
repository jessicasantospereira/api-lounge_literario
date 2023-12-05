package com.fatec.les.loungeliterarioapi.repository;

import com.fatec.les.loungeliterarioapi.model.Cupom;
import com.fatec.les.loungeliterarioapi.model.SolicitacaoTroca;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.les.loungeliterarioapi.model.CupomTroca;

public interface CupomTrocaRepository extends JpaRepository<CupomTroca, Long> {

    CupomTroca findBySolicitacaoTroca(SolicitacaoTroca troca);

    CupomTroca findByCodigo(String codigo);
}
