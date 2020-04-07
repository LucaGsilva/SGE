
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DA CLASE MODELO USUARIO
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.Usuario;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepositiry rep;

	
	public void addUsuario(@RequestBody Usuario user) {

		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

		if (user.getPassword() == null || user.getPassword().equals("******************")) {
			user.setPassword(rep.findById(user.getId()).get().getPassword());

		} else {

			user.setPassword(encode.encode(user.getPassword()));
		}

		rep.save(user);

	}

	@GetMapping("/show")
	public Iterable<Usuario> show() {

		return rep.findAll();
	}

	@GetMapping("/show/{id}")
	public Usuario show(@PathVariable(value = "id") int id) {

		return rep.findById(id);

	}
	@GetMapping("/show/login/{login}")
	public Usuario showLogin(@PathVariable(value = "login") String login) {
		return rep.findByLogin(login);

	}

}
