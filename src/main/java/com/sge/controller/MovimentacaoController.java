package com.sge.controller;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.Enumeracao;
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

	@GetMapping("/show/filter/{tipo}/{data_inicio}/{data_final}")
	public Set<Movimentacao> showFilter(@PathVariable(value = "tipo") String tipo,
			@PathVariable(value = "data_inicio") String data_inicio,
			@PathVariable(value = "data_final") String data_final) {

		try {

			Set<Movimentacao> movimentacao = new LinkedHashSet<>();
			movimentacao = rep.findByFilter();

			movimentacao = Filter(movimentacao, tipo, data_inicio, data_final);
			return movimentacao;
		} catch (Exception e) {
			return null;
		}

	}

	private Set<Movimentacao> Filter(Set<Movimentacao> movimentacao, String tipo, String data_inicio,
			String data_final) {

		try {

			Calendar dataInicio = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
			Calendar dataFinal = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));

			String Inicio[] = new String[3];
			Inicio = data_inicio.split("-");

			dataInicio.set(Integer.parseInt(Inicio[2]), Integer.parseInt(Inicio[1]), Integer.parseInt(Inicio[0]));

			String Fim[] = new String[3];
			Fim = data_final.split("-");

			dataFinal.set(Integer.parseInt(Fim[2]), Integer.parseInt(Fim[1]), Integer.parseInt(Fim[0]));

			if (tipo.equals("Entrada")) {
				movimentacao.removeIf(x -> x.getTipo() == Enumeracao.Saida);
			}

			if (tipo.equals("Saida")) {
				movimentacao.removeIf(x -> x.getTipo() == Enumeracao.Entrada);
			}

			if (Inicio[0] != "0" && Fim[0] != "0") {

				// As ordens das datas não podem ser alteradas, pois pode quebrar o filtro

				if (dataInicio.getTime().before(dataFinal.getTime()) || dataInicio.equals(dataFinal)) {

					movimentacao.removeIf(x -> x.getData().getTime().getYear() < dataInicio.getTime().getYear());
					movimentacao.removeIf(x -> x.getData().getTime().getMonth() < dataInicio.getTime().getMonth() - 1);
					movimentacao.removeIf(x -> x.getData().getTime().getDate() < dataInicio.getTime().getDate());

					movimentacao.removeIf(x -> x.getData().getTime().getYear() > dataFinal.getTime().getYear());
					movimentacao.removeIf(x -> x.getData().getTime().getMonth() > dataFinal.getTime().getMonth());
					movimentacao.removeIf(x -> x.getData().getTime().getDate() > dataFinal.getTime().getDate());
				}
			}

			return movimentacao;
		} catch (Exception e) {
			return null;
		}
	}

}
