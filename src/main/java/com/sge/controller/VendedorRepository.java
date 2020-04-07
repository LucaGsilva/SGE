
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DE PERSISTENCIA DO CONTROLLER VENDEDOR
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

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sge.model.Vendedor;

public interface VendedorRepository extends CrudRepository<Vendedor, Integer> {

	Optional<Vendedor> findById(Long id);

	@Query(value = "SELECT * FROM vendedor m WHERE m.nome LIKE CONCAT('%',:nome,'%')", nativeQuery = true)
	Optional<Vendedor> findByName(String nome);

	@Query(value = "SELECT * FROM vendedor where ativo = 'S'", nativeQuery = true)
	Iterable<Vendedor> findByAtivo();
	
	@Query(value = "SELECT * FROM vendedor where ativo = 'S' and id = :codigo", nativeQuery = true)
	Iterable<Vendedor> findByAtivo(long codigo);

}
