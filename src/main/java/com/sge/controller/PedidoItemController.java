
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DA CLASE MODELO PEDIDOITEM
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

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.Enumeracao;
import com.sge.model.Mercadoria;
import com.sge.model.PedidoItem;

@RestController()
@RequestMapping("/Pedidoitem")
public class PedidoItemController {

	@Autowired
	private PedidoItemRepository Repitem;

	@Autowired
	private PedidoRepository PedRep;

	PedidoValidate valped = new PedidoValidate();

	public PedidoItemController(PedidoRepository pedRep) {
		PedRep = pedRep;
	}

	PedidoItem pedidoitem = new PedidoItem();

	@PostMapping("/add")
	public void addPedido(@RequestBody PedidoItem ped) {

		//Long numero_pedido = (long) 0;

		try {

			ped.getPedido().setCancelado(Enumeracao.N);
			ped.getPedido().setData_pedido(new Date());
			PedRep.save(ped.getPedido());
			//ped.getPedido().setId(numero_pedido);

			/*
			 * for (Mercadoria mercadoria : ped.getPedido().getMercadoria()) {
			 * 
			 * if (PedRep.findById(ped.getPedido().getId()) != null) {
			 * pedidoitem.setQtd(mercadoria.getQtd());
			 * pedidoitem.setPedido(ped.getPedido()); pedidoitem.setMercadoria(mercadoria);
			 * Repitem.save(pedidoitem);
			 * 
			 * }
			 * 
			 * }
			 * 
			 */

		} catch (NullPointerException e) {
			
		}

	}

	@GetMapping("/show")
	public Iterable<PedidoItem> show() {
		return Repitem.findAll();
	}

	@GetMapping("/show/{id}")
	public PedidoItem show(@PathVariable(value = "id") long id) {
		return Repitem.findBypedido(id);

	}

}
