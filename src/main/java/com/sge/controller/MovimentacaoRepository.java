package com.sge.controller;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sge.model.Movimentacao;

public interface MovimentacaoRepository extends CrudRepository<Movimentacao, Long> {

	@Query(value = "SELECT * FROM movimentacao m WHERE m.mercadoria_id = :mercadoria", nativeQuery = true)
	Iterable<Movimentacao> findByMercadoria(Long mercadoria);

	@Query(value = "SELECT * from movimentacao m group by m.mercadoria_id", nativeQuery = true)
	Iterable<Movimentacao> findByGrupo();

	@Query(value = "SELECT * from movimentacao m order by m.data asc", nativeQuery = true)
	Set<Movimentacao> findByFilter();

}
