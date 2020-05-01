package com.sge.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PedidoCancelado {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@MapsId
	Pedido pedido;

	@OneToOne
	Usuario usuario;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data_cancelamento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Calendar data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getData_cancelamento() {
		return data_cancelamento;
	}

	public void setData_cancelamento(Calendar data_cancelamento) {
		this.data_cancelamento = data_cancelamento;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public PedidoCancelado(Pedido pedido, Usuario usuario, Calendar data_cancelamento, Calendar data) {
		super();
		this.pedido = pedido;
		this.usuario = usuario;
		this.data_cancelamento = data_cancelamento;
		this.data = data;
	}

	public PedidoCancelado() {
		super();
	}

}
