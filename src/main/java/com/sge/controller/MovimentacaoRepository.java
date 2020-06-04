package com.sge.controller;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sge.model.Movimentacao;

public interface MovimentacaoRepository extends CrudRepository<Movimentacao, Long> {

	@Query(value = "SELECT * FROM movimentacao m WHERE m.mercadoria_id = :mercadoria", nativeQuery = true)
	Iterable<Movimentacao> findByMercadoria(Long mercadoria);

	@Query(value = "SELECT * from movimentacao m group by m.mercadoria_id", nativeQuery = true)
	Set<Movimentacao> findByGrupo();

	@Query(value = "SELECT * FROM movimentacao m where DATE_FORMAT(m.data , \"%Y-%m-%d\") between :data_inicio and :data_fim order by m.data asc", nativeQuery = true)
	Set<Movimentacao> findByFilter(String data_inicio, String data_fim);

}
