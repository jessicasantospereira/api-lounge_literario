package com.fatec.les.loungeliterarioapi.mapper;

import com.fatec.les.loungeliterarioapi.dto.VendaDTO;
import com.fatec.les.loungeliterarioapi.model.Cupom;
import com.fatec.les.loungeliterarioapi.model.CupomTroca;
import com.fatec.les.loungeliterarioapi.model.Venda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface VendaMapper {

    @Mapping(source = "cliente", target = "cliente")
    @Mapping(source = "cartaoDeCredito", target = "cartaoDeCredito")
    @Mapping(source = "itens", target = "itens")
    @Mapping(source = "temCupom", target = "temCupom")
    @Mapping(source = "temTroca", target = "temTroca")
    @Mapping(source = "dataVenda", target = "dataVenda")
    @Mapping(source = "statusVenda", target = "statusVenda")
    @Mapping(source = "parcelas", target = "parcelas")
    @Mapping(source = "valorParcela", target = "valorParcela")
    @Mapping(source = "enderecoEntrega", target = "enderecoEntrega")
    @Mapping(source = "cupom", target = "cupom", qualifiedByName = "toCupomEntity")
    @Mapping(source = "cupomTroca", target = "cupomTroca", qualifiedByName = "toCupomTrocaEntity")
    Venda toEntity(VendaDTO vendaDto);

    @Mapping(source = "cliente", target = "cliente")
    @Mapping(source = "cartaoDeCredito", target = "cartaoDeCredito")
    @Mapping(source = "itens", target = "itens")
    @Mapping(source = "temCupom", target = "temCupom")
    @Mapping(source = "temTroca", target = "temTroca")
    @Mapping(source = "dataVenda", target = "dataVenda")
    @Mapping(source = "statusVenda", target = "statusVenda")
    @Mapping(source = "parcelas", target = "parcelas")
    @Mapping(source = "valorParcela", target = "valorParcela")
    @Mapping(source = "enderecoEntrega", target = "enderecoEntrega")
    @Mapping(source = "cupom", target = "cupom", qualifiedByName = "toCupomDTO")
    @Mapping(source = "cupomTroca", target = "cupomTroca", qualifiedByName = "toCupomTrocaDTO")
    VendaDTO toDto(Venda venda);

    @Named("toCupomDTO")
    default String toCupom(Cupom cupom) {
        if(cupom == null) {
            return null;
        }
        return cupom.getCodigo();
    }

    @Named("toCupomEntity")
    default Cupom toCupomEntity(String cupom) {
        var cupomNovo = new Cupom();
        cupomNovo.setCodigo(cupom);
        return cupomNovo;
    }

    @Named("toCupomTrocaEntity")
    default CupomTroca toCupomTrocaEntity(String cupomTroca) {
        var cupomTrocaNovo = new CupomTroca();
        cupomTrocaNovo.setCodigo(cupomTroca);
        return cupomTrocaNovo;
    }

    @Named("toCupomTrocaDTO")
    default String toCupomTrocaDTO(CupomTroca cupom) {
        if(cupom == null) {
            return null;
        }
        return cupom.getCodigo();
    }

}
