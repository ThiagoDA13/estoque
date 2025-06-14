package com.estoque.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String codigo;
    
    @Column(nullable = false)
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProduto tipo;
    
    @Column(name = "valor_fornecedor", nullable = false)
    private Double valorFornecedor;
    
    @Column(name = "quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;

    public enum TipoProduto {
    	ELETRONICO, 
    	ELETRODOMESTICO, 
    	MOVEL
    	
    }
}
