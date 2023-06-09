package com.fatec.les.loungeliterarioapi.services;

import com.fatec.les.loungeliterarioapi.dto.VendaDTO;
import com.fatec.les.loungeliterarioapi.mapper.VendaMapper;
import com.fatec.les.loungeliterarioapi.model.Venda;
import com.fatec.les.loungeliterarioapi.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {
    @Autowired
    private VendaRepository repository;
    @Autowired
    private VendaMapper mapper;
    @Autowired
    private CupomService cupomService;


    public Venda salvarVenda(VendaDTO venda) {
        Venda novaVenda = mapper.toEntity(venda);
        if (venda.getTemCupom()) {
            novaVenda.setCupom(cupomService.buscarCupom(venda.getCupom()));
        }
        return repository.save(novaVenda);
    }

    public long getItens() {
        return repository.count();
    }
}
