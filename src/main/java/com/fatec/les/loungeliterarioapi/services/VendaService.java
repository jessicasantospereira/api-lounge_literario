package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.dto.VendaDTO;
import com.fatec.les.loungeliterarioapi.mapper.VendaMapper;
import com.fatec.les.loungeliterarioapi.model.CupomTroca;
import com.fatec.les.loungeliterarioapi.model.Venda;
import com.fatec.les.loungeliterarioapi.repository.CupomRepository;
import com.fatec.les.loungeliterarioapi.repository.CupomTrocaRepository;
import com.fatec.les.loungeliterarioapi.repository.VendaRepository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.CustomRepositoryImplementationDetector;
import org.springframework.stereotype.Service;

@Service
public class VendaService {
    @Autowired
    private VendaRepository repository;
    @Autowired
    private VendaMapper mapper;
    @Autowired
    private CupomService cupomService;
    @Autowired
    private CupomRepository cupomRepository;
    @Autowired
    private CupomTrocaRepository trocaRepository;

    public Venda salvarVenda(VendaDTO venda) {
        Venda novaVenda = mapper.toEntity(venda);
        UUID uuid = UUID.randomUUID();

        if (venda.getTemCupom()) {
            novaVenda.setCupom(cupomRepository.findByCodigo(venda.getCupom()));
        }
        CupomTroca troca = new CupomTroca();
        troca.setCodigo(uuid.toString());
        troca.setDataValidade(LocalDate.now().plusDays(30));
        trocaRepository.save(troca);

        novaVenda.setCupomTroca(troca);
        
        return repository.save(novaVenda);
    }

    public long getItens() {
        return repository.count();
    }
}
