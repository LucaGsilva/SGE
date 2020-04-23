$(document).ready(function () {
    $('#tabela_cliente').dataTable({
        "paging": true,
        "info": true,
        "searching": true,
        "keys": true,
        ajax: {
            url: "/Clientes/show/",
            type: "GET",
            dataSrc: ''
        },
        columns: [{
            data: "id"
        }, {
            data: "nome"
        },
        {
            data: "cidade"
        },
        {
            data: "estado"
        }],
    });

});

// Chama a função de leitura para executar outras ações na tabela
$(document).ready(function () {

    $("#PesquisaCliente").on("click", function () {
        PreencheTabela();
    });

    function PreencheTabela() {

        var table = $('#tabela_cliente').DataTable();
        table.search('').columns().search('').draw();
        table.ajax.reload();

    };

    $("#tabela_cliente").DataTable();
    var table = $("#tabela_cliente").DataTable();
    var data = table.row(this).data();

    //Habilita seleção de linha
    $("#tabela_cliente tbody").on("click", "tr", function () {

        if ($(this).hasClass("selec")) {
            $(this).removeClass("selec");
            DesabilitaBtn();
        } else {
            table.$("tr.selec").removeClass("selec");
            $(this).addClass("selec");


        }
    });

    //Busca linha selecionada
    $("#tabela_cliente tbody").on("click", ".delbutton", function (e) {
        e.preventDefault();
        var data = $(this).attr("id");
        var info = "key=" + data;
        var row = table.row($(this).parents("tr"));
    });


    // insere o codigo e o nome do cliente no pedido
    $("#btnselecionar_cliente").click(function () {
        dados = table.rows('.selec').data();
        try {
            $('#CodigoCliente').val(dados[0].id);
            $('#NomeCliente').html(dados[0].nome);
        } catch (error) {
            $('#CodigoCliente').val('');
            $('#NomeCliente').html('');
        }

        $('#CodigoCliente').focus();

    });


});