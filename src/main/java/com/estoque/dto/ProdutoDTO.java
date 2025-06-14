package com.estoque.dto;

import com.estoque.model.Produto.TipoProduto;
import lombok.Data;

@Data
public class ProdutoDTO {
    private Long id;
    private String codigo;
    private String descricao;
    private TipoProduto tipo;
    private Double valorFornecedor;
    private Integer quantidadeEstoque;
}