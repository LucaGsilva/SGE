
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DE PERSISTENCIA DO CONTROLLER PEDIDOITEM
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

import com.sge.model.PedidoItem;

public interface PedidoItemRepository extends CrudRepository<PedidoItem, Long> {

	@Query(value = "SELECT * FROM pedido_item p WHERE p.pedido_id = :pedido", nativeQuery = true)
	PedidoItem findBypedido(long pedido);
}
