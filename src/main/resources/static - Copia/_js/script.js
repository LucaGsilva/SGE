//Envia dados por metodo Post
function enviar() {

	var nome = document.getElementById('nome');
	var email = document.getElementById('email');

	if (nome.value == null || email.value == null) {
		alert("Dados inválido, por favor preencha o campo")
	}

	else {
		$.ajax({
			url : "/mercadoria/add",
			type : "POST",
			//data: JSON.stringify({nome:nome.value,email:email.value}),
			data : JSON.stringify({nome : nome.value}),
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				alert("Dados Gravado");
			}
		});

	}

}

// Recupera dados por ID
function pegar() {

	var codigo = document.getElementById('cod');

	$.getJSON("/pedido/show/" + codigo.value, function(dados, status) {

		try {
			$("#usuario_user").val(dados.nome);
			//$("#email_user").val(dados.email);
		} catch (e) {

		}

	});

}
