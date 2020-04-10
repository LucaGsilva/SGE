$(document).ready(function () {

    $("#finalizar_venda").prop('disabled', true);
    $("#valor_desconto").prop('disabled', true);
    $("#total_bruto").prop('disabled', true);
    $("#total_liquido").prop('disabled', true);
    $("#total_itens").prop('disabled', true);
    $("#total_unidades").prop('disabled', true);

    var codigo = 0;
    var quantidade = 0;
    var valor_total = 0;
    var tabela = $("#tabela_pedido").DataTable();
    var ta = $("#tabela_cliente").DataTable();

    var mercadoria_id = 0;
    var descricao = '';
    var preco = 0;


    // Adiciona Linha
    $("#btnadd").on("click", function () {

        try {

            if (ValidateQuantidade()) {

                ValidateMercadoria()

            }
            else {

            }

        } catch (e) {

        }

    });

    //Remove Linha da tabela
    $("#btndel").click(function () {
        tabela
            .row(".selec")
            .remove()
            .draw(false);
        AjustaValores()
    });


    function ValidateQuantidade() {

        if ($('#adicionar_quantidade').val() < 1) {
            document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> Quantidade digitada não permitida';
            $('#modal_validate').modal({
                show: true
            });
            $(".modal-backdrop").addClass("fundo");
            $('#adicionar_quantidade').css("box-shadow", "0 0 0 .2rem rgba(153, 0, 0, 0.445)");
            $('#adicionar_quantidade').focus();
            return false;
        }
        else {
            $('#adicionar_quantidade').val(Math.floor($('#adicionar_quantidade').val()));
            $('#adicionar_quantidade').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)")
            return true;
        }

    };

    function ValidateMercadoria() {


        if ($('#CodigoMercadoria').val().trim() != '') {

            codigo = $('#CodigoMercadoria').val();
            $.getJSON("/Estoques/show/" + codigo, function (dados, status) {

                if (dados[0] != null) {
                    mercadoria_id = dados[0].mercadoria.id;
                    descricao = dados[0].mercadoria.nome;
                    preco = valor_total = dados[0].mercadoria.preco;
                    quantidade = $('#adicionar_quantidade').val();
                    valor_total = parseFloat((valor_total * quantidade).toFixed(2));


                    //Adiciona Mercadoria a Tabela de Compra
                    tabela.row.add([
                        mercadoria_id,
                        descricao,
                        quantidade,
                        preco,
                        valor_total,
                    ]).draw();

                    $('#Descricao').html('');
                    $('#CodigoMercadoria').val('');
                    $('#adicionar_quantidade').val(0);

                    AjustaValores();

                }
                else {
                    document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> A mercadoria informada deve ser corrigida ';
                    $('#modal_validate').modal({
                        show: true
                    });
                    $(".modal-backdrop").addClass("fundo");
                    $('#CodigoMercadoria').css("box-shadow", "0 0 0 .2rem rgba(153, 0, 0, 0.445)");

                }

            })

        }
        else {

            document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> Nenhuma mercadoria selecionada';
            $('#modal_validate').modal({
                show: true
            });
            $(".modal-backdrop").addClass("fundo");
            $('#CodigoMercadoria').val('');
            $('#CodigoMercadoria').css("box-shadow", "0 0 0 .2rem rgba(153, 0, 0, 0.445)");

        }
    }

    function TotalizaItens() {
        return $('#total_itens').val(tabela.rows('[role=row]').count());
    }

    function TotalUnidade() {

        linhas = TotalizaItens().val();
        total = 0;

        for (let index = 0; index < linhas; index++) {

            valor = tabela.row(index).data()[2];
            total = total + parseInt(valor);

        }

        $('#total_unidades').val(total);

    }

    function TotalBruto() {
        linhas = TotalizaItens().val();
        total = 0;

        for (let index = 0; index < linhas; index++) {


            quantidade = tabela.row(index).data()[2];
            valor = tabela.row(index).data()[3];
            total = total + (parseInt(quantidade) * parseFloat(valor))

        }

        total = parseFloat(total).toFixed(2);
        $('#total_bruto').val(total);
        $('#total_bruto').unmask();
        $('#total_bruto').mask('000.000.000.000.000,00', { reverse: true });

        return total;
    }


    function Desconto() {

        desconto = 0;

        if ($('#percent_desconto').val() != 0) {

            desconto = (($('#percent_desconto').val() * TotalBruto()) / 100)

            desconto = parseFloat(desconto).toFixed(2);


            if (desconto == TotalBruto()) {
                desconto = TotalBruto() - 0.01;
            }


            $("#valor_desconto").val(desconto);
            $('#valor_desconto').unmask();
            $('#valor_desconto').mask('000.000.000.000.000,00', { reverse: true });
        }
        return desconto;

    }

    function TotalLiquido() {
        total_liquido = 0;

        if (Desconto() != 0) {
            total_liquido = TotalBruto() - Desconto();
            total_liquido = parseFloat(total_liquido).toFixed(2);
            $('#total_liquido').val(total_liquido);
            $('#total_liquido').unmask();
            $('#total_liquido').mask('000.000.000.000.000,00', { reverse: true });
            return total_liquido;
        }
        else {
            total_liquido = TotalBruto();
            total_liquido = parseFloat(total_liquido).toFixed(2);
            $('#total_liquido').val(total_liquido);
            $('#total_liquido').unmask();
            $('#total_liquido').mask('000.000.000.000.000,00', { reverse: true });

            return total_liquido;

        }

    }

    //Valida Desconto
    $('#percent_desconto').blur(function () {
        ValidateDesconto();
    });

    function ValidateDesconto() {

        if ($('#percent_desconto').val() > 99.99) {
            document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> O desconto maximo permitido é de 99.99%';
            $('#modal_validate').modal({
                show: true
            });
            $(".modal-backdrop").addClass("fundo");
            $('#percent_desconto').val(0).focus();
        }

        if ($('#percent_desconto').val() < 0) {
            document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> Não é permitido desconto negativo';
            $('#modal_validate').modal({
                show: true
            });
            $(".modal-backdrop").addClass("fundo");
            $('#percent_desconto').val(0).focus();
        }

        if ($('#percent_desconto').val().trim() == '') {
            $('#percent_desconto').val(0);
        }

        if ($('#total_bruto').val() != 0) {
            AjustaValores();

        }
    }


    $("#finalizar_venda").click(function () {

        if (TotalizaItens().val() > 0) {

            if (ValidateCliente() && ValidateVendedor()) {

            }
        }
        else {
            document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> Não é possivel fechar pedido sem itens';
            $('#modal_validate').modal({
                show: true
            });
            $(".modal-backdrop").addClass("fundo");
        }

    });


    //As funções não podem mudar de Ordem.
    function AjustaValores() {
        TotalizaItens();
        TotalUnidade();
        TotalBruto();
        Desconto();
        TotalLiquido();


        if (TotalizaItens().val() > 0) {
            $("#finalizar_venda").prop('disabled', false);
        }
        else {
            $("#finalizar_venda").prop('disabled', true);
        }

    }


    function ValidateCliente() {

        if ($('#CodigoCliente').val().trim() != '') {
            cliente = $('#CodigoCliente').val();
        }
        else {
            cliente = 0;
        }

        $.getJSON("Clientes/show/" + cliente, function (dados, status) {


            try {

                if (dados[0] == null) {
                    document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> Cliente não encontrado';
                    $('#modal_validate').modal({
                        show: true
                    });
                    $(".modal-backdrop").addClass("fundo");
                    $('#CodigoCliente').css("box-shadow", "0 0 0 .2rem rgba(153, 0, 0, 0.445)");
                    $('#NomeCliente').html('');
                    valida_cliente = 0;
                }
                else {
                    $('#NomeCliente').html(dados[0].nome);
                    $('#CodigoCliente').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)")
                }


            } catch (e) {

            }

        });

        if ($("#NomeCliente").html() != '') {
            return true;
        }
        else {
            return false;
        }

    }

    function ValidateVendedor() {

        if ($('#CodigoVendedor').val() != '') {
            vendedor = $('#CodigoVendedor').val();
        }
        else {
            vendedor = 0;
        }

        $.getJSON("Vendedores/show/ativo/" + vendedor, function (dados, status) {


            try {

                if (dados[0] == null) {
                    document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> Vendedor não encontrado';
                    $('#modal_validate').modal({
                        show: true
                    });
                    $(".modal-backdrop").addClass("fundo");
                    $('#CodigoVendedor').css("box-shadow", "0 0 0 .2rem rgba(153, 0, 0, 0.445)");
                    $('#NomeVendedor').html('');

                }
                else {
                    $('#NomeVendedor').html(dados[0].nome);
                    $('#CodigoVendedor').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)")
                }

            } catch (e) {

            }

        });

    }


});