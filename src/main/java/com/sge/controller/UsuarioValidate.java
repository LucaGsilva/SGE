
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É RESPONSAVEL POR REALIZAR AS VALIDAÇÕES DE INFORMAÇOES ANTES DA PERSISTENCIA
*  
*  INVOCADA NA CLASSE USUARIOCONTROLLER
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

import com.sge.model.Usuario;

public class UsuarioValidate {

	public boolean ValidaUsuario(Usuario user) {
		if (ValidateNome(user) && ValidateUser(user) && ValidatePass(user)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean ValidateNome(Usuario usuario) {

		if (usuario.getNome().trim().length() != 0) {
			return true;
		} else {
			return false;

		}
	}

	private boolean ValidateUser(Usuario usuario) {

		if (usuario.getLogin().trim().length() != 0) {
			return true;
		} else {
			return false;

		}
	}

	private boolean ValidatePass(Usuario usuario) {

		if (usuario.getPassword().trim().length() != 0) {
			return true;
		} else {
			return false;

		}
	}

}
