/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É O MODELO UTILIZADO NO SISTEMA E NA PERSISTENCIA DE DADOS.
*  AS TABELAS SÃO GERADAS AUTOMATICAMENTE NO BANCO DE DADOS JUNTAMENTE COM SEUS RELACIONAMENTOS
*
*  ----------------------------------ALTERAÇÕES-------------------------------------------------
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
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@MapKeyColumn
	private Mercadoria mercadoria;

	private int qtd_estoque;

	public Long getId() {
		return id;
	}

	public Mercadoria getMercadoria() {
		return mercadoria;
	}

	public void setMercadoria(Mercadoria mercadoria) {
		this.mercadoria = mercadoria;
	}

	public int getQtd_estoque() {
		return qtd_estoque;
	}

	public void setQtd_estoque(int qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}

	public Estoque(Long id, Mercadoria mercadoria, int qtd_estoque) {
		super();
		this.id = id;
		this.mercadoria = mercadoria;
		this.qtd_estoque = qtd_estoque;
	}

	public Estoque() {
		super();
	}

}
