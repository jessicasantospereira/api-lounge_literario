package com.fatec.les.loungeliterarioapi.services.impl;

import com.fatec.les.loungeliterarioapi.dto.ResponseVendaDTO;
import com.fatec.les.loungeliterarioapi.dto.VendaDTO;
import com.fatec.les.loungeliterarioapi.mapper.VendaMapper;
import com.fatec.les.loungeliterarioapi.model.CupomTroca;
import com.fatec.les.loungeliterarioapi.model.Endereco;
import com.fatec.les.loungeliterarioapi.model.StatusVenda;
import com.fatec.les.loungeliterarioapi.model.Venda;
import com.fatec.les.loungeliterarioapi.repository.CupomRepository;
import com.fatec.les.loungeliterarioapi.repository.CupomTrocaRepository;
import com.fatec.les.loungeliterarioapi.repository.EnderecoRepository;
import com.fatec.les.loungeliterarioapi.repository.VendaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fatec.les.loungeliterarioapi.services.VendaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class VendaServiceImpl implements VendaService {
    private final VendaRepository repository;
    private final VendaMapper mapper;
    private final CupomRepository cupomRepository;
    private final CupomTrocaRepository trocaRepository;
    private final EnderecoRepository enderecoRepository;
    public VendaServiceImpl(VendaRepository repository, VendaMapper mapper, CupomRepository cupomRepository, CupomTrocaRepository trocaRepository, EnderecoRepository enderecoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.cupomRepository = cupomRepository;
        this.trocaRepository = trocaRepository;
        this.enderecoRepository = enderecoRepository;
    }
    @Override
    public Venda salvarVenda(VendaDTO venda) {
        log.info("Salvando venda {}", venda.getEnderecoEntrega());
        Venda novaVenda = mapper.toEntity(venda);
        UUID uuid = UUID.randomUUID();

        if (venda.getTemCupom()) {
            novaVenda.setCupom(cupomRepository.findByCodigo(venda.getCupom()));
        }
        CupomTroca troca = new CupomTroca();
        troca.setCodigo(uuid.toString());
        troca.setDataValidade(LocalDate.now().plusDays(30));
        trocaRepository.save(troca);
        Endereco end = enderecoRepository.findByIdEndereco(venda.getEnderecoEntrega().getIdEndereco()).get();
        novaVenda.setCupomTroca(troca);
        novaVenda.setDataVenda(LocalDate.now());
        novaVenda.setEnderecoEntrega(end);
        return repository.save(novaVenda);
    }
    @Override
    public long getItens() {
        return repository.count();
    }
    @Override
    public List<Venda> listarVendasPorCliente(Long id) {
       return repository.findAllByIdCliente(id).get();
    }

    @Override
    public Page<ResponseVendaDTO> buscarTodasVendas(Pageable pageable) {
        Page<Venda> vendas =  repository.findAll(pageable);
        Page<ResponseVendaDTO> responseDTO = vendas.map(venda -> new ResponseVendaDTO(venda));
        return responseDTO;
    }

    @Override
    public ResponseVendaDTO atualizarVenda(Long id, String status) {
       Venda venda = repository.findById(id).get();
       venda.setStatusVenda(StatusVenda.getStatusVenda(status));
       repository.save(venda);
       ResponseVendaDTO responseVendaDTO = new ResponseVendaDTO(venda);
       return responseVendaDTO;
    }
}
