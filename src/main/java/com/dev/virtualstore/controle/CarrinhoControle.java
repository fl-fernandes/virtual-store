package com.dev.virtualstore.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dev.virtualstore.modelos.ItensCompra;
import com.dev.virtualstore.modelos.Produto;
import com.dev.virtualstore.repositorios.ProdutoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarrinhoControle {

	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();

	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	@GetMapping("/carrinho")
	public ModelAndView chamarCarrinho() {
		ModelAndView mv = new ModelAndView("cliente/carrinho");
		mv.addObject("listaItens", this.itensCompra);
		return mv;
	}

	@GetMapping("/adicionarCarrinho/{id}")
	public ModelAndView chamarCarrinho(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("cliente/carrinho");
		
		Optional<Produto> produto = this.produtoRepositorio.findById(id);
		Produto p = produto.get();
		ItensCompra item = new ItensCompra();

		item.setProduto(p);
		item.setValorUnitario(p.getValorVenda());
		item.setQuantidade(item.getQuantidade()+1);
		item.setValorTotal(item.getQuantidade()*item.getValorUnitario());

		this.itensCompra.add(item);

		mv.addObject("listaItens", this.itensCompra);

		return mv;
	}
}
