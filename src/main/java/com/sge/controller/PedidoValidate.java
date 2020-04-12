
/*-------------------------------------COMENTARIOS------------------------------------------------
* 
*
*
*  ESSA CLASE É RESPONSAVEL POR REALIZAR AS VALIDAÇÕES DE INFORMAÇOES ANTES DA PERSISTENCIA
*  
*  INVOCADA NA CLASSE PEDIDOCONTROLLER
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sge.model.Cliente;
import com.sge.model.Pedido;
import com.sge.model.Usuario;
import com.sge.model.Vendedor;

@Component
public class PedidoValidate {

	@Autowired
	private UsuarioRepositiry repUser;

	@Autowired
	private ClienteRepository repCli;

	@Autowired
	private VendedorRepository repVend;

	public boolean ValidatePedido(Pedido ped) {

		System.out.println("User" + ValidateUser(ped.getUsuario()) + "Cliente" + ValidateCliente(ped.getCliente())
				+ "Vendedor" + ValidateVendedor(ped.getVendedor()));
		if (ValidateUser(ped.getUsuario()) && ValidateCliente(ped.getCliente())
				&& ValidateVendedor(ped.getVendedor())) {
			return true;
		} else {
			return false;
		}
	}

	private boolean ValidateUser(Usuario user) {

		if (repUser.findById(user.getId()) != null) {
			return true;
		} else {
			return false;
		}

	}

	private boolean ValidateCliente(Cliente cli) {

		if (repCli.findById(cli.getId()) != null) {
			return true;
		} else {
			return false;
		}

	}

	private boolean ValidateVendedor(Vendedor vend) {
		if (repVend.findById(vend.getId()) != null) {
			return true;
		} else {
			return false;
		}
	}
}
