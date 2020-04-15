$(function () {
    var nome = '';
    $.getJSON("/Logado/show/", function (dados, status) {

        try {
            nome = dados.usuario.nome
            $('#nome_usuario').append(nome);

            if (dados.dashboard == 'N') {
                document.getElementById('Menu_Dashboard').style.display = "none";
            }

            if (dados.pedido_novo == 'N') {
                document.getElementById('Menu_Pedido_Novo').style.display = "none";
            }

            if (dados.pedido_listagem == 'N') {
                document.getElementById('Menu_Pedido_Listagem').style.display = "none";
            }

            if (dados.pedido_cancela == 'N') {
                document.getElementById('Menu_Pedido_Cancela').style.display = "none";
            }
            if (dados.pedido_troca == 'N') {
                document.getElementById('Menu_Pedido_Troca').style.display = "none";
            }

            if (dados.pedido_novo == 'N' && dados.pedido_cancela == 'N' && dados.pedido_troca == 'N' && dados.pedido_listagem == 'N') {
                document.getElementById('Menu_Pedido').style.display = "none";
            }

            if (dados.mercadoria == 'N') {
                document.getElementById('Menu_Mercadoria').style.display = "none";
            }

            if (dados.vendedor == 'N') {
                document.getElementById('Menu_Vendedor').style.display = "none";
            }

            if (dados.cliente == 'N') {
                document.getElementById('Menu_Cliente').style.display = "none";
            }

            if (dados.usuario_acesso == 'N') {
                document.getElementById('Menu_Usuario').style.display = "none";
            }

            if (dados.estoque == 'N') {
                document.getElementById('Menu_Estoque').style.display = "none";
            }

            if (dados.titulo_aberto == 'N') {
                document.getElementById('Menu_Titulo_Aberto').style.display = "none";
            }

            if (dados.titulo_liquidado == 'N') {
                document.getElementById('Menu_Titulo_Liquidado').style.display = "none";
            }

            if (dados.titulo_aberto == 'N' && dados.titulo_liquidado == 'N') {
                document.getElementById('Menu_Titulo').style.display = "none";
            }


        } catch (e) {
            $('#nome_usuario').append('');
        }

    });
});


$(document).ready(function () {

    $("#btnvisualizar").prop('disabled', true);

    $('#tabela').dataTable({
        keys: true,
        order: [[0, 'desc']],

        ajax: {
            url: "/Pedidos/Novos/show",
            type: "GET",
            dataSrc: ''
        },
        columns: [
            { data: "id" },
            {
                data: "data_pedido"
            }, {
                data: "cliente.nome"
            }
            , {
                data: "vendedor.nome"
            }, {
                data: "usuario.nome"
            }, {
                data: "cancelado"
            }, {
                data: "valor_liquido"
            }]
    });


    $('#tabela_pedido').dataTable({
        ajax: {
            url: "/Pedidoitem/show/0",
            type: "GET",
            dataSrc: ''
        },
        columns: [
            { data: "mercadoria.id" },
            {
                data: "mercadoria.nome"
            }, {
                data: "qtd"
            },
            {
                data: "preco"
            }, {
                data: "preco_total"
            }]
    });


    var tabela_pedido = $("#tabela_pedido").DataTable();

    // Chama a função de leitura para executar outras ações na tabela
    $("#tabela").DataTable();
    var table = $("#tabela").DataTable();
    var data = table.row(this).data();

    //Habilita seleção de linha
    $("#tabela tbody").on("click", "tr", function () {

        if ($(this).hasClass("selec")) {
            $(this).removeClass("selec");
            DesabilitaBtn();
        } else {
            table.$("tr.selec").removeClass("selec");
            $(this).addClass("selec");
            HabilitaBtn();

        }
    });

    //Busca linha selecionada
    $("#tabela tbody").on("click", ".delbutton", function (e) {
        e.preventDefault();
        var data = $(this).attr("id");
        var info = "key=" + data;
        var row = table.row($(this).parents("tr"));

    });

    $("#btnvisualizar").click(function (e) {

        LimparTabela();

        PreencheTabela();

    });

    //Limpar toda a tabela
    function LimparTabela() {
        tabela_pedido.rows("[role=row]").remove().draw(false);

    }


    //Preenche tabela com dados atualizados
    function PreencheTabela() {
        var table = $('#tabela_pedido').DataTable();

        var tabela = $('#tabela').DataTable();

        dados = tabela.rows('.selec').data();

        codigo = dados[0].id;

        table.ajax.url("/Pedidoitem/show/" + codigo).load();

        $.getJSON("/Pedidos/Novos/show/" + codigo, function (dados) { 

            $("#finaliza_numero_pedido").val(codigo);
            $("#finaliza_total_liquido").val(dados.valor_liquido);
            $("#finaliza_desconto").val(dados.valor_desconto);
            $("#finaliza_total_bruto").val(dados.valor_bruto);
            $("#finaliza_forma_pagamento").val(dados.formaPagameto);
            $("#finaliza_itens").val(dados.itens);
            $("#finaliza_total_unidades").val(dados.itens_total_unidade);
            $("#finaliza_cliente").val(dados.cliente.id + " - "+dados.cliente.nome);
            $("#finaliza_vendedor").val(dados.vendedor.id + " - " + dados.vendedor.nome);

            finaliza_total_liquido

        });

        

    };


    function HabilitaBtn() {
        $("#btnvisualizar").prop('disabled', false);
    };

    function DesabilitaBtn() {
        $("#btnvisualizar").prop('disabled', true);
    };

});
