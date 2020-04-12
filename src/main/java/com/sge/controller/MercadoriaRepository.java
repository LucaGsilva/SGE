
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DE PERSISTENCIA DO CONTROLLER MERCADORIA
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

import com.sge.model.Mercadoria;

public interface MercadoriaRepository extends CrudRepository<Mercadoria, Integer> {

	Optional<Mercadoria> findById(Long id);

	@Query(value = "SELECT * FROM mercadoria m WHERE m.nome LIKE CONCAT('%',:nome,'%')", nativeQuery = true)
	Optional<Mercadoria> findByName(String nome);

	@Query(value = "SELECT * FROM mercadoria m WHERE m.cod_barras = :cod_barras", nativeQuery = true)
	Mercadoria findByCodBarra(long cod_barras);

}
