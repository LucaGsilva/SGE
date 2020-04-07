

/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É CONTROLADORA DA CLASE MODELO MERCADORIA
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sge.model.Estoque;
import com.sge.model.Mercadoria;

@RestController
@RequestMapping("/Mercadorias")
public class MercadoriaController {

	@Autowired
	private MercadoriaRepository rep;

	@Autowired
	private EstoqueRepository repestoque;

	@PostMapping("/add")
	public void addUsuario(@RequestBody Mercadoria merc) {
		MercadoriaValidate valmerc = new MercadoriaValidate();
		if (valmerc.ValidateMercadoria(merc)) {

			merc.setNome(merc.getNome().toUpperCase());
			merc.setReferencia(merc.getReferencia().toUpperCase());
			

			try {
				merc.getPreco();
			} catch (NullPointerException e) {
				merc.setPreco(0.0);
			}

			Estoque est = new Estoque();
			merc.setId(rep.save(merc).getId());
			est.setQtd_estoque(0);
			est.setMercadoria(merc);
			if (repestoque.VerificaMercadoria(merc.getId()) == null) {
				repestoque.save(est);
			}

		}

	}

	@GetMapping("/show")
	public Iterable<Mercadoria> show() {
		return rep.findAll();
	}

	@GetMapping("/show/barras/{barras}")
	public Mercadoria show(@PathVariable(value = "barras") long barras) {

		return rep.findByCodBarra(barras);
	}

	@GetMapping("/show/{nome}")
	public Optional<Mercadoria> show(@PathVariable(value = "nome") String nome) {

		try {
			return rep.findById(Long.parseLong(nome));

		} catch (NumberFormatException e) {
			return rep.findByName(nome);
		}

	}

}
