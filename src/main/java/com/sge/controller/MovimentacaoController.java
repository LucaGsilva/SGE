package com.sge.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.Movimentacao;

@RestController
@RequestMapping("/Movimentacoes")
public class MovimentacaoController {

	@Autowired
	private MovimentacaoRepository rep;

	@GetMapping("/show")
	public Iterable<Movimentacao> show() {
		return rep.findAll();
	}

	@GetMapping("/show/{nome}")
	public Iterable<Movimentacao> show(@PathVariable(value = "mercadoria") Long mercadoria) {

		try {
			return rep.findByMercadoria(mercadoria);

		} catch (NumberFormatException e) {
			return null;
		}

	}

	@GetMapping("/show/grupo")
	public Iterable<Movimentacao> showGrupo() {
		return rep.findByGrupo();

	}

	@GetMapping("/show/filter/{tipo}/{data_inicio}/{data_final}/{mercadoria}/{agrupa}")
	public Set<Movimentacao> showFilter(@PathVariable(value = "tipo") String tipo,
			@PathVariable(value = "data_inicio") String data_inicio,
			@PathVariable(value = "data_final") String data_final, @PathVariable(value = "mercadoria") int mercadoria ,@PathVariable(value = "agrupa") String agrupa) {

		try {

			Movimentacao_Filtro filtro = new Movimentacao_Filtro();
			Set<Movimentacao> movimentacao = new LinkedHashSet<>();
			movimentacao = rep.findByFilter();

			movimentacao = filtro.Filtro(movimentacao, tipo, data_inicio, data_final, mercadoria, agrupa);
			return movimentacao;
		} catch (Exception e) {
			return null;
		}

	}

}
