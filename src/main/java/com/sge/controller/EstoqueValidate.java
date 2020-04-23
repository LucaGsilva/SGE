
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É RESPONSAVEL POR REALIZAR AS VALIDAÇÕES DE INFORMAÇOES ANTES DA PERSISTENCIA
*  
*  INVOCADA NA CLASSE ESTOQUECONTROLLER
*  
*	----------------------------------ALTERAÇÕES-------------------------------------------------
*
*
*	DESENVOLVEDOR: XXXX
*	DATA: XX/XX/XX
*	MOTIVO: XXXXXXXXXXX
*	ALTERAÇÃO: XXXXXXXXXXXX
*
*
*------------------------------------------------------------------------------------------------ */

package com.sge.controller;

import com.sge.model.Enumeracao;
import com.sge.model.Estoque;

public class EstoqueValidate {

	public boolean Validate(Estoque estoque) {

		if (ValidaMovimentacao(estoque) && ValidateObservacao(estoque) && ValidateQuantodade(estoque)) {
			return true;
		} else {
			return false;
		}
	}

	private Boolean ValidaMovimentacao(Estoque estoque) {

		if (estoque.getTipo_Movimentacao() == Enumeracao.Entrada
				|| estoque.getTipo_Movimentacao() == Enumeracao.Saida) {
			return true;
		} else {
			return false;
		}

	}

	private Boolean ValidateObservacao(Estoque estoque) {

		try {

			if (estoque.getObservacao().trim() != null) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}

	}
	
	private Boolean ValidateQuantodade(Estoque estoque) {

		try {

			if (estoque.getQtd_estoque() < 1) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			return false;
		}

	}
}
