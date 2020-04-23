$(document).ready(function () {
    $('#tabela_mercadoria').dataTable({
        "paging": true,
        "info": true,
        "searching": true,
        "keys": true,
        ajax: {
            url: "/Estoques/show",
            type: "GET",
            dataSrc: ''
        },
        columns: [
            { data: "mercadoria.id" },
            {
                data: "mercadoria.nome"
            }, {
                data: "mercadoria.preco"
            },
            {
                data: "mercadoria.codBarras"
            },
            {
                data: "qtd_estoque"
            }]
    });

});

// Chama a função de leitura para executar outras ações na tabela
$(document).ready(function () {

    $("#PesquisaMercadoria").on("click", function () {
        PreencheTabela();
    });

    function PreencheTabela() {

        var table = $('#tabela_mercadoria').DataTable();
        table.search('').columns().search('').draw();
        table.ajax.reload();

    };

    $("#tabela_mercadoria").DataTable();
    var table = $("#tabela_mercadoria").DataTable();
    var data = table.row(this).data();

    //Habilita seleção de linha
    $("#tabela_mercadoria tbody").on("click", "tr", function () {


        if ($(this).hasClass("selec")) {
            $(this).removeClass("selec");

        } else {
            table.$("tr.selec").removeClass("selec");
            $(this).addClass("selec");


        }
    });

    //Busca linha selecionada
    $("#tabela_mercadoria tbody").on("click", ".delbutton", function (e) {
        e.preventDefault();
        var data = $(this).attr("id");
        var info = "key=" + data;
        var row = table.row($(this).parents("tr"));
    });


    // insere o codigo e o nome do cliente no pedido
    $("#btnselecionar_mercadoria").click(function () {
        dados = table.rows('.selec').data();

        try {
            $('#CodigoMercadoria').val(dados[0].mercadoria.id);
            $('#Descricao').html(dados[0].mercadoria.nome);
            $('#adicionar_quantidade').val(1);

        } catch (error) {
            $('#CodigoMercadoria').val('erro');
            $('#Descricao').html('erro');
            $('#adicionar_quantidade').val(0);

        }

        $('#CodigoMercadoria').focus();


    });


});