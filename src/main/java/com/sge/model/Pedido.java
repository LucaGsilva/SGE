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

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "pedido")
	private List<PedidoItem> pedidoItem;

	@ManyToOne
	private Vendedor vendedor;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private Usuario usuario;

	private FormaPagamento FormaPagameto;

	// A LISTA DE MERCADORIA NÃO É PERSISTIDA EM BANCO, ESSE ATRIBUTO É UTILIZADO NO
	// PEDIDO
	@Transient
	private List<Mercadoria> mercadoria;

	@Enumerated(EnumType.STRING)
	private Enumeracao cancelado;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_pedido;

	private int itens, itens_total_unidade;

	private Double valor_liquido, valor_bruto, valor_desconto, percentual_desconto;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Mercadoria> getMercadoria() {
		return mercadoria;
	}

	public void setMercadoria(List<Mercadoria> mercadoria) {
		this.mercadoria = mercadoria;
	}

	public Enumeracao getCancelado() {
		return cancelado;
	}

	public void setCancelado(Enumeracao cancelado) {
		this.cancelado = cancelado;
	}

	public Date getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(Date data_pedido) {
		this.data_pedido = data_pedido;
	}

	public Double getValor_desconto() {
		return valor_desconto;
	}

	public void setValor_desconto(Double valor_desconto) {
		this.valor_desconto = valor_desconto;
	}

	public Double getPercentual_desconto() {
		return percentual_desconto;
	}

	public void setPercentual_desconto(Double percentual_desconto) {
		this.percentual_desconto = percentual_desconto;
	}

	public int getItens() {
		return itens;
	}

	public void setItens(int itens) {
		this.itens = itens;
	}

	public int getItens_total_unidade() {
		return itens_total_unidade;
	}

	public void setItens_total_unidade(int itens_total_unidade) {
		this.itens_total_unidade = itens_total_unidade;
	}

	public Double getValor_liquido() {
		return valor_liquido;
	}

	public void setValor_liquido(Double valor_liquido) {
		this.valor_liquido = valor_liquido;
	}

	public Double getValor_bruto() {
		return valor_bruto;
	}

	public void setValor_bruto(Double valor_bruto) {
		this.valor_bruto = valor_bruto;
	}

	public FormaPagamento getFormaPagameto() {
		return FormaPagameto;
	}

	public void setFormaPagameto(FormaPagamento formaPagameto) {
		FormaPagameto = formaPagameto;
	}

	public Pedido(List<PedidoItem> pedidoItem, Vendedor vendedor, Cliente cliente, Usuario usuario,
			FormaPagamento formaPagameto, List<Mercadoria> mercadoria, Enumeracao cancelado, Date data_pedido,
			int itens, int itens_total_unidade, Double valor_liquido, Double valor_bruto, Double valor_desconto,
			Double percentual_desconto) {
		super();
		this.pedidoItem = pedidoItem;
		this.vendedor = vendedor;
		this.cliente = cliente;
		this.usuario = usuario;
		FormaPagameto = formaPagameto;
		this.mercadoria = mercadoria;
		this.cancelado = cancelado;
		this.data_pedido = data_pedido;
		this.itens = itens;
		this.itens_total_unidade = itens_total_unidade;
		this.valor_liquido = valor_liquido;
		this.valor_desconto = valor_desconto;
		this.percentual_desconto = percentual_desconto;
	}

	public Pedido() {
		super();
	}

}