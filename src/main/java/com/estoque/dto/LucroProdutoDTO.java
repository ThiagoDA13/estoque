package com.estoque.dto;

import lombok.Data;

@Data
public class LucroProdutoDTO {
    private Long produtoId;
    private String produtoDescricao;
    private Integer quantidadeSaida;
    private Double lucroTotal;
}