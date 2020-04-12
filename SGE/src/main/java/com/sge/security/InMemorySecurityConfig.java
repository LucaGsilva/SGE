

/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE CRIA UM USUARIO AUTOMATICAMENTE E NÃO PERSISTE NO BANCO É APENAS EM MEMORIA.
*  CASO A CLASE LOGIN NÃO ESTEJA COMENTADA ESSA CLASE É IGNORADA, BUSCANSO UM USUARIO NO 
*  BANCO DE DADOS PARA REALIZAR A AUTENTICAÇÃO 
*
*	----------------------------------ALTERAÇÕES---------------------------------------------
*	DESENVOLVEDOR: XXXX
*	DATA: XX/XX/XX
*	MOTIVO: XXXXXXXXXXX
*	ALTERAÇÃO: XXXXXXXXXXXX
*------------------------------------------------------------------------------------------------ */

/*
package com.sge.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class InMemorySecurityConfig {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		builder.inMemoryAuthentication()
		.withUser("master").password(encoder.encode("master@123"))
				.roles("PG_INDEX", "PG_MERCADORIA", "PG_CLIENTE", "PG_VENDEDOR", "PG_ESTOQUE", "PG_USUARIO")
				.and()
				.withUser("teste").password(encoder.encode("teste"))
				.roles("PG_INDEX","PG_CLIENTE", "PG_VENDEDOR");
	}
}

*/