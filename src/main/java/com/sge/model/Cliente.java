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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "cliente")
	private Pedido pedido;

	@Column(unique = true)
	private String cpf;
	private String nome, endereco, cidade, fone, email, estado,cep;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cliente(String cpf, String nome, String endereco, String cidade, String fone, String email, String estado,String cep) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
		this.cidade = cidade;
		this.fone = fone;
		this.email = email;
		this.estado = estado;
		this.cep = cep;
	}

	public Cliente() {
		super();
	}

}
