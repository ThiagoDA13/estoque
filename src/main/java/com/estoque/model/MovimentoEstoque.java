package com.estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class MovimentoEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao", nullable = false)
    private TipoMovimentacao tipo;
    
    @Column(name = "valor_venda", nullable = false)
    private Double valorVenda;
    
    @Column(name = "data_movimentacao", nullable = false)
    private LocalDateTime dataMovimentacao = LocalDateTime.now();
    
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    public enum TipoMovimentacao {
    	ENTRADA, SAIDA
    }
}
