/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É RESPONSAVEL  POR RETORNAR O USUARIO QUE REALIZOU A REQUISIÇÃO E AS SUAS PERMISSÕES
*  
*	----------------------------------ALTERAÇÕES---------------------------------------------
*	DESENVOLVEDOR: XXXX
*	DATA: XX/XX/XX
*	MOTIVO: XXXXXXXXXXX
*	ALTERAÇÃO: XXXXXXXXXXXX
*------------------------------------------------------------------------------------------------ */

package com.sge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.controller.UsuarioParametroRepositiry;
import com.sge.model.UsuarioParametro;

@RestController
@RequestMapping("/Logado")
@Component
public class UsuarioLogado {

	private final UsuarioParametroRepositiry UserRep;

	@Autowired
	public UsuarioLogado(UsuarioParametroRepositiry useRepositiry) {
		this.UserRep = useRepositiry;
	}

	@GetMapping("/show")
	public UsuarioParametro UserLogado() {

		return VerificaUsarioLogado();
	}

	private UsuarioParametro VerificaUsarioLogado() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			if (auth.isAuthenticated()) {
				String user = auth.getName();
				UsuarioParametro logado = new UsuarioParametro();
				logado = UserRep.findByLoginParametro(user);
				return logado;
			}
		} catch (NullPointerException e) {

		}
		return null;

	}
}