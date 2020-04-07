
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DE PERSISTENCIA DO CONTROLLER ESTOQUE
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

import com.sge.model.Estoque;

public interface EstoqueRepository extends CrudRepository<Estoque, Long> {

	@Query(value = "SELECT * FROM estoque e WHERE e.mercadoria_id = :cod_mercadoria", nativeQuery = true)
	Iterable<Estoque> findByMercadoria(Long cod_mercadoria);

	@Query(value = "SELECT e.mercadoria_id FROM estoque e WHERE e.mercadoria_id = :cod_mercadoria", nativeQuery = true)
	Integer VerificaMercadoria(Long cod_mercadoria);
}
