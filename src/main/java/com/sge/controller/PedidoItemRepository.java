
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

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sge.model.PedidoItem;

public interface PedidoItemRepository extends CrudRepository<PedidoItem, Long> {

	@Query(value = "SELECT * FROM pedido_item p WHERE p.pedido_id = :pedido", nativeQuery = true)
	Iterable<PedidoItem> findBypedido(long pedido);

	@Query(value = "SELECT * FROM pedido_item p WHERE p.pedido_id = :pedido", nativeQuery = true)
	List<PedidoItem> findBypedidoId(long pedido);

	@Query(value = "SELECT  count(*) FROM pedido_item p WHERE p.pedido_id = :pedido and p.mercadoria_id = :mercadoria", nativeQuery = true)
	int findByPedidoMercadoria(long pedido, long mercadoria);

	@Query(value = "SELECT * FROM pedido_item p WHERE p.pedido_id = :pedido and p.mercadoria_id = :mercadoria", nativeQuery = true)
	PedidoItem findByMercadoriaPedido(long pedido, long mercadoria);

}
