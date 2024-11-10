package com.fatec.les.loungeliterarioapi.mapper.impl;

import com.fatec.les.loungeliterarioapi.dto.ProdutoDTO;
import com.fatec.les.loungeliterarioapi.mapper.ProdutoMapper;
import com.fatec.les.loungeliterarioapi.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapperImpl implements ProdutoMapper {

    @Override
    public Produto toEntity(ProdutoDTO produtoDto) {
        Produto produto = new Produto();
        produto.setTitulo(produtoDto.getTitulo());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setPreco(produtoDto.getPreco());
        produto.setQtdeEstoque(produtoDto.getQtdeEstoque());
        produto.setAtivo(produtoDto.isAtivo());
        produto.setImagem(produtoDto.getImagem());
        produto.setCategoria(produtoDto.getCategoria());
        produto.setAutor(produtoDto.getAutor());
        produto.setEditora(produtoDto.getEditora());
        produto.setIsbn(produtoDto.getIsbn());
        produto.setNumeroPaginas(produtoDto.getNumeroPaginas());
        produto.setPeso(produtoDto.getPeso());
        produto.setAltura(produtoDto.getAltura());
        produto.setLargura(produtoDto.getLargura());
        produto.setProfundidade(produtoDto.getProfundidade());
        produto.setEdicao(produtoDto.getEdicao());
        produto.setAno(produtoDto.getAno());
        produto.setAtivo(produtoDto.isAtivo());
        produto.setCodigo(produtoDto.getCodigo());
        produto.setDataCadastro(produtoDto.getDataCadastro());

        return produto;
    }

    @Override
    public ProdutoDTO toDto(Produto produto) {
        if (produto == null) {
            return null;
        }
        ProdutoDTO dto = new ProdutoDTO();
        dto.setAltura(produto.getAltura());
        dto.setAno(produto.getAno());
        dto.setAtivo(produto.isAtivo());
        dto.setAutor(produto.getAutor());
        dto.setCodigo(produto.getCodigo());
        dto.setDescricao(produto.getDescricao());
        dto.setEdicao(produto.getEdicao());
        dto.setEditora(produto.getEditora());
        dto.setId(produto.getId());
        dto.setImagem(produto.getImagem());
        dto.setIsbn(produto.getIsbn());
        dto.setLargura(produto.getLargura());
        dto.setNumeroPaginas(produto.getNumeroPaginas());
        dto.setPeso(produto.getPeso());
        dto.setPreco(produto.getPreco());
        dto.setProfundidade(produto.getProfundidade());
        dto.setQtdeEstoque(produto.getQtdeEstoque());
        dto.setTitulo(produto.getTitulo());
        dto.setCategoria(produto.getCategoria());
        dto.setDataCadastro(produto.getDataCadastro());
        dto.setAtivo(produto.isAtivo());
        dto.setDataCadastro(produto.getDataCadastro());

        return dto;
    }

}
