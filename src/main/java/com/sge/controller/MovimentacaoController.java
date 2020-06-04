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

	@GetMapping("/show/grupo/{tipo}")
	public Iterable<Movimentacao> showGrupo(@PathVariable(value = "tipo") String tipo) {

		Movimentacao_Filtro filtro = new Movimentacao_Filtro();
		Set<Movimentacao> movimentacao = new LinkedHashSet<>();

		movimentacao = rep.findByGrupo();

		movimentacao = filtro.Filtro(movimentacao, tipo, "S");
		return movimentacao;

	}

	@GetMapping("/show/filter/{tipo}/{data_inicio}/{data_final}/{mercadoria}/{agrupa}")
	public Set<Movimentacao> showFilter(@PathVariable(value = "tipo") String tipo,
			@PathVariable(value = "data_inicio") String data_inicio,
			@PathVariable(value = "data_final") String data_final, @PathVariable(value = "mercadoria") int mercadoria,
			@PathVariable(value = "agrupa") String agrupa) {

		try {

			if (data_inicio.equals("00-00-0000") && data_final.equals("00-00-0000")) {
				data_inicio = "00-00-0000";
				data_final = "31-12-9999";
			}

			String Data_Inicio, Data_Fim;

			String Inicio[] = new String[3];
			Inicio = data_inicio.split("-");

			String Fim[] = new String[3];
			Fim = data_final.split("-");

			Data_Inicio = Inicio[2] + "-" + Inicio[1] + "-" + Inicio[0];
			Data_Fim = Fim[2] + "-" + Fim[1] + "-" + Fim[0];

			Movimentacao_Filtro filtro = new Movimentacao_Filtro();
			Set<Movimentacao> movimentacao = new LinkedHashSet<>();

			movimentacao = rep.findByFilter(Data_Inicio, Data_Fim);

			movimentacao = filtro.Filtro(movimentacao, tipo, agrupa, mercadoria);
			return movimentacao;
		} catch (Exception e) {
			return null;
		}

	}

}
