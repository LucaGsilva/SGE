
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É RESPONSAVEL POR REALIZAR AS VALIDAÇÕES DE INFORMAÇOES ANTES DA PERSISTENCIA
*  
*  INVOCADA NA CLASSE MERCADORIACONTROLLER
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

import com.sge.model.Mercadoria;

public class MercadoriaValidate {

	public boolean ValidateMercadoria(Mercadoria merc) {
		if (ValidateNome(merc) && ValidatePreco(merc)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean ValidateNome(Mercadoria mercadoria) {

		if (mercadoria.getNome().trim().length() != 0) {
			return true;
		} else {
			return false;

		}
	}
	
	private boolean ValidatePreco(Mercadoria mercadoria) {

		if (mercadoria.getPreco() > 0) {
			return true;
		} 
		if (mercadoria.getPreco() == 0) {
			mercadoria.setPreco(0.01);
			return true;
		}
		else {
			return false;

		}
	}
}
