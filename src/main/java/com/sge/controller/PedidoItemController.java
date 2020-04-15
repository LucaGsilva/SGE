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
	public long addPedido(@RequestBody PedidoItem ped, Authentication auth) {

		Long numero_pedido = (long) 0;
		int menos_merc = 1;

		try {

			Usuario usuario = new Usuario();
			usuario.setId(userRep.findByLoginParametro(auth.getName()).getId());

			ped.getPedido().setCancelado(Enumeracao.N);
			ped.getPedido().setData_pedido(new Date());
			ped.getPedido().setUsuario(usuario);
			numero_pedido = PedRep.save(ped.getPedido()).getId();

			ped.getPedido().setId(numero_pedido);

			for (Mercadoria mercadoria : ped.getPedido().getMercadoria()) {

				if (PedRep.findById(ped.getPedido().getId()) != null) {

					if (Repitem.findByPedidoMercadoria(numero_pedido, mercadoria.getId()) == 0) {

						// Verifica se existe a mesma mercadoria no pedido antes de adicionar, caso
						// existe apenas atualiza a quantidade
						PedidoItem pedidoitem = new PedidoItem();
						pedidoitem.setQtd(mercadoria.getQtd());
						pedidoitem.setPedido(ped.getPedido());
						pedidoitem.setPreco(mercadoria.getPreco());
						pedidoitem.setPreco_total(mercadoria.getPreco_total());
						pedidoitem.setMercadoria(mercadoria);
						Repitem.save(pedidoitem);

					} else {

						PedidoItem pedidoitem = new PedidoItem();
						pedidoitem = Repitem.findByMercadoriaPedido(numero_pedido, mercadoria.getId());
						pedidoitem.setQtd(mercadoria.getQtd() + mercadoria.getQtd());
						pedidoitem.setPreco_total(pedidoitem.getPreco() * pedidoitem.getQtd());
						Repitem.save(pedidoitem);
						// Corrige a quantidade de intem na capa após agregação de mercadorias iguais
						ped.getPedido().setId(numero_pedido);
						ped.getPedido().setItens(ped.getPedido().getItens() - 1);
						PedRep.save(ped.getPedido());

					}

				}

			}

		} catch (NullPointerException e) {

		}
		return numero_pedido;

	}

	@GetMapping("/show")
	public Iterable<PedidoItem> show() {
		return Repitem.findAll();
	}

	@GetMapping("/show/{pedido}")
	public Iterable<PedidoItem> show(@PathVariable(value = "pedido") long pedido) {
		return Repitem.findBypedido(pedido);

	}

}