/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É RESPONSAVEL  POR CONTROLAR AS REQUISIÇOES REALIZADAS, LISTANDO AS PERMISSÕES 
*  NECESSÁRIAS PARA ACESSAR OS LINKS E DIRECIONANDO PARA PAGINA DE LOGIN E LOGOUT QUANDO 
*  NÃO AUTENTICADOS
*
*	----------------------------------ALTERAÇÕES---------------------------------------------
*	DESENVOLVEDOR: XXXX
*	DATA: XX/XX/XX
*	MOTIVO: XXXXXXXXXXX
*	ALTERAÇÃO: XXXXXXXXXXXX
*------------------------------------------------------------------------------------------------ */

package com.sge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.sge.permissoes.Login;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Login userPermissao;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/Logado/**").authenticated()
			.antMatchers("/Index").hasRole("PG_DASHBOARD")
			.antMatchers("/").hasRole("PG_DASHBOARD")
			.antMatchers("/Dasboard/**").hasRole("PG_DASHBOARD")
			.antMatchers("/Mercadoria**/**").hasRole("PG_MERCADORIA")
			.antMatchers("/Vendedor**/**").hasRole("PG_VENDEDOR")
			.antMatchers("/Cliente**/**").hasRole("PG_CLIENTE")
			.antMatchers("/MovimentacaoMotivo/**").hasRole("PG_ESTOQUE")
			.antMatchers("/Estoque**/**").hasRole("PG_ESTOQUE")
			.antMatchers("/Usuario**/**").hasRole("PG_USUARIO")
			.antMatchers("/User**").hasRole("PG_USUARIO")
			.antMatchers("/Pedido**/**").hasRole("PG_PEDIDO_NOVO")
			.antMatchers("/Pedidoitem**/**").hasRole("PG_PEDIDO_NOVO")
			.antMatchers("/Listagem-Pedido**/**").hasRole("PG_PEDIDO_LISTAGEM")
			.antMatchers("/Titulo/Aberto**/**").hasRole("PG_TITULO_ABERTO")
			.antMatchers("/Titulo/Liquidado**/**").hasRole("PG_TITULO_LIQUIDADO")
			.antMatchers("/Movimentacoes**/**").hasRole("PG_MOVIMENTACAO")
			.antMatchers("/MovimentacaoMotivo**/**").hasRole("PG_MOVIMENTACAO")
			.antMatchers("/Pedidos/Cancelamento/add").hasRole("PG_PEDIDO_CANCELA")
			.antMatchers("/css/**").permitAll()
			.antMatchers("/resources/static/**").permitAll()
			.and()
			.formLogin()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/")
			.and()
			.logout()
			.logoutSuccessUrl("/login?logout")
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true)
			.and()
			.sessionManagement().maximumSessions(1).expiredUrl("/login");
		

	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userPermissao);

	}
}
