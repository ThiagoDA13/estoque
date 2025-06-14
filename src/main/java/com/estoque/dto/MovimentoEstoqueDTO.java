package com.estoque.dto;

import com.estoque.model.MovimentoEstoque.TipoMovimentacao;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimentoEstoqueDTO {
    private Long id;
    private Long produtoId;
    private TipoMovimentacao tipo;
    private Double valorVenda;
    private LocalDateTime dataMovimentacao;
    private Integer quantidade;
}