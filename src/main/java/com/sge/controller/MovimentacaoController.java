package com.sge.controller;

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

}
