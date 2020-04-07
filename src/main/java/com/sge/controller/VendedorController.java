
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DA CLASE MODELO VENDEDOR
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

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.Vendedor;

@RestController
@RequestMapping("/Vendedores")
public class VendedorController {

	@Autowired
	private VendedorRepository rep;

	private VendedorValidate validadeVendedor = new VendedorValidate();

	@PostMapping("/add")
	public void addVendedor(@RequestBody Vendedor vendedor) {

		if (validadeVendedor.ValidaVendedor(vendedor)) {

			rep.save(vendedor);
		}

	}

	@GetMapping("/show")
	public Iterable<Vendedor> show() {
		return rep.findAll();
	}

	@GetMapping("/show/ativo")
	public Iterable<Vendedor> showAtivo() {
		return rep.findByAtivo();
	}

	@GetMapping("/show/ativo/{codigo}")
	public Iterable<Vendedor> showAtivoID(@PathVariable(value = "codigo") long codigo) {
		return rep.findByAtivo(codigo);
	}

	@GetMapping("/show/{nome}")
	public Optional<Vendedor> show(@PathVariable(value = "nome") String nome) {

		try {
			return rep.findById(Long.parseLong(nome));

		} catch (NumberFormatException e) {
			return rep.findByName(nome);
		}

	}

}
