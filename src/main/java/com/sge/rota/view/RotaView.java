/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É RESPONSAVEL POR DIRECIONAR AS CHAMADAS DA URL PARA AS PAGINAS CONTIDAS NA 
*  PASTA TEMPLATES
*
*	----------------------------------ALTERAÇÕES---------------------------------------------
*	DESENVOLVEDOR: XXXX
*	DATA: XX/XX/XX
*	MOTIVO: XXXXXXXXXXX
*	ALTERAÇÃO: XXXXXXXXXXXX
*------------------------------------------------------------------------------------------------ */

package com.sge.rota.view;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RotaView implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("Index");

		registry.addViewController("/Index").setViewName("Index");
		registry.addViewController("/Cliente").setViewName("Cliente");
		registry.addViewController("/Estoque").setViewName("Estoque");
		registry.addViewController("/Mercadoria").setViewName("Mercadoria");
		registry.addViewController("/Usuario").setViewName("Usuario");
		registry.addViewController("/Vendedor").setViewName("Vendedor");
		registry.addViewController("/Pedido").setViewName("Pedido");
	}

}
