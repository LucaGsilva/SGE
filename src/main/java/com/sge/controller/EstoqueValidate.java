
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

		if (ValidaMovimentacao(estoque) && ValidateObservacao(estoque)) {
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

		if (estoque.getObservacao().trim() != "") {
			return true;
		} else {
			return false;
		}

	}

}
