package com.sge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.Enumeracao;
import com.sge.model.Movimentacao_Motivo;

@RestController
@RequestMapping("/MovimentacaoMotivo")
public class Movimentacao_MotivoController {

	Movimentacao_MotivoControllerValidate validate = new Movimentacao_MotivoControllerValidate();

	@Autowired
	private Movimentacao_MotivoRepository Repositorio;

	@PostMapping("/add")
	public void addMotivo(@RequestBody Movimentacao_Motivo motivo) {

		try {

			if (validate.Validate(motivo)) {
				Repositorio.save(motivo);
			}
		} catch (Exception e) {

		}
	}

	@GetMapping("/show")
	public Iterable<Movimentacao_Motivo> show() {
		return Repositorio.findAll();
	}

	@GetMapping("/show/{tipo}")
	public Iterable<Movimentacao_Motivo> show(@PathVariable(value = "tipo") Enumeracao tipo) {

		return Repositorio.findByTipo(tipo);

	}

}
