package com.sge.controller;

import com.sge.model.Vendedor;

public class VendedorValidate {

	public boolean ValidaVendedor(Vendedor vendedor) {
		if (ValidateNome(vendedor)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean ValidateNome(Vendedor vendedor) {

		if (vendedor.getNome().trim().length() != 0) {
			return true;
		} else {
			return false;

		}
	}

}
