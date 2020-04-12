$(function () {
    $('#nome_usuario').append("Lucas");
});

$(document).ready(function () {
    $('#tabela').dataTable({
        ajax: {
            url: "https://viacep.com.br/ws/RS/Porto Alegre/Domingos/json/",
            type: "GET",
            dataSrc: ''
        },
        columns: [
            {
                data: "cep"
            },
            {
                data: "bairro"
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
            .add({ cep: "praca", logradouro: "Feira", bairro: "Feira" })
            .draw(false);
    });

    //Remove Linha da tabela
    $("#button").click(function () {
        table
            .row(".selec")
            .remove()
            .draw(false);
    });
});