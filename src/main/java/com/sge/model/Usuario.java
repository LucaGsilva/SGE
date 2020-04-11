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

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Usuario {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@OneToOne(mappedBy = "usuario")
	private UsuarioParametro usuario;

	@OneToMany(mappedBy = "usuario")
	private List<Pedido> pedido;

	@Column(unique = true)
	@NotEmpty
	private String login;

	@Column(unique = true)
	@NotEmpty
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private String nome;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario(String nome, String login, String password) {
		super();
		this.nome = nome;
		this.login = login;
		this.password = password;
	}

	public Usuario() {
		super();
	}

}