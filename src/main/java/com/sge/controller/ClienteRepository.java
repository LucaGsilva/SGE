
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DE PERSISTENCIA DO CONTROLLER CLIENTE
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

import com.sge.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

	Iterable<Cliente> findById(Long id);

	@Query(value = "SELECT * FROM cliente m WHERE m.nome LIKE CONCAT('%',:nome,'%')", nativeQuery = true)
	Iterable<Cliente> findByName(String nome);
	
}
