
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DE PERSISTENCIA DO CONTROLLER USUARIO
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



package com.sge.controller;

import org.springframework.data.repository.CrudRepository;

import com.sge.model.Usuario;

public interface UsuarioRepositiry extends CrudRepository<Usuario, Long> {

	Iterable<Usuario> findAll();

	Usuario findById(long id);

	Usuario findByLogin(String login);
	
}
