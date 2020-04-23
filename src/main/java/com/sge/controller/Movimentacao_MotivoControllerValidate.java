package com.sge.controller;

import com.sge.model.Enumeracao;
import com.sge.model.Movimentacao_Motivo;

public class Movimentacao_MotivoControllerValidate {

	public Boolean Validate(Movimentacao_Motivo motivo) {

		if (ValidaMotivo(motivo) && ValidaTipo(motivo)) {
			return true;
		} else {
			return false;
		}
	}

	private Boolean ValidaTipo(Movimentacao_Motivo motivo) {

		if (motivo.getTipo() != Enumeracao.Entrada && motivo.getTipo() != Enumeracao.Saida) {
			return false;
		} else {
			return true;
		}
	}

	private Boolean ValidaMotivo(Movimentacao_Motivo motivo) {

		if (motivo.getMotivo().trim() != null) {
			return true;
		} else {
			return false;
		}
	}

}
