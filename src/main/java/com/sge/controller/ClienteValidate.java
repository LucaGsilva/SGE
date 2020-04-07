
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É RESPONSAVEL POR REALIZAR AS VALIDAÇÕES DE INFORMAÇOES ANTES DA PERSISTENCIA
*  
*  INVOCADA NA CLASSE CLIENTECONTROLLER
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

import com.sge.model.Cliente;

public class ClienteValidate {

	protected boolean ValidaCliente(Cliente cliente) {

		if (ValidateNome(cliente.getNome().trim()) == true) {
			return true;
		} else {
			return false;
		}
	}

	private boolean ValidateNome(String nome) {

		if (nome.isEmpty()) {

			return false;
		} else {
			return true;
		}
	}

}
