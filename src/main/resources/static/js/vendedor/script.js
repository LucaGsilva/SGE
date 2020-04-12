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

            if (dados.pedido_cancela == 'N') {
                document.getElementById('Menu_Pedido_Cancela').style.display = "none";
            }
            if (dados.pedido_troca == 'N') {
                document.getElementById('Menu_Pedido_Troca').style.display = "none";
            }

            if (dados.pedido_novo == 'N' && dados.pedido_cancela == 'N' && dados.pedido_troca == 'N') {
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
    $('#tabela').dataTable({
        keys: true,
        keys: {
            editorKeys: 'tab-only'
        },
        ajax: {
            url: "/Vendedores/show",
            type: "GET",
            dataSrc: ''
        },
        columns: [{
            data: "id"
        }, {
            data: "nome"
        }, {
            data: "ativo"
        }]
    });

});

function HabilitaBtn() {
    document.getElementById('btneditar').style.display = "inline-block";
};

function DesabilitaBtn() {
    document.getElementById('btneditar').style.display = "none";
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
    $("#btnnovo").on("click", function () {
        table.$("tr.selec").removeClass("selec");
        limparDados();
    });

    //Envia dados por metodo Post
    function salvar() {

        if (Validate()) {
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

                });
                DesabilitaBtn();
                setTimeout(function () {
                    LimparTabela();
                    PreencheTabela();
                }, 140);
                limparDados();
            }
        }

    };

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

    // Editar cadastro
    $("#btneditar").click(function () {
        dados = table.rows('.selec').data();
        $('#nome').val(dados[0].nome);
        $('#ativo').val(dados[0].ativo);
    });

    $("#nome").blur(function (e) {
        if ($("#nome").val().trim() != '') {
            $('#nome').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)");
        }

    });

    function ValidateVendedor() {
        if ($("#nome").val().trim() == '') {

            document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> O nome deve ser preenchido';
            $('#modal_validate').modal({
                show: true
            })
            $(".modal-backdrop").css("backgroud-color", "transparent");
            $('#nome').css("box-shadow", "0 0 0 .2rem rgba(153, 0, 0, 0.445)");
            return false;
        }
        else {
            $('#nome').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)");
            return true;
        }
    }

    function Validate() {
        if (ValidateVendedor()) {
            return true;
        }
    }
});