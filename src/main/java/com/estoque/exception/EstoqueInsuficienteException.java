package com.estoque.exception;

public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(Long produtoId, Integer estoqueAtual, Integer quantidadeRequerida) {
        super(String.format("Estoque insuficiente para o produto ID %d. Estoque atual: %d, Quantidade requerida: %d", 
                produtoId, estoqueAtual, quantidadeRequerida));
    }
}