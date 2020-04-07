/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DA CLASE MODELO CLIENTE
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.Cliente;

@RestController
@RequestMapping("/Clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository rep;

	@PostMapping("/add")
	public void addCliente(@RequestBody Cliente cli) {
		ClienteValidate validate = new ClienteValidate();
		if (validate.ValidaCliente(cli) == true) {
			rep.save(cli);
		}

	}

	@GetMapping("/show")
	public Iterable<Cliente> show() {
		return rep.findAll();
	}

	@GetMapping("/show/{nome}")
	public Iterable<Cliente> show(@PathVariable(value = "nome") String nome) {

		try {
			return rep.findById(Long.parseLong(nome));

		} catch (NumberFormatException e) {
			return rep.findByName(nome);
		}

	}

}
