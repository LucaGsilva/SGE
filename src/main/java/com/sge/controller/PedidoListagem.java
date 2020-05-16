package com.sge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.Pedido;
import com.sge.model.PedidoItem;

@RestController()
@RequestMapping("/Listagem-Pedido")
public class PedidoListagem {

	@Autowired
	private PedidoItemRepository PedidoItem;

	@Autowired
	private PedidoRepository Pedido;

	public PedidoListagem(PedidoItemRepository pedidoItem, PedidoRepository pedido) {
		super();
		PedidoItem = pedidoItem;
		Pedido = pedido;
	}

	@GetMapping("/show")
	public Iterable<Pedido> show() {
		return Pedido.findAll();
	}
	
	@GetMapping("/show/pedido/{pedido}")
	public Pedido showPedid(@PathVariable(value = "pedido") long pedido) {
		return Pedido.findById(pedido);
	}

	@GetMapping("/show/{pedido}")
	public Iterable<PedidoItem> showPedido(@PathVariable(value = "pedido") long pedido) {

		return PedidoItem.findBypedido(pedido);
	}
}
