$(document).ready(function () {
    $('#tabela_vendedor').dataTable({
        "paging": true,
        "info": true,
        "searching": true,
        "keys": true,
        ajax: {
            url: "/Pedidoitem/Vendedores/show/ativo",
            type: "GET",
            dataSrc: ''
        },
        columns: [{
            data: "id"
        }, {
            data: "nome"
        }],
    });

});

// Chama a função de leitura para executar outras ações na tabela
$(document).ready(function () {

    $("#PesquisaVendedor").on("click", function () {
        PreencheTabela();
    });

    function PreencheTabela() {

        var table = $('#tabela_vendedor').DataTable();
        table.search('').columns().search('').draw();
        table.ajax.reload();

    };

    $("#tabela_vendedor").DataTable();
    var table = $("#tabela_vendedor").DataTable();
    var data = table.row(this).data();

    //Habilita seleção de linha
    $("#tabela_vendedor tbody").on("click", "tr", function () {

        if ($(this).hasClass("selec")) {
            $(this).removeClass("selec");

        } else {
            table.$("tr.selec").removeClass("selec");
            $(this).addClass("selec");


        }
    });

    //Busca linha selecionada
    $("#tabela_vendedor tbody").on("click", ".delbutton", function (e) {
        e.preventDefault();
        var data = $(this).attr("id");
        var info = "key=" + data;
        var row = table.row($(this).parents("tr"));
    });


    // insere o codigo e o nome do cliente no pedido
    $("#btnselecionar").click(function () {
        dados = table.rows('.selec').data();
        try {
            $('#CodigoVendedor').val(dados[0].id);
            $('#NomeVendedor').html(dados[0].nome);

        } catch (error) {
            $('#CodigoVendedor').val('');
            $('#NomeVendedor').html('');
        }

        $('#CodigoVendedor').focus();


    });


});