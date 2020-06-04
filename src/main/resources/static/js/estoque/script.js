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
                document.getElementById('Menu_Movimentacao').style.display = "none";
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
                data: "mercadoria.codBarras"
            },
            {
                data: "qtd_estoque"
            }]
    });

});

$("#btneditar").prop('disabled', true);

function HabilitaBtn() {
    $("#btneditar").prop('disabled', false);
};

function DesabilitaBtn() {
    $("#btneditar").prop('disabled', true);
};

// Chama a função de leitura para executar outras ações na tabela
$(document).ready(function () {
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

    //Preenche tabela com dados atualizados
    function PreencheTabela() {
        var table = $('#tabela').DataTable();
        table.ajax.reload();


    };

    // Adiciona Linha
    $("#btnadd").on("click", function () {
        table.row
            .add({ nome: $('#nome').val(), ativo: $('#ativo').val() })
            .draw(false);
        limparDados();
    });

    // Adiciona Linha
    $("#btnsalva").on("click", function () {
        salvar();

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
    $("#btnSalvaMotivo").on("click", function () {
        cod = 0;
        valTipo = $("#tipo").val();
        valMotivo = $("#motivo").val();

        if (validateMotivo()) {


            $.ajax({
                url: "/Estoques/MovimentacaoMotivo/add",
                type: "POST",
                data: JSON.stringify({

                    id: cod,
                    motivo: valMotivo,
                    tipo: valTipo,

                }),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                }
            });
            limparMotivo();

        }
    });


    function validateMotivo() {

        if ($("#motivo").val().trim() == '') {
            document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> O motivo deve ser preenchido ';
            $('#modal_validate').modal({
                show: true
            });
            $(".modal-backdrop").css("backgroud-color", "transparent");
            $('#motivo').css("box-shadow", "0 0 0 .2rem rgba(153, 0, 0, 0.445)");
            return false;
        }
        else {
            $('#motivo').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)");
            return true;
        }

    }

    function validateQuantidade() {

        if ($("#QTD").val() == '') {
            document.getElementsByClassName('Mensagem_modal2')[0].innerHTML = '<strong>ATENÇÃO !</strong> A quantidade deve ser preenchida ';
            $('#modal_validate2').modal({
                show: true
            });
            $(".modal-backdrop").css("backgroud-color", "transparent");
            $('#QTD').css("box-shadow", "0 0 0 .2rem rgba(153, 0, 0, 0.445)");
            return false;
        }
        else {
            $('#QTD').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)");
            return true;
        }

    }

    function validate() {

        if ($("#Observacao").val() == null) {
            document.getElementsByClassName('Mensagem_modal2')[0].innerHTML = '<strong>ATENÇÃO !</strong> O motivo deve ser preenchido ';
            $('#modal_validate2').modal({
                show: true
            });
            $(".modal-backdrop").css("backgroud-color", "transparent");
            $('#Observacao').css("box-shadow", "0 0 0 .2rem rgba(153, 0, 0, 0.445)");
            return false;
        }
        else {
            $('#Observacao').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)");
            return true;
        }

    }

    //Envia dados por metodo Post
    function salvar() {


        if (validate() && validateQuantidade()) {

            dados = table.rows('.selec').data();
            mercadoria_id = dados[0].mercadoria.id;
            movimentacao = $("#Movimentacao").val();
            Obs = $("#Observacao").val();
            estoque = $("#QTD").val();

            try {
                cod = dados[0].id;
            } catch (error) {
                cod = 0;
            }



            $.ajax({
                url: "/Estoques/add",
                type: "POST",
                //data: JSON.stringify({nome:nome.value,email:email.value}),
                data: JSON.stringify({

                    id: cod,
                    tipo_Movimentacao: movimentacao,
                    observacao: Obs,
                    mercadoria: { id: mercadoria_id },
                    qtd_estoque: estoque,
                }),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                }
            });

            DesabilitaBtn();
            setTimeout(function () {
                LimparTabela();
                PreencheTabela();
            }, 140);
            limparDados();
        }

    };

    //Limpar dados do Cadastro
    function limparMotivo() {
        $('#motivo').val('');
        $('#Modelo-Movimentacao').modal('toggle');
    }

    //Limpar dados do Cadastro
    function limparDados() {
        $('#Nome').val('');
        $('#Tel').val('');
        $('#CPF').val('');
        $('#Email').val('');
        $('#Cidade').val('');
        $('#Endereco').val('');
        $('#Estado').val('');
        $('#modelo-cadastro').modal('toggle');
    }

    //Remove Linha da tabela
    $("#button").click(function () {
        table.row(".selec").remove().draw(false);
    });

    //Limpar toda a tabela
    function LimparTabela() {
        table.rows("[role=row]").remove().draw(false);
    }

    $("#Movimentacao").click(function () {
        Movimentacao();
    });

    // Editar cadastro
    $("#btneditar").click(function () {
        dados = table.rows('.selec').data();
        $('#ID').val(dados[0].mercadoria.id);
        $('#Mercadoria').val(dados[0].mercadoria.nome);
        $('#CodBarras').val(dados[0].mercadoria.codBarras);
        $('#Estoque').val(dados[0].qtd_estoque);
        $("#QTD").val('');
        Movimentacao();
    });

    function Movimentacao() {

        Entrada = [];
        Saida = [];

        $("#Observacao").empty();

        if ($("#Movimentacao").val() == "Entrada") {
            $.getJSON("/Estoques/ShowMovimentacao/Entrada/", function (dados) {
                Entrada = dados

                for (let index = 0; index < Entrada.length; index++) {

                    $('#Observacao').append('<option>' + Entrada[index].motivo + '</option>');

                }
            })
        }

        if ($("#Movimentacao").val() == "Saida") {
            $.getJSON("/Estoques/ShowMovimentacao/Saida/", function (dados) {
                Saida = dados
                for (let index = 0; index < Saida.length; index++) {
                    $('#Observacao').append('<option>' + Saida[index].motivo + '</option>');


                }
            })
        }

    }

});
