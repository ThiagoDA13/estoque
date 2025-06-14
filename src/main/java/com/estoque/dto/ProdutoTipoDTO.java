package com.estoque.dto;

import lombok.Data;

@Data
public class ProdutoTipoDTO {
    private String tipo;
    private Integer quantidadeSaida;
    private Integer quantidadeDisponivel;
}