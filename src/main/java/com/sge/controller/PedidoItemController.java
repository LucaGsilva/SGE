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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.Enumeracao;
import com.sge.model.Mercadoria;
import com.sge.model.PedidoItem;
import com.sge.model.Usuario;

@RestController()
@RequestMapping("/Pedidoitem")
public class PedidoItemController {

	@Autowired
	private PedidoItemRepository Repitem;

	@Autowired
	private PedidoRepository PedRep;

	@Autowired
	private UsuarioParametroRepositiry userRep;

	private PedidoValidate valped = new PedidoValidate();

	public PedidoItemController(PedidoRepository pedRep, UsuarioParametroRepositiry userRep) {
		super();
		PedRep = pedRep;
		this.userRep = userRep;
	}

	@PostMapping("/add")
	public void addPedido(@RequestBody PedidoItem ped, Authentication auth) {

		try {

			Long numero_pedido = (long) 0;
			Usuario usuario = new Usuario();
			usuario.setId(userRep.findByLoginParametro(auth.getName()).getId());

			ped.getPedido().setCancelado(Enumeracao.N);
			ped.getPedido().setData_pedido(new Date());
			ped.getPedido().setUsuario(usuario);
			numero_pedido = PedRep.save(ped.getPedido()).getId();

			ped.getPedido().setId(numero_pedido);

			for (Mercadoria mercadoria : ped.getPedido().getMercadoria()) {

				if (PedRep.findById(ped.getPedido().getId()) != null) {
					PedidoItem pedidoitem = new PedidoItem();
					pedidoitem.setQtd(mercadoria.getQtd());
					pedidoitem.setPedido(ped.getPedido());
					pedidoitem.setPreco(mercadoria.getPreco());
					pedidoitem.setPreco_total(mercadoria.getPreco_total());
					pedidoitem.setMercadoria(mercadoria);
					Repitem.save(pedidoitem);

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