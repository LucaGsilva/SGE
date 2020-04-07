$(function () {
    $('#nome_usuario').append("Lucas");
});

$(document).ready(function () {
    $('#tabela').dataTable({
        ajax: {
            url: "usuario/show/",
            type: "GET",
            dataSrc: ''
        },
        columns: [
            {
                data: "nome"
            },
            {
                data: "ativo"
            }
        ]
    });

});


// Chama a função de leitura para executar outras ações na tabela
$(document).ready(function () {
    $("#tabela").DataTable();
    var table = $("#tabela").DataTable();
    var data = table.row(this).data();

    //Habilita seleção de linha
    $("#tabela tbody").on("click", "tr", function () {

        if ($(this).hasClass("selec")) {
            $(this).removeClass("selec");
        } else {
            table.$("tr.selec").removeClass("selec");
            $(this).addClass("selec");

        }
    });

    //Busca linha selecionada
    $("#tabela tbody").on("click", ".delbutton", function (e) {
        e.preventDefault();
        var data = $(this).attr("id");
        var info = "key=" + data;
        var row = table.row($(this).parents("tr"));
    });

    // Adiciona Linha
    $("#btnadd").on("click", function () {
        table.row
            .add({ nome: 2, ativo: 2 })
            .draw(false);
        limparDados();
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
        table
            .row(".selec")
            .remove()
            .draw(false);
    });
});