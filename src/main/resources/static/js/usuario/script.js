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

        ajax: {
            url: "/Users/show",
            type: "GET",
            dataSrc: ''
        },
        columns: [
            { data: "usuario.id" },
            {
                data: "usuario.nome"
            }, {
                data: "usuario.login"
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
            url: "/Users/show",
            type: "GET",
            dataSrc: '',

            columns: [
                { data: "usuario.id" },
                {
                    data: "usuario.nome"
                }, {
                    data: "usuario.login"
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

        dados = table.rows('.selec').data();
        var nome = $("#Nome").val();
        var login = $("#Login").val();
        var password = $("#Password").val();
        var cliente = $("#Cliente").val();
        var mercadoria = $("#Mercadoria").val();
        var estoque = $("#Estoque").val();
        var pedido_novo = $("#Pedido_Novo").val();
        var pedido_cancela = $("#Pedido_Cancela").val();
        var pedido_troca = $("#Pedido_Troca").val();
        var vendedor = $("#Vendedor").val();
        var usuario = $("#Usuario").val();
        var titulo_aberto = $("#Titulo_Aberto").val();
        var titulo_liquidado = $("#Titulo_Fechado").val();
        var dashboard = $("#Dashboard").val();

        if (Validate()) {


            try {
                id_usuario = dados[0].usuario.id;
            } catch (error) {
                id_usuario = '';
            }

            try {
                cod = dados[0].id;
            } catch (error) {
                cod = '';
            }



            $.ajax({
                url: "/Users/add",
                type: "POST",
                //data: JSON.stringify({nome:nome.value,email:email.value}),
                data: JSON.stringify({

                    id: cod,
                    usuario: { id: id_usuario, login: login, password: password, nome: nome },
                    dashboard: dashboard,
                    cliente: cliente,
                    mercadoria: mercadoria,
                    estoque: estoque,
                    pedido_novo: pedido_novo,
                    pedido_cancela: pedido_cancela,
                    pedido_troca: pedido_troca,
                    vendedor: vendedor,
                    usuario_acesso: usuario,
                    titulo_aberto: titulo_aberto,
                    titulo_liquidado: titulo_liquidado
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

            limparDados();
        }
    };

    //Limpar dados do Cadastro
    function limparDados() {
        $('#Nome').val('');
        $('#Login').val('');
        $('#Password').val('');
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

    // Editar cadastro
    $("#btneditar").click(function () {
        dados = table.rows('.selec').data();
        $('#Nome').val(dados[0].usuario.nome);
        $('#Login').val(dados[0].usuario.login);
        $('#Password').val('******************');
        $('#Dashboard').val(dados[0].dashboard);
        $('#Mercadoria').val(dados[0].mercadoria);
        $('#Cliente').val(dados[0].cliente);
        $('#Estoque').val(dados[0].estoque);
        $('#Pedido_Novo').val(dados[0].pedido_novo);
        $('#Pedido_Cancela').val(dados[0].pedido_cancela);
        $('#Pedido_Troca').val(dados[0].pedido_troca);
        $('#Vendedor').val(dados[0].vendedor);
        $('#Usuario').val(dados[0].usuario_acesso);
        $('#Titulo_Aberto').val(dados[0].titulo_aberto);
        $('#Titulo_Fechado').val(dados[0].titulo_liquidado);
    });

    function ObtemUsuario(login) {
        $.getJSON("/Usuarios/show/login/" + login, function (dados, status) {

            try {
                return dados.id;
            } catch (e) {
            }

        });
    }

    $("#Nome").blur(function (e) {

        if ($("#Nome").val().trim() != '') {
            $('#Nome').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)");
        }

    });

    $("#Login").blur(function (e) {
        if ($("#Login").val().trim() != '') {
            $('#Login').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)");
        }

    });

    $("#Password").blur(function (e) {
        if ($("#Password").val().trim() != '') {
            $('#Password').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)");
        }

    });



    function ValidateNome() {
        if ($("#Nome").val().trim() == '') {

            document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> O nome deve ser preenchido';
            $('#modal_validate').modal({
                show: true
            })
            $(".modal-backdrop").css("backgroud-color", "transparent");
            $('#Nome').css("box-shadow", "0 0 0 .2rem rgba(153, 0, 0, 0.445)");

            return false;
        }
        else {
            $('#Nome').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)");
            return true;
        }
    }

    function ValidateLogin() {
        if ($("#Login").val().trim() == '') {

            document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> O login deve ser preenchido';
            $('#modal_validate').modal({
                show: true
            })
            $(".modal-backdrop").css("backgroud-color", "transparent");
            $('#Login').css("box-shadow", "0 0 0 .2rem rgba(153, 0, 0, 0.445)");

            return false;
        }
        else {
            $('#Login').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)");
            return true;
        }
    }

    function ValidatePassword() {

        if ($("#Password").val().trim() == '') {

            document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> O password deve ser preenchido';
            $('#modal_validate').modal({
                show: true
            })
            $(".modal-backdrop").css("backgroud-color", "transparent");
            $('#Password').css("box-shadow", "0 0 0 .2rem rgba(153, 0, 0, 0.445)");

            return false;
        }
        else {
            $('#Password').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)");
            return true;
        }

    }

    function Validate() {

        if (ValidateNome() && ValidateLogin() && ValidatePassword()) {
            return true;
        }
        else {
            return false;
        }
    }
});
