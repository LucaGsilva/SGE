
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DA CLASE MODELO PEDIDO
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.Pedido;

@RestController()
@RequestMapping("/Pedidos/Novos")
public class PedidoController {

	@Autowired
	private PedidoRepository rep;

	// @PostMapping("/add")
	public void addPedido(@RequestBody Pedido ped) {

		rep.save(ped);

	}

	@GetMapping("/show")
	public Iterable<Pedido> show() {
		return rep.findAll();
	}

	@GetMapping("/show/{id}")
	public Pedido show(@PathVariable(value = "id") long id) {

		return rep.findById(id);

	}

}
