<%-- 
    Document   : gerirPedidos
    Created on : 03/10/2020, 17:08:09
    Author     : Gabriel
--%>
<%@include file="../header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciar pedidos por reserva</title>
    </head>
    <body>
        <div class="container">
            <h1>Gerenciar pedidos por reserva</h1>
            <br>
            <form class="form-group" action="<%= request.getContextPath()%>/relatorioItensMaisPedidos" method="post">
                <div class="card">
                    <div class="card-body">
                        <b><label class="card-title">Relatório pedidos</label></b>
                        <br>
                        <input type="submit" class="btn btn-primary" value="Gerar relatório de pedidos">
                    </div>
                </div>
            </form>
            <hr>
            <div class="row" style="margin-left: 0px;">
                <c:forEach items="${reservas}" var="reservas">
                    <div class="card mb-4" style="width:250px; margin-right: 3%;">
                        <div class="card-body">
                            <h4 class="card-title">Quarto Nº</h4>
                            <p class="card-text">${reservas.quarto.numero}</p>
                            <h4 class="card-title">Sobre</h4>
                            <p class="card-text">${reservas.quarto.tipo}</p>
                            <div class="card">
                                <div class="card-body">
                                    Entrada <p class="card-text"><fmt:formatDate type = "date" dateStyle = "short" value = "${reservas.dt_entrada}" /></p>
                                    Saida <p class="card-text"><fmt:formatDate type = "date" dateStyle = "short" value = "${reservas.dt_saida}" /></p>
                                </div>
                            </div>
                            <hr>
                            <form action="<%= request.getContextPath()%>/gerenciarPedidos" method="get">
                                <input type="hidden" value="${reservas.id}" name="id_reserva">
                                <input class="btn btn-success form-control" type="submit" value="Verificar pedidos"/>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
