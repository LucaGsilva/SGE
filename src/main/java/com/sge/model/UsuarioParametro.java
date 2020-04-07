
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É O MODELO UTILIZADO NO SISTEMA E NA PERSISTENCIA DE DADOS.
*  AS TABELAS SÃO GERADAS AUTOMATICAMENTE NO BANCO DE DADOS JUNTAMENTE COM SEUS RELACIONAMENTOS
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

package com.sge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;

@Entity
public class UsuarioParametro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@MapKeyColumn()
	private Usuario usuario;

	private Enumeracao dashboard, cliente, estoque, mercadoria, pedido_novo, pedido_troca, pedido_cancela, vendedor,
			usuario_acesso, titulo_aberto, titulo_liquidado;

	public Long getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Enumeracao getDashboard() {
		return dashboard;
	}

	public void setDashboard(Enumeracao dashboard) {
		this.dashboard = dashboard;
	}

	public Enumeracao getCliente() {
		return cliente;
	}

	public void setCliente(Enumeracao cliente) {
		this.cliente = cliente;
	}

	public Enumeracao getEstoque() {
		return estoque;
	}

	public void setEstoque(Enumeracao estoque) {
		this.estoque = estoque;
	}

	public Enumeracao getMercadoria() {
		return mercadoria;
	}

	public void setMercadoria(Enumeracao mercadoria) {
		this.mercadoria = mercadoria;
	}

	public Enumeracao getPedido_novo() {
		return pedido_novo;
	}

	public void setPedido_novo(Enumeracao pedido_novo) {
		this.pedido_novo = pedido_novo;
	}

	public Enumeracao getPedido_troca() {
		return pedido_troca;
	}

	public void setPedido_troca(Enumeracao pedido_troca) {
		this.pedido_troca = pedido_troca;
	}

	public Enumeracao getPedido_cancela() {
		return pedido_cancela;
	}

	public void setPedido_cancela(Enumeracao pedido_cancela) {
		this.pedido_cancela = pedido_cancela;
	}

	public Enumeracao getVendedor() {
		return vendedor;
	}

	public void setVendedor(Enumeracao vendedor) {
		this.vendedor = vendedor;
	}

	public Enumeracao getUsuario_acesso() {
		return usuario_acesso;
	}

	public void setUsuario_acesso(Enumeracao usuario_acesso) {
		this.usuario_acesso = usuario_acesso;
	}

	public Enumeracao getTitulo_aberto() {
		return titulo_aberto;
	}

	public void setTitulo_aberto(Enumeracao titulo_aberto) {
		this.titulo_aberto = titulo_aberto;
	}

	public Enumeracao getTitulo_liquidado() {
		return titulo_liquidado;
	}

	public void setTitulo_liquidado(Enumeracao titulo_liquidado) {
		this.titulo_liquidado = titulo_liquidado;
	}

	public UsuarioParametro(Usuario usuario, Enumeracao dashboard, Enumeracao cliente, Enumeracao estoque,
			Enumeracao mercadoria, Enumeracao pedido_novo, Enumeracao pedido_troca, Enumeracao pedido_cancela,
			Enumeracao vendedor, Enumeracao usuario_acesso, Enumeracao titulo_aberto, Enumeracao titulo_liquidado) {
		this.usuario = usuario;
		this.dashboard = dashboard;
		this.cliente = cliente;
		this.estoque = estoque;
		this.mercadoria = mercadoria;
		this.pedido_novo = pedido_novo;
		this.pedido_troca = pedido_troca;
		this.pedido_cancela = pedido_cancela;
		this.vendedor = vendedor;
		this.usuario_acesso = usuario_acesso;
		this.titulo_aberto = titulo_aberto;
		this.titulo_liquidado = titulo_liquidado;
	}

	public UsuarioParametro() {
		super();
	}

}
