
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.Mercadoria;
import com.sge.model.PedidoItem;

@RestController()
@RequestMapping("/Pedidoitem")
public class PedidoItemController {

	@Autowired
	private PedidoItemRepository Repitem;

	private PedidoRepository PedRep;

	PedidoValidate valped = new PedidoValidate();

	public PedidoItemController(PedidoRepository pedRep) {
		PedRep = pedRep;
	}

	PedidoItem pedidoitem = new PedidoItem();

	@PostMapping("/add")
	public void addPedido(PedidoItem ped) {

		try {
			if (valped.ValidatePedido(ped.getPedido())) {
				PedRep.save(ped.getPedido());

				for (Mercadoria mercadoria : ped.getPedido().getMercadoria()) {

					if (PedRep.findById(ped.getPedido().getId()) != null) {
						pedidoitem.setQtd(mercadoria.getQtd());
						pedidoitem.setPedido(ped.getPedido());
						pedidoitem.setMercadoria(mercadoria);
						Repitem.save(pedidoitem);

					}

				}
			}
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
