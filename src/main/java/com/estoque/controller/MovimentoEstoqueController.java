package com.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estoque.dto.LucroProdutoDTO;
import com.estoque.dto.MovimentoEstoqueDTO;
import com.estoque.dto.ProdutoTipoDTO;
import com.estoque.model.Produto.TipoProduto;
import com.estoque.service.MovimentoEstoqueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movimentos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")

public class MovimentoEstoqueController {
	
	@Autowired
    private MovimentoEstoqueService movimentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovimentoEstoqueDTO criar(@RequestBody MovimentoEstoqueDTO movimentoDTO) {
        return movimentoService.criar(movimentoDTO);
    }

    @GetMapping("/produto/{produtoId}")
    public List<MovimentoEstoqueDTO> listarPorProduto(@PathVariable Long produtoId) {
        return movimentoService.listarPorProduto(produtoId);
    }

    @GetMapping("/tipo/{tipo}")
    public List<ProdutoTipoDTO> consultarPorTipo(@PathVariable TipoProduto tipo) {
        return movimentoService.consultarPorTipo(tipo);
    }

    @GetMapping("/lucro")
    public List<LucroProdutoDTO> calcularLucroPorProduto() {
        return movimentoService.calcularLucroPorProduto();
    }
}