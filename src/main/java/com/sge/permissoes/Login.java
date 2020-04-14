/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É RESPONSAVEL POR VERIFICAR SE O LOGIN INSERIDO É EXISTENTENTE NO BANCO DE DADOS, 
*  CASO SEJA EXISTENTE RETORNA COM AS PERMISSÕES DO USUARIO.
*
*	----------------------------------ALTERAÇÕES---------------------------------------------
*
*	DESENVOLVEDOR: XXXX
*	DATA: XX/XX/XX
*	MOTIVO: XXXXXXXXXXX
*	ALTERAÇÃO: XXXXXXXXXXXX
*
*------------------------------------------------------------------------------------------------ */

package com.sge.permissoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sge.controller.UsuarioParametroRepositiry;
import com.sge.model.UsuarioParametro;

@Component
public class Login implements UserDetailsService {

	private final UsuarioParametroRepositiry useRepositiry;

	List<String> Permissao = new ArrayList<String>();

	@Autowired
	public Login(UsuarioParametroRepositiry useRepositiry) {
		this.useRepositiry = useRepositiry;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		UsuarioParametro user = Optional.ofNullable(useRepositiry.findByLoginParametro(login))
				.orElseThrow(() -> new UsernameNotFoundException("Usuario Inexistente"));
		Permissao.clear(); // É necessario limpar a lista para que as permissões do primeiro usuario
		// que fez login nao seja replicada para os proximos logins efetuados
		VerificaPermissao(user);
		List<GrantedAuthority> concessoes = AuthorityUtils.createAuthorityList(Permissao.get(0), Permissao.get(1),
				Permissao.get(2), Permissao.get(3), Permissao.get(4), Permissao.get(5), Permissao.get(6),
				Permissao.get(7), Permissao.get(8), Permissao.get(9), Permissao.get(10), Permissao.get(11));

		return new User(user.getUsuario().getLogin(), user.getUsuario().getPassword(), concessoes);
	}

	private void VerificaPermissao(UsuarioParametro usuario) {

		if (usuario.getCliente().toString() == "S") {
			Permissao.add("ROLE_PG_CLIENTE");
		} else {
			Permissao.add("N");
		}

		if (usuario.getEstoque().toString() == ("S")) {
			Permissao.add("ROLE_PG_ESTOQUE");
		} else {
			Permissao.add("N");
		}

		if (usuario.getMercadoria().toString() == ("S")) {
			Permissao.add("ROLE_PG_MERCADORIA");
		} else {
			Permissao.add("N");
		}

		if (usuario.getPedido_novo().toString() == ("S")) {
			Permissao.add("ROLE_PG_PEDIDO_NOVO");
		} else {
			Permissao.add("N");
		}
		if (usuario.getPedido_cancela().toString() == ("S")) {
			Permissao.add("ROLE_PG_PEDIDO_CANCELA");
		} else {
			Permissao.add("N");
		}
		if (usuario.getPedido_troca().toString() == ("S")) {
			Permissao.add("ROLE_PG_PEDIDO_TROCA");
		} else {
			Permissao.add("N");
		}
		if (usuario.getVendedor().toString() == ("S")) {
			Permissao.add("ROLE_PG_VENDEDOR");
		} else {
			Permissao.add("N");
		}
		if (usuario.getTitulo_aberto().toString() == ("S")) {
			Permissao.add("ROLE_PG_TITULO_ABERTO");
		} else {
			Permissao.add("N");
		}
		if (usuario.getTitulo_liquidado().toString() == ("S")) {
			Permissao.add("ROLE_PG_TITULO_LIQUIDADO");
		} else {
			Permissao.add("N");
		}
		if (usuario.getDashboard().toString() == ("S")) {
			Permissao.add("ROLE_PG_DASHBOARD");
		} else {
			Permissao.add("N");
		}
		if (usuario.getUsuario_acesso().toString() == ("S")) {
			Permissao.add("ROLE_PG_USUARIO");
		} else {
			Permissao.add("N");
		}
		if (usuario.getPedido_listagem().toString() == ("S")) {
			Permissao.add("ROLE_PG_PEDIDO_LISTAGEM");
		} else {
			Permissao.add("N");
		}
	}

}
