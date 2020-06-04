package com.sge.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import com.sge.model.Enumeracao;
import com.sge.model.Movimentacao;

public class Movimentacao_Filtro {

	public Set<Movimentacao> Filtro(Set<Movimentacao> movimentacao, String tipo, String agrupa, int mercadoria) {

		return Filter(movimentacao, tipo, agrupa, mercadoria);

	}

	public Set<Movimentacao> Filtro(Set<Movimentacao> movimentacao, String tipo, String agrupa) {

		return Filter(movimentacao, tipo, agrupa, 0);

	}

	private Set<Movimentacao> Filter(Set<Movimentacao> movimentacao, String tipo, String agrupa, int mercadoria) {

		try {

			if (mercadoria != 0) {
				movimentacao.removeIf(x -> x.getMercadoria().getId() != mercadoria);
			}

			if (tipo.equals("Saida")) {
				movimentacao.removeIf(x -> x.getTipo() == Enumeracao.Entrada);
			}

			if (tipo.equals("Entrada")) {
				movimentacao.removeIf(x -> x.getTipo() == Enumeracao.Saida);

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