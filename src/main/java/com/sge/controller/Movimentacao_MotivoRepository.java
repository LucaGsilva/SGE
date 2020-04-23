package com.sge.controller;

import org.springframework.data.repository.CrudRepository;

import com.sge.model.Enumeracao;
import com.sge.model.Movimentacao_Motivo;

public interface Movimentacao_MotivoRepository extends CrudRepository<Movimentacao_Motivo, Long> {

	
	Iterable<Movimentacao_Motivo> findByTipo(Enumeracao tipo);
	
}
