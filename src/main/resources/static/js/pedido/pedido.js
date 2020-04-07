
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

    //Preenche tabela com dados atualizados
    function PreencheTabela() {
        var table = $('#tabela_pedido').DataTable();

        $.ajax({
            url: "/Vendedores/show",
            type: "GET",
            dataSrc: '',

            columns: [{
                data: "id"
            }, {
                data: "nome"
            }, {
                data: "ativo"
            }]
        });
        table.ajax.reload();

    };

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

    //Envia dados por metodo Post
    function salvar() {

        dados = table.rows('.selec').data();
        var nome = document.getElementById('nome');
        var ativo = document.getElementById('ativo');

        try {
            cod = dados[0].id;
        } catch (error) {
            cod = 0;
        }

        if (nome.value == null || ativo.value == null) {
            alert("Dados inválido, por favor preencha o campo")
        }

        else {
            $.ajax({
                url: "/Vendedores/add",
                type: "POST",
                //data: JSON.stringify({nome:nome.value,email:email.value}),
                data: JSON.stringify({
                    id: cod,
                    nome: nome.value,
                    ativo: ativo.value
                }),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    alert("Dados Gravado");
                }

            });;
            DesabilitaBtn();
            setTimeout(function () {
                LimparTabela();
                PreencheTabela();
            }, 140);
        }

    };

    //Limpar dados do Cadastro
    function limparDados() {
        $('#nome').val('');
        $('#ativo').val('N');
        $('#modelo-cadastro').modal('toggle')
    }


    //Limpar toda a tabela
    function LimparTabela() {
        table.rows("[role=row]").remove().draw(false);
    }

    // Editar cadastro
    $("#btneditar").click(function () {
        dados = table.rows('.selec').data();
        $('#nome').val(dados[0].nome);
        $('#ativo').val(dados[0].ativo);
    });

});