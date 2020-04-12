
$(document).ready(function () {
    $('#tabela_pedido').dataTable({
        "paging": false,
        "info": false,
        "searching": false
    });

});

// Chama a função de leitura para executar outras ações na tabela
$(document).ready(function () {

    $("#tabela_pedido").DataTable();
    var table = $("#tabela_pedido").DataTable();
    var data = table.row(this).data();

    //Habilita seleção de linha
    $("#tabela_pedido tbody").on("click", "tr", function () {

        if ($(this).hasClass("selec")) {
            $(this).removeClass("selec");
        } else {
            table.$("tr.selec").removeClass("selec");
            $(this).addClass("selec");

        }
    });

    //Busca linha selecionada
    $("#tabela_pedido tbody").on("click", ".delbutton", function (e) {
        e.preventDefault();
        var data = $(this).attr("id");
        var info = "key=" + data;
        var row = table.row($(this).parents("tr"));
    });

});