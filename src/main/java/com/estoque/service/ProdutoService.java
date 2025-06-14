package com.estoque.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoque.dto.ProdutoDTO;
import com.estoque.exception.EstoqueInsuficienteException;
import com.estoque.exception.ProdutoNotFoundException;
import com.estoque.model.Produto;
import com.estoque.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	@Autowired
    private ProdutoRepository produtoRepository; 

    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
    }

    public ProdutoDTO criar(ProdutoDTO produtoDTO) {
        Produto produto = toEntity(produtoDTO);
        produto.setId(null);
        return toDTO(produtoRepository.save(produto));
    }

    public ProdutoDTO atualizar(Long id, ProdutoDTO produtoDTO) {
        if (!produtoRepository.existsById(id)) {
            throw new ProdutoNotFoundException(id);
        }
        Produto produto = toEntity(produtoDTO);
        produto.setId(id);
        return toDTO(produtoRepository.save(produto));
    }

    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ProdutoNotFoundException(id);
        }
        produtoRepository.deleteById(id);
    }

    public void verificarEstoque(Long produtoId, Integer quantidade) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNotFoundException(produtoId));
        
        if (produto.getQuantidadeEstoque() < quantidade) {
            throw new EstoqueInsuficienteException(produtoId, produto.getQuantidadeEstoque(), quantidade);
        }
    }

    private ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setCodigo(produto.getCodigo());
        dto.setDescricao(produto.getDescricao());
        dto.setTipo(produto.getTipo());
        dto.setValorFornecedor(produto.getValorFornecedor());
        dto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        return dto;
    }

    private Produto toEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setCodigo(dto.getCodigo());
        produto.setDescricao(dto.getDescricao());
        produto.setTipo(dto.getTipo());
        produto.setValorFornecedor(dto.getValorFornecedor());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        return produto;
    }
}