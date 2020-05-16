
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DA CLASE MODELO ESTOQUE
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

import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.Enumeracao;
import com.sge.model.Estoque;
import com.sge.model.Movimentacao;
import com.sge.model.Movimentacao_Motivo;

@RestController
@RequestMapping("/Estoques")
public class EstoqueController {

	@Autowired
	private EstoqueRepository rep;

	@Autowired
	private MovimentacaoRepository RepMovimenta;

	@Autowired
	private Movimentacao_MotivoRepository Repositorio;

	private Movimentacao_MotivoControllerValidate ValidateMovimentacao = new Movimentacao_MotivoControllerValidate();

	public EstoqueController(EstoqueRepository rep, MovimentacaoRepository repMovimenta,
			Movimentacao_MotivoRepository repositorio) {
		super();
		this.rep = rep;
		RepMovimenta = repMovimenta;
		Repositorio = repositorio;
	}

	private EstoqueValidate validate = new EstoqueValidate();

	@PostMapping("/add")
	public void addEstoque(@RequestBody Estoque estoque) {

		Calendar data = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));

		if (rep.VerificaMercadoria(estoque.getMercadoria().getId()) != null) {
			if (validate.Validate(estoque)) {

				if (estoque.getTipo_Movimentacao().equals(Enumeracao.Entrada)) {
					Estoque est = new Estoque();
					Movimentacao movimentacao = new Movimentacao();

					est = rep.findByUnicaMercadoria(estoque.getMercadoria().getId());

					est.setQtd_estoque(est.getQtd_estoque() + estoque.getQtd_estoque());
					rep.save(est);

					movimentacao.setTipo(Enumeracao.Entrada);
					movimentacao.setData(data.getInstance());
					movimentacao.setEstoque_Atual(est.getQtd_estoque());
					movimentacao.setMercadoria(estoque.getMercadoria());
					movimentacao.setQtd(estoque.getQtd_estoque());
					movimentacao.setAtividade(estoque.getObservacao());
					RepMovimenta.save(movimentacao);
				}

				if (estoque.getTipo_Movimentacao().equals(Enumeracao.Saida)) {
					Estoque est = new Estoque();
					Movimentacao movimentacao = new Movimentacao();

					est = rep.findByUnicaMercadoria(estoque.getMercadoria().getId());

					est.setQtd_estoque(est.getQtd_estoque() - estoque.getQtd_estoque());
					rep.save(est);

					movimentacao.setTipo(Enumeracao.Entrada);
					movimentacao.setData(data.getInstance());
					movimentacao.setEstoque_Atual(est.getQtd_estoque());
					movimentacao.setMercadoria(estoque.getMercadoria());
					movimentacao.setQtd(estoque.getQtd_estoque());
					movimentacao.setAtividade(estoque.getObservacao());
					RepMovimenta.save(movimentacao);
				}
			}
		}

	}

	@PostMapping("/MovimentacaoMotivo/add")
	public void addMotivo(@RequestBody Movimentacao_Motivo motivo) {

		try {

			if (ValidateMovimentacao.Validate(motivo)) {
				Repositorio.save(motivo);
			}
		} catch (Exception e) {

		}
	}

	@GetMapping("/show")
	public Iterable<Estoque> show() {
		return rep.findAll();
	}

	@GetMapping("/show/{cod_mercadoria}")
	public Iterable<Estoque> show(@PathVariable(value = "cod_mercadoria") Long cod_mercadoria) {

		return rep.findByMercadoria(cod_mercadoria);

	}

	@GetMapping("/ShowMovimentacao/{tipo}")
	public Iterable<Movimentacao_Motivo> ShowMovimentacao(@PathVariable(value = "tipo") Enumeracao tipo) {

		return Repositorio.findByTipo(tipo);
	}

}
