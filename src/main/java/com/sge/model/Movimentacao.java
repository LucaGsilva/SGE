package com.sge.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	Mercadoria mercadoria;

	int qtd;

	Enumeracao Tipo;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	Calendar data;

	int Estoque_Atual;

	String Atividade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Mercadoria getMercadoria() {
		return mercadoria;
	}

	public void setMercadoria(Mercadoria mercadoria) {
		this.mercadoria = mercadoria;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public Enumeracao getTipo() {
		return Tipo;
	}

	public void setTipo(Enumeracao tipo) {
		Tipo = tipo;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public int getEstoque_Atual() {
		return Estoque_Atual;
	}

	public void setEstoque_Atual(int estoque_Atual) {
		Estoque_Atual = estoque_Atual;
	}

	public String getAtividade() {
		return Atividade;
	}

	public void setAtividade(String atividade) {
		Atividade = atividade;
	}

	public Movimentacao(Mercadoria mercadoria, int qtd, Enumeracao tipo, Calendar data, int estoque_Atual,
			String atividade) {
		super();
		this.mercadoria = mercadoria;
		this.qtd = qtd;
		Tipo = tipo;
		this.data = data;
		Estoque_Atual = estoque_Atual;
		Atividade = atividade;
	}

	public Movimentacao() {
		super();
	}

}