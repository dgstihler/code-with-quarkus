package com.ismoke.application.services;

import com.ismoke.application.dtos.VendaDTO;
import com.ismoke.application.exceptions.CompradorNaoEncontradoException;
import com.ismoke.application.exceptions.ProdutoNaoEncontradoException;
import com.ismoke.application.exceptions.VendedorNaoEncontradoException;
import com.ismoke.domain.models.Comprador;
import com.ismoke.domain.models.Produto;
import com.ismoke.domain.models.Venda;
import com.ismoke.domain.models.Vendedor;
import com.ismoke.domain.repositories.CompradorRepository;
import com.ismoke.domain.repositories.ProdutoRepository;
import com.ismoke.domain.repositories.VendaRepository;
import com.ismoke.domain.repositories.VendedorRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VendaService {

    private final VendaRepository vendaRepository;
    private final CompradorRepository compradorRepository;
    private final VendedorRepository vendedorRepository;
    private final ProdutoRepository produtoRepository;

    public VendaService(VendaRepository vendaRepository, CompradorRepository compradorRepository, VendedorRepository vendedorRepository, ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.compradorRepository = compradorRepository;
        this.vendedorRepository = vendedorRepository;
        this.produtoRepository = produtoRepository;
    }

    public Venda realizarVenda(String cpfComprador, String cnpjVendedor, List<String> idsProdutos) {

        Comprador comprador = compradorRepository.buscarPorCpf(cpfComprador)
            .orElseThrow(() -> new CompradorNaoEncontradoException(cpfComprador));

        Vendedor vendedor = vendedorRepository.buscarPorCnpj(cnpjVendedor)
            .orElseThrow(() -> new VendedorNaoEncontradoException(cnpjVendedor));

        List<Produto> produtos = idsProdutos.stream()
            .map(produtoRepository::buscarPorId)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());

        idsProdutos.stream()
            .filter(id -> produtos.stream().noneMatch(produto -> produto.getId().equals(id)))
            .findFirst()
            .ifPresent(idNaoEncontrado -> {
                throw new ProdutoNaoEncontradoException(idNaoEncontrado);
            });


        Venda venda = new Venda(null, comprador, vendedor, produtos);
        vendaRepository.registrarVenda(venda);
        return venda;
    }

    public List<VendaDTO> listarTodasVendas() {
        return vendaRepository.listarTodas().stream()
            .map(venda -> new VendaDTO(
                venda.getVendedor(),
                venda.getProdutos(),
                venda.getComprador(),
                venda.getId()))
            .collect(Collectors.toList());
    }

    public Optional<VendaDTO> buscarVendaPorId(String id) {
        return vendaRepository.buscarPorId(id).map(venda -> new VendaDTO(
            venda.getVendedor(),
            venda.getProdutos(),
            venda.getComprador(),
            venda.getId()));
    }
}
