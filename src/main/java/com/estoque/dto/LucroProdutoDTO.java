package com.estoque.dto;

import com.estoque.model.Produto.TipoProduto;

import lombok.Data;

@Data
public class LucroProdutoDTO {
    private Long produtoId;
    private String produtoDescricao;
    private TipoProduto tipoProduto;
    private Integer quantidadeSaida;
    private Double lucroTotal;
}