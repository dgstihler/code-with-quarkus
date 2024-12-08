package com.ismoke.application.services;

import com.ismoke.application.dtos.ProdutoDTO;
import com.ismoke.domain.models.Produto;
import com.ismoke.domain.repositories.ProdutoRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void criarProduto(String nome, BigDecimal preco, BigDecimal quantidade) {
        Produto produto = new Produto(null, nome, preco, quantidade);
        produtoRepository.salvar(produto);
    }

    public List<ProdutoDTO> listarTodosProdutos() {
        return produtoRepository.listarTodos().stream().map(produto ->
                new ProdutoDTO(
                    produto.getId(),
                    produto.getNome(),
                    produto.getPreco(),
                    produto.getQuantidade()))
            .toList();
    }

    public Optional<ProdutoDTO> buscarProdutoPorId(String id) {
        return produtoRepository.buscarPorId(id).map(produto ->
            new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade()));
    }

    public void deletarProduto(String id) {
        produtoRepository.deletar(id);
    }

}
