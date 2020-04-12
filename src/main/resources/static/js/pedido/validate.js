valida_cliente = 0;
valida_vendedor = 0;
valida_mercadoria = 0;


$(document).ready(function () {


    //Retira a exibição do Model Validade ao clicar
    $("#PesquisaCliente").click(function () {
        $('#modal_validate').modal({
            show: false
        });
    });

    $("#PesquisaCliente").hover(function () {
        valida_cliente = 1;
    });

    $("#PesquisaCliente").mouseleave(function () {
        valida_cliente = 0;
    });


    //Retira a exibição do Model Validade ao clicar
    $("#PesquisaVendedor").click(function () {
        $('#modal_validate').modal({
            show: false
        });
    });

    $("#PesquisaVendedor").hover(function () {
        valida_vendedor = 1;
    });

    $("#PesquisaVendedor").mouseleave(function () {
        valida_vendedor = 0;
    });


    //Retira a exibição do Model Validade ao clicar
    $("#PesquisaMercadoria").click(function () {
        $('#modal_validate').modal({
            show: false
        });
    });

    $("#Pesquisamercadoria").hover(function () {
        valida_mercadoria = 1;
    });

    $("#PesquisaMercadoria").mouseleave(function () {
        valida_mercadoria = 0;
    });


    //Valida Cliente
    $('#CodigoCliente').blur(function () {

        cliente = $('#CodigoCliente').val();

        $.getJSON("Clientes/show/" + cliente, function (dados, status) {


            try {

                if (valida_cliente == 0) {

                    if ($('#CodigoCliente').val().trim() != '') {
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
                    }
                    else {
                        $('#NomeCliente').html('');
                    }
                }


            } catch (e) {

            }

        });

    });


    //Valida Vendedor
    $('#CodigoVendedor').blur(function () {

        vendedor = $('#CodigoVendedor').val();

        $.getJSON("Vendedores/show/ativo/" + vendedor, function (dados, status) {


            try {

                if (valida_vendedor == 0) {

                    if ($('#CodigoVendedor').val().trim() != '') {

                        if (dados[0] == null) {
                            document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> Vendedor não encontrado';
                            $('#modal_validate').modal({
                                show: true
                            });
                            $(".modal-backdrop").addClass("fundo");
                            $('#CodigoVendedor').css("box-shadow", "0 0 0 .2rem rgba(153, 0, 0, 0.445)");
                            $('#NomeVendedor').html('');
                            valida_vendedor = 0;
                        }
                        else {
                            $('#NomeVendedor').html(dados[0].nome);
                            $('#CodigoVendedor').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)")
                        }
                    }
                    else {
                        $('#NomeVendedor').html('');
                    }
                }


            } catch (e) {

            }

        });
    });



    //Valida mercadoria
    $('#CodigoMercadoria').blur(function () {

        mercadoria = $('#CodigoMercadoria').val();

        $.getJSON("/Estoques/show/" + mercadoria, function (dados, status) {


            try {

                if (valida_mercadoria == 0) {

                    if ($('#CodigoMercadoria').val().trim() != '') {

                        if (dados[0] == null) {
                            document.getElementsByClassName('Mensagem_modal')[0].innerHTML = '<strong>ATENÇÃO !</strong> Mercadoria não encontrada para venda';
                            $('#modal_validate').modal({
                                show: true
                            });
                            $(".modal-backdrop").addClass("fundo");
                            $('#CodigoMercadoria').css("box-shadow", "0 0 0 .2rem rgba(153, 0, 0, 0.445)");
                            $('#Descricao').html('');
                            $('#adicionar_quantidade').val(0);
                            valida_mercadoria = 0;
                        }
                        else {
                            $('#Descricao').html(dados[0].mercadoria.nome);
                            $('#adicionar_quantidade').val(1);
                            $('#CodigoMercadoria').css("box-shadow", "0 0 0 .2rem rgba(0, 0, 0, 0)")
                        }
                    }
                    else {
                        $('#Descricao').html('');
                        $('#CodigoMercadoria').val('');

                    }
                }


            } catch (e) {

            }

        });
    });



});
