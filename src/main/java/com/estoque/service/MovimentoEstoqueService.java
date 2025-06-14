package com.estoque.service;

import com.estoque.dto.LucroProdutoDTO;
import com.estoque.dto.MovimentoEstoqueDTO;
import com.estoque.dto.ProdutoTipoDTO;
import com.estoque.exception.EstoqueInsuficienteException;
import com.estoque.exception.ProdutoNotFoundException;
import com.estoque.model.MovimentoEstoque;
import com.estoque.model.Produto;
import com.estoque.repository.MovimentoEstoqueRepository;
import com.estoque.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimentoEstoqueService {
	
	@Autowired
    private MovimentoEstoqueRepository movimentoRepository;
	
	@Autowired
    private ProdutoRepository produtoRepository;
	
	@Autowired
    private ProdutoService produtoService;

    @Transactional
    public MovimentoEstoqueDTO criar(MovimentoEstoqueDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new ProdutoNotFoundException(dto.getProdutoId()));

        if (dto.getTipo() == MovimentoEstoque.TipoMovimentacao.SAIDA) {
            produtoService.verificarEstoque(dto.getProdutoId(), dto.getQuantidade());
        }

        MovimentoEstoque movimento = new MovimentoEstoque();
        movimento.setProduto(produto);
        movimento.setTipo(dto.getTipo());
        movimento.setValorVenda(dto.getValorVenda());
        movimento.setQuantidade(dto.getQuantidade());

        if (movimento.getTipo() == MovimentoEstoque.TipoMovimentacao.ENTRADA) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + movimento.getQuantidade());
        } else {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - movimento.getQuantidade());
        }
        produtoRepository.save(produto);

        return toDTO(movimentoRepository.save(movimento));
    }

    public List<MovimentoEstoqueDTO> listarPorProduto(Long produtoId) {
        return movimentoRepository.findByProdutoId(produtoId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ProdutoTipoDTO> consultarPorTipo(Produto.TipoProduto tipo) {
        return produtoRepository.findByTipo(tipo).stream()
                .map(produto -> {
                    ProdutoTipoDTO dto = new ProdutoTipoDTO();
                    dto.setTipo(produto.getTipo().name());
                    dto.setQuantidadeDisponivel(produto.getQuantidadeEstoque());
                    
                    int saida = movimentoRepository.findByProdutoId(produto.getId()).stream()
                            .filter(m -> m.getTipo() == MovimentoEstoque.TipoMovimentacao.SAIDA)
                            .mapToInt(MovimentoEstoque::getQuantidade)
                            .sum();
                    
                    dto.setQuantidadeSaida(saida);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<LucroProdutoDTO> calcularLucroPorProduto() {
        return produtoRepository.findAll().stream()
                .map(produto -> {
                    List<MovimentoEstoque> saidas = movimentoRepository.findByProdutoId(produto.getId()).stream()
                            .filter(m -> m.getTipo() == MovimentoEstoque.TipoMovimentacao.SAIDA)
                            .collect(Collectors.toList());
                    
                    int quantidadeSaida = saidas.stream()
                            .mapToInt(MovimentoEstoque::getQuantidade)
                            .sum();
                    
                    double lucroTotal = saidas.stream()
                            .mapToDouble(m -> (m.getValorVenda() - produto.getValorFornecedor()) * m.getQuantidade())
                            .sum();
                    
                    LucroProdutoDTO dto = new LucroProdutoDTO();
                    dto.setProdutoId(produto.getId());
                    dto.setProdutoDescricao(produto.getDescricao());
                    dto.setQuantidadeSaida(quantidadeSaida);
                    dto.setLucroTotal(lucroTotal);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private MovimentoEstoqueDTO toDTO(MovimentoEstoque movimento) {
        MovimentoEstoqueDTO dto = new MovimentoEstoqueDTO();
        dto.setId(movimento.getId());
        dto.setProdutoId(movimento.getProduto().getId());
        dto.setTipo(movimento.getTipo());
        dto.setValorVenda(movimento.getValorVenda());
        dto.setDataMovimentacao(movimento.getDataMovimentacao());
        dto.setQuantidade(movimento.getQuantidade());
        return dto;
    }
}