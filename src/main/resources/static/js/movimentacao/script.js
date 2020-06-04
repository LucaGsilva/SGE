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

            if (dados.pedido_troca == 'N') {
                document.getElementById('Menu_Pedido_Troca').style.display = "none";
            }

            if (dados.pedido_novo == 'N' && dados.pedido_troca == 'N' && dados.pedido_listagem == 'N') {
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

            if (dados.movimentacao_Estoque == 'N') {
                document.getElementById('Movimentacao').style.display = "none";
            }

            if (dados.estoque == 'N' && dados.movimentacao_Estoque == 'N') {
                document.getElementById('id_estoque').style.display = "none";
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
    $('#tabela').dataTable({
        keys: true,
        keys: {
            editorKeys: 'tab-only'
        },
        ajax: {
            url: "/Movimentacoes/show/grupo/Ambos",
            type: "GET",
            dataSrc: ''
        },
        columns: [{
            data: "mercadoria.id"
        }, {
            data: "mercadoria.nome"
        }]
    });

});

$(document).ready(function () {
    $('#tabela_movimentacao').dataTable({
        keys: true,
        keys: {
            editorKeys: 'tab-only'
        },
        ajax: {
            url: "/Movimentacoes/show/grupo/Ambos",
            type: "GET",
            dataSrc: ''
        },
        columns: [{
            data: "mercadoria.nome"
        }, {
            data: "atividade"
        },
        {
            data: "data"
        }]
    });

});


function HabilitaBtn() {
    $("#btnvisualizar").prop('disabled', false);
};

function DesabilitaBtn() {
    $("#btnvisualizar").prop('disabled', true);
};

// Chama a função de leitura para executar outras ações na tabela
$(document).ready(function () {
    var table = $("#tabela").DataTable();
    var tabelaMovimentacao = $("#tabela_movimentacao").DataTable();

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

    //Limpar os dados fechar
    $("#btnclose").on("click", function () {
        limparDados();
    });

    //Limpar os dados quando clicar em cacelar
    $("#btncancel").on("click", function () {
        limparDados();
    });

    //Limpar os dados quando clicar em Novo
    $("#btnnovo").on("click", function () {
        table.$("tr.selec").removeClass("selec");
        limparDados();
    });


    //Limpar dados do Cadastro
    function limparDados() {
        $('#nome').val('');
        $('#ativo').val('N');
        $('#modelo-cadastro').modal('toggle')
    }

    //Remove Linha da tabela
    $("#button").click(function () {
        table.row(".selec").remove().draw(false);
    });

    //Limpar toda a tabela
    function LimparTabela() {
        table.rows("[role=row]").remove().draw(false);
    }

    $("#tipo").click(function () {


        if ($("#tipo").val() == "1") {
            $(".periodo").css("display", "none");
            $("#data_inicio").val("");
            $("#data_fim").val("");
        }

        if ($("#tipo").val() == "2") {
            $(".periodo").css("display", "block");
        }

    });


    $("#tipo").keyup(function () {

        if ($("#tipo").val() == "1") {
            $(".periodo").css("display", "none");
        }

        if ($("#tipo").val() == "2") {
            $(".periodo").css("display", "block");
        }
    });

    $("#tipo").blur(function (e) {

        if ($("#tipo").val() == "1") {
            $(".periodo").css("display", "none");
        }

        if ($("#tipo").val() == "2") {
            $(".periodo").css("display", "block");
        }

    });


    $("#btnfiltro").click(function () {

        $("#btnvisualizar").prop('disabled', true);

        try {

            tipo = $("#movimento").val();

            if ($("#tipo").val() == 2) {

                if (($("#data_inicio").val() < $("#data_fim").val() || $("#data_inicio").val() == $("#data_fim").val()) && $("#data_inicio").val() != '' && $("#data_fim").val() != '') {


                    dataInicio = $("#data_inicio").val().split("-");
                    dataFinal = $("#data_fim").val().split("-");

                    IniDia = 00 + "-";
                    IniMes = 00 + "-";
                    IniAno = 00;

                    IniDia = dataInicio[2] + "-";
                    IniMes = dataInicio[1] + "-";
                    IniAno = dataInicio[0];

                    FimDia = 00 + "-";
                    FimMes = 00 + "-";
                    FimAno = 00;

                    FimDia = dataFinal[2] + "-";
                    FimMes = dataFinal[1] + "-";
                    FimAno = dataFinal[0];

                    table.ajax.url("/Movimentacoes/show/filter/" + tipo + "/" + IniDia + IniMes + IniAno + "/" + FimDia + FimMes + FimAno + "/0/S").load();
                }
                else {

                    table.ajax.reload();
                }
            }
            else {

                table.ajax.url("/Movimentacoes/show/grupo/" + tipo).load();
            }

        } catch (error) {

        }

    });

    $("#btnvisualizar").click(function () {
        try {
            dados = table.rows('.selec').data();
            cod = dados[0].mercadoria.id;
            tipo = $("#movimento").val();
        } catch (error) {
            cod = 0;
        }

        if (($("#data_inicio").val() < $("#data_fim").val() || $("#data_inicio").val() == $("#data_fim").val()) && $("#data_inicio").val() != '' && $("#data_fim").val() != '') {


            dataInicio = $("#data_inicio").val().split("-");
            dataFinal = $("#data_fim").val().split("-");

            IniDia = 00 + "-";
            IniMes = 00 + "-";
            IniAno = 00;

            IniDia = dataInicio[2] + "-";
            IniMes = dataInicio[1] + "-";
            IniAno = dataInicio[0];

            FimDia = 00 + "-";
            FimMes = 00 + "-";
            FimAno = 00;

            FimDia = dataFinal[2] + "-";
            FimMes = dataFinal[1] + "-";
            FimAno = dataFinal[0];

            tabelaMovimentacao.ajax.url("/Movimentacoes/show/filter/" + tipo + "/" + IniDia + IniMes + IniAno + "/" + FimDia + FimMes + FimAno + "/" + cod + "/N").load();
        }
        else {
            tabelaMovimentacao.ajax.url("/Movimentacoes/show/filter/" + tipo + "/00-00-0000/00-00-0000/" + cod + "/N").load();
        }

    });

});