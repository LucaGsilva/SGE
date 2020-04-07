
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DE PERSISTENCIA DO CONTROLLER USUARIOPARAMETRO
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

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sge.model.UsuarioParametro;

public interface UsuarioParametroRepositiry extends CrudRepository<UsuarioParametro, Long> {

	Iterable<UsuarioParametro> findAll();

	UsuarioParametro findById(long id);
	
	@Query(value = "select * from usuario_parametro p join usuario u\r\n" + 
			"on p.usuario_id = u.id\r\n" + 
			"where u.login = :login", nativeQuery = true)
	UsuarioParametro findByLoginParametro(String login);
	
	
	
}
