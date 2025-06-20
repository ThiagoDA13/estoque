package com.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoque.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByTipo(Produto.TipoProduto tipo);
}