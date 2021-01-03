<%-- 
    Document   : checkoutAntecipado
    Created on : 10/10/2020, 22:08:23
    Author     : Gabriel
--%>
<%@include file="../header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="../bootstrap-4.3.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../assets/css/gerenciamento.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciador de usuarios</title>
    </head>
    <body>
        <div class="p-4 mt-5">
            <div class="row form_gerir">
                <div class="col-md-7 pt-5 shadow-lg">
                    <h1 class="p-3">Checkout</h1>
                    <form class="form-group" action="<%= request.getContextPath()%>/checkoutAntecipado" method="post">
                        <div class="cadastro_esquerda">
                            <input type="hidden" value="${quarto.id}" name="idQuarto">
                            <input type="hidden" value="${usuario.id}" name="idUsuario">
                            <label>Nome Titular</label>
                            <input class="form-control" type="tel" placeholder="João Exemplo" name="nome">
                        </div>   
                        <div class="cadastro_direita">
                            <label>Número do cartão</label>
                            <input class="form-control nasc" type="tel" placeholder="0000111122223333" name="numero_cartao">
                            <label>Data de Vencimento</label>
                            <input class="form-control nasc" type="text" placeholder="00/0000" name="dtVenc">
                            <label>Código de Segurança</label>
                            <input class="form-control nasc" type="tel" placeholder="000" name="codSeg">
                            <label>Tipo de pagamento: ${tipo_pagamento}</label><br>
                            <input name="tipo_pagamento" type="hidden" value="${tipo_pagamento}">
                            <label>Data de Entrada: ${dt_entrada}</label><br>
                            <input name="dt_entrada" type="hidden" value="${dt_entrada}">
                            <label>Data de Saida: ${dt_saida}</label><br>
                            <input name="dt_saida" type="hidden" value="${dt_saida}">
                            <label>Valor da diária: ${diaria}</label><br>

                            <!--CALCULA ORÇAMENTO-->
                            <c:set var="orcamento" value="${dias * diaria}"/>
                            <c:set var="orcamentoDesconto" value="${orcamento - (orcamento * (desconto/100))}"/>                
                            <c:choose>
                                <c:when test="${desconto > 0}">
                                    <label>Total (com desconto de ${desconto}%): R$${orcamentoDesconto}</label><br>
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
                            <div class="container">
                                <div id="accordion">
                                    <div class="card">
                                        <div class="card-header">
                                            <a class="card-link" data-toggle="collapse" href="#collapseOne">
                                                Pedidos e total a pagar
                                            </a>
                                        </div>
                                        <div id="collapseOne" class="collapse" data-parent="#accordion">
                                            <div class="card-body">
                                                <c:set var="total_estadia" value="${0}"/>
                                                <c:set var="inteiro" value="${0}"/>
                                                <c:forEach items="${pedidos}" var="pedidos">                                                
                                                    <b class="card-text">${pedidos.item.nome} x${pedidos.quantidade}----------------------------------------${pedidos.item.preco * pedidos.quantidade}</b><br>
                                                    <c:set var="total_estadia" value="${total_estadia + (pedidos.item.preco * pedidos.quantidade)}"/>
                                                </c:forEach>
                                                <hr>
                                                <c:choose>
                                                    <c:when test="${tipo_pagamento.toString().equals('Inteiro')}">
                                                        <b class="card-text">Total a pagar (inteiro): R$${total_estadia}</b>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <b class="card-text">Total a pagar (meio a meio): R$${total_estadia + (orcamentoDesconto/2)}</b><br>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <input type="hidden" name="idReserva" value="${idReserva}">
                        <div style="text-align: center;">
                            <input name="situacao" type="hidden" value="${situacao}">
                            <input class="btn btn-success teste form-control" type="submit" value="Confirmar checkout">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
<script src="../bootstrap-4.3.1-dist/js/bootstrap.min.js" type="text/javascript"></script>
