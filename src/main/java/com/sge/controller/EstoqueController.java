
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DA CLASE MODELO ESTOQUE
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

import com.sge.model.Estoque;

@RestController
@RequestMapping("/Estoques")
public class EstoqueController {

	@Autowired
	private EstoqueRepository rep;

	@PostMapping("/add")
	public void addEstoque(@RequestBody Estoque est) {

		if (rep.VerificaMercadoria(est.getMercadoria().getId()) != null) {
			rep.save(est);
		}

	}

	@GetMapping("/show")
	public Iterable<Estoque> show() {
		return rep.findAll();
	}

	@GetMapping("/show/{cod_mercadoria}")
	public Iterable<Estoque> show(@PathVariable(value = "cod_mercadoria") Long cod_mercadoria) {

		return rep.findByMercadoria(cod_mercadoria);

	}

}
