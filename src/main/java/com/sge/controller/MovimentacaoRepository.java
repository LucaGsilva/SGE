package com.sge.controller;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sge.model.Movimentacao;

public interface MovimentacaoRepository extends CrudRepository<Movimentacao, Long> {

	@Query(value = "SELECT * FROM movimentacao m WHERE m.mercadoria_id = :mercadoria", nativeQuery = true)
	Iterable<Movimentacao> findByMercadoria(Long mercadoria);
	
}
