<%-- 
    Document   : consultarQuarto
    Created on : 10/11/2019, 18:48:04
    Author     : Gabriel
--%>
<%@page import="Model.Usuario"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="Model.Quarto"%>
<%@include file="../header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/daterangepicker-master/daterangepicker.css" rel="stylesheet" type="text/css"/>
        <link href="../assets/bootstrap-4.3.1-dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/index.css" rel="stylesheet" type="text/css"/>
        <link href="../assets/css/cadastro.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
        <!--<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>-->
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
        <script src="../assets/daterangepicker-master/calendario_nosso.js" type="text/javascript"></script>
        <title>Reserva</title>
    </head>
    <body>
        <div class="pt-5 mt-5">
            <div class="row form_gerir">
                <div class="col-md-7 pt-5 shadow-lg">
                    <h1 class="pb-3">Finalizar reserva</h1>
                    <form action="/NNH2/inserirReserva" method="post">
                        <div class="cadastro_esquerda">
                            <c:set var="tipo_pagamento" value="${1}"/>
                            <label class="mr-1">Modo de pagamento</label><a href="#" data-toggle="tooltip" title="Meio a meio paga 50% na reserva e 50% no checkout Inteiro paga a estadia inteira na reserva e caso o quarto não esteja em promoção o hospede ganha 10% de desconto"><i class="fa fa-question-circle" aria-hidden="true"></i></a>
                            <select class="form-control" name="tipo">
                                <option value="Meio a meio">Meio a meio</option>
                                <option value="Inteiro">Inteiro</option>
                            </select>
                            <input type="hidden" value="${quarto.id}" name="idQuarto">
                            <input type="hidden" value="${usuario.id}" name="idUsuario">
                            <label>Nome Titular</label>
                            <input class="form-control" type="tel" placeholder="João Exemplo" name="nome">
                            <label>CPF</label>
                            <input class="form-control" type="tel" placeholder="000.000.000-00" name="cpf">
                        </div>   
                        <div class="cadastro_direita">
                            <label>Número do cartão</label>
                            <input class="form-control nasc" type="tel" placeholder="0000111122223333" name="numero_cartao">
                            <label>Data de Vencimento</label>
                            <input class="form-control nasc" type="text" placeholder="00/0000" name="dtVenc">
                            <label>Código de Segurança</label>
                            <input class="form-control nasc" type="tel" placeholder="000" name="codSeg">
                            <label>Data de Entrada: ${dt_entrada}</label><br>
                            <input name="dt_entrada" type="hidden" value="${dt_entrada}">
                            <label>Data de Saida: ${dt_saida}</label><br>
                            <input name="dt_saida" type="hidden" value="${dt_saida}">
                            <label>Valor da diária: ${quarto.valorDiaria}</label><br>

                            <!--CALCULA ORÇAMENTO-->
                            <c:set var="orcamento" value="${dias * quarto.valorDiaria}"/>
                            <c:set var="orcamentoDesconto" value="${orcamento - (orcamento * (quarto.desconto/100))}"/>
                            <c:choose>
                                <c:when test="${quarto.desconto > 0}">
                                    <label>Total (com desconto de ${quarto.desconto}%): R$${orcamentoDesconto}</label><br>
                                    <input name="orcamentoDesconto" type="hidden" value="${orcamentoDesconto}">
                                </c:when>
                                <c:otherwise>
                                    <label>Total inteiro(com desconto de 10% devido ao pagamento inteiro): R$${orcamento - (orcamento/10)}</label><br>
                                    <input name="total_inteiro" type="hidden" value="${orcamento - (orcamento/10)}">
                                </c:otherwise>
                            </c:choose>
                            <label>Total meio a meio(sem desconto): R$${orcamento/2} na reserva e R$${orcamento/2} no checkout (mais pedidos)</label>
                            <input name="orcamentoDesconto" type="hidden" value="${orcamento}">
                            <label>Total (sem desconto): R$${orcamento}</label>
                            <input name="orcamentoDesconto" type="hidden" value="${orcamento}">
                            <input name="desconto" type="hidden" value="${quarto.desconto}">
                        </div>
                        <div style="text-align: center;">
                            <input class="btn btn-success form-control teste" type="submit" value="Reservar">
                        </div>                        
                        <%
//                            Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
//                            String perfil = usuarioLogado.getEmail();
//
//                            PrintWriter pw = response.getWriter();
//                            pw.print(perfil);
%>
                    </form>
                </div>
            </div>
            <br>
        </div>
    </body>
</html>
<script>
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
<script src="https://use.fontawesome.com/1dc51360ad.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
