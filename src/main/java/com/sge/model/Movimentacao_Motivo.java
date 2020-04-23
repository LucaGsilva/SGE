package com.sge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movimentacao_Motivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String motivo;

	private Enumeracao tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Enumeracao getTipo() {
		return tipo;
	}

	public void setTipo(Enumeracao tipo) {
		this.tipo = tipo;
	}

	public Movimentacao_Motivo(String motivo, Enumeracao tipo) {
		super();
		this.motivo = motivo;
		this.tipo = tipo;
	}

	public Movimentacao_Motivo() {
		super();
	}

}
