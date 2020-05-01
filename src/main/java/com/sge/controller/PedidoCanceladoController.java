package com.sge.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.Enumeracao;
import com.sge.model.Estoque;
import com.sge.model.Movimentacao;
import com.sge.model.PedidoCancelado;
import com.sge.model.PedidoItem;
import com.sge.model.Usuario;

@RestController()
@RequestMapping("/Pedidos/Cancelamento")
public class PedidoCanceladoController {

	@Autowired
	private PedidoCanceladoRepository Repositorio;

	@Autowired
	private PedidoRepository RepositorioPedido;

	@Autowired
	private PedidoItemRepository RepositorioPedidoItem;

	@Autowired
	private UsuarioParametroRepositiry userRep;

	@Autowired
	private MovimentacaoRepository RepMov;

	@Autowired
	private EstoqueRepository RepEst;

	public PedidoCanceladoController(PedidoCanceladoRepository repositorio, PedidoRepository repositorioPedido,
			PedidoItemRepository repositorioPedidoItem, UsuarioParametroRepositiry userRep,
			MovimentacaoRepository repMov, EstoqueRepository repEst) {
		super();
		Repositorio = repositorio;
		RepositorioPedido = repositorioPedido;
		RepositorioPedidoItem = repositorioPedidoItem;
		this.userRep = userRep;
		RepMov = repMov;
		RepEst = repEst;
	}

	@PostMapping("/add")
	public void addPedido(@RequestBody PedidoCancelado pedido, Authentication auth) {

		try {

			Calendar data = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));

			if (RepositorioPedidoItem.findBypedido(pedido.getPedido().getId()) != null) {

				Usuario usuario = new Usuario();
				usuario.setId(userRep.findByLoginParametro(auth.getName()).getId());

				List<PedidoItem> ped = new ArrayList<>();
				ped = RepositorioPedidoItem.findBypedidoId(pedido.getPedido().getId());
				if (ped.get(0).getPedido().getCancelado() == Enumeracao.N) {
					pedido.setPedido(ped.get(0).getPedido());
					pedido.getPedido().setCancelado(Enumeracao.S);
					RepositorioPedido.save(pedido.getPedido());

					for (PedidoItem pedidoItem : ped) {

						Estoque estoque = new Estoque();
						Movimentacao movimentacao = new Movimentacao();

						estoque = RepEst.findByUnicaMercadoria(pedidoItem.getMercadoria().getId());
						estoque.setQtd_estoque(estoque.getQtd_estoque() + pedidoItem.getQtd());
						RepEst.save(estoque);

						movimentacao.setMercadoria(pedidoItem.getMercadoria());
						movimentacao.setQtd(pedidoItem.getQtd());
						movimentacao.setEstoque_Atual(estoque.getQtd_estoque());
						movimentacao.setTipo(Enumeracao.Saida);
						movimentacao.setData(data.getInstance());
						movimentacao.setAtividade("Retorno mediante a cancelamento - Pedido: " + pedidoItem.getPedido().getId());
						RepMov.save(movimentacao);
					}
					pedido.setData(data);
					pedido.setData_cancelamento(data);
					pedido.setUsuario(usuario);
					Repositorio.save(pedido);
				}
			}

		} catch (Exception e) {
			
		}
	}
}
