package com.sge.controller;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TimeZone;

import com.sge.model.Enumeracao;
import com.sge.model.Movimentacao;

public class Movimentacao_Filtro {

	public Set<Movimentacao> Filtro(Set<Movimentacao> movimentacao, String tipo, String data_inicio, String data_final,
			String agrupa) {

		return Filter(movimentacao, tipo, data_inicio, data_final, agrupa);

	}

	private Set<Movimentacao> Filter(Set<Movimentacao> movimentacao, String tipo, String data_inicio, String data_final,
			String agrupa) {

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

			if (!Inicio[0].equals("00") && !Fim[0].equals("00")) {

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

			if (agrupa.equals("S")) {

				Set<Movimentacao> filter = new LinkedHashSet<>();

				for (Movimentacao mov : movimentacao) {

					filter.removeIf(x -> x.getMercadoria().getId() == mov.getMercadoria().getId());
					filter.add(mov);

				}

				movimentacao.clear();
				movimentacao = filter;

			}
			return movimentacao;
		} catch (

		Exception e) {
			return null;
		}
	}

}
