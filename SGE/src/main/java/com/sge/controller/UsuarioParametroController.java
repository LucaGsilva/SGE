
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DA CLASE MODELO USUARIOPARAMETRO
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.UsuarioParametro;

@RestController
@RequestMapping("/Users")
public class UsuarioParametroController {

	@Autowired
	private UsuarioParametroRepositiry rep;

	private UsuarioRepositiry UsuarioRep;
	private UsuarioValidate uservalidate = new UsuarioValidate();

	public UsuarioParametroController(UsuarioRepositiry usuarioRep) {
		UsuarioRep = usuarioRep;
	}

	@PostMapping("/add")
	public void addUser(@RequestBody UsuarioParametro user) {

		if (uservalidate.ValidaUsuario(user.getUsuario())) {

			BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

			if (user.getUsuario().getPassword() == null
					|| user.getUsuario().getPassword().equals("******************")) {

				String senha = UsuarioRep.findById(user.getUsuario().getId()).get().getPassword();
				user.getUsuario().setPassword(senha);

			} else {

				user.getUsuario().setPassword(encode.encode(user.getUsuario().getPassword()));
			}
			UsuarioRep.save(user.getUsuario());
			rep.save(user);
		}

	}

	@GetMapping("/show")
	public Iterable<UsuarioParametro> show() {

		return rep.findAll();
	}

	@GetMapping("/show/{id}")
	public UsuarioParametro show(@PathVariable(value = "id") int id) {

		return rep.findById(id);

	}

}
