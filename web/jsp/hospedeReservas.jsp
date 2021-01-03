<%-- 
    Document   : hospedeReservas
    Created on : 12/05/2020, 19:19:58
    Author     : Gabriel
--%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@include file="../header.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../bootstrap-4.3.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Quartos Reservados</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${listaReservaId.isEmpty()}">
                <h1 class="text-center badge-danger">Não há quartos reservados nesta conta.</h1>
            </c:when>
            <c:otherwise>
                <h1 class="text-center">Quartos Reservados</h1>
                <table>
                    <div class="container">
                        <div class="row">
                            <c:forEach items="${listaReservaId}" var="listaReservaId">
                                <c:set var = "entrada" value = "${listaReservaId.dt_entrada}" />
                                <c:set var = "saida" value = "${listaReservaId.dt_saida}" />
                                <div class="card" style="width:300px; margin-right: 3%;">
                                    <img class="card-img-top" src="./assets/images/quartod1.jpg" alt="Card image" style="width:100%">
                                    <div class="card-body">
                                        <h4 class="card-title">Sobre</h4>
                                        <p class="card-text">Número do quarto: ${listaReservaId.quarto.numero}</p>
                                        <p class="card-text">Valor da diária: ${listaReservaId.quarto.valorDiaria}</p>
                                        <p class="card-text">Tipo: ${listaReservaId.quarto.tipo}</p>
                                        <p class="card-text">Data de entrada: <fmt:formatDate type = "date" value = "${entrada}"/></p>
                                        <p class="card-text">Data de saída: <fmt:formatDate type = "date" value = "${saida}"/></p>
                                        <p class="card-text">Orçamento: ${listaReservaId.orcamento}</p>
                                        <p class="card-text">Situação: ${listaReservaId.situacao}</p>
                                        <p class="card-text">Tipo de pagamento: ${listaReservaId.tipoPagamento}</p>
                                        <c:if test="${listaReservaId.quarto.desconto > 0}">
                                            <span class="badge badge-warning">Desconto de ${listaReservaId.quarto.desconto}%</span>
                                            <br>
                                        </c:if>

                                        <c:set var = "hoje" value = "<%= new java.util.Date()%>" />                        
                                        <fmt:formatDate value="${hoje}" var="hoje" pattern="yyyy-MM-dd"/>
                                        <c:choose>
                                            <c:when test="${entrada == hoje && listaReservaId.situacao == 'Reservada'}">
                                                <form action="<%=request.getContextPath()%>/checkin" method="post">
                                                    <input name="idReserva"type="hidden" value="${listaReservaId.id}">
                                                    <p class="card-text"><input class="btn btn-success" type="submit" value="Checkin"></p>                                
                                                </form>
                                            </c:when>
                                            <c:otherwise>

                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${hoje >= saida}">
                                                <%
                                                    Date dt = new Date();
                                                    Calendar calendar = GregorianCalendar.getInstance();
                                                    calendar.setTime(dt);
                                                    int horas = calendar.get(Calendar.HOUR_OF_DAY);
                                                    if (horas >= 12) {
                                                %>                            
                                                <form action="<%=request.getContextPath()%>/checkoutAntecipadoTela" method="post">
                                                            <input name="dt_entrada"type="hidden" value="${entrada}">
                                                            <input name="dt_saida"type="hidden" value="${saida}">
                                                            <input name="idReserva"type="hidden" value="${listaReservaId.id}">
                                                            <input name="diaria"type="hidden" value="${listaReservaId.quarto.valorDiaria}">
                                                            <input name="desconto"type="hidden" value="${listaReservaId.quarto.desconto}">
                                                            <input name="tipo_pagamento"type="hidden" value="${listaReservaId.tipoPagamento}">
                                                            <input name="situacao"type="hidden" value="Finalizada">
                                                            <p class="card-text"><input class="btn btn-danger" type="submit" value="Checkout"></p>
                                                        </form>
                                                <%
                                                    }
                                                %>
                                                <br>
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${listaReservaId.situacao == 'Ocorrendo'}">
                                                        <form action="<%=request.getContextPath()%>/checkoutAntecipadoTela" method="post">
                                                            <input name="dt_entrada"type="hidden" value="${entrada}">
                                                            <input name="dt_saida"type="hidden" value="${saida}">
                                                            <input name="idReserva"type="hidden" value="${listaReservaId.id}">
                                                            <input name="diaria"type="hidden" value="${listaReservaId.quarto.valorDiaria}">
                                                            <input name="desconto"type="hidden" value="${listaReservaId.quarto.desconto}">
                                                            <input name="tipo_pagamento"type="hidden" value="${listaReservaId.tipoPagamento}">
                                                            <input name="situacao"type="hidden" value="Checkout antecipado">
                                                            <p class="card-text"><input class="btn btn-danger" type="submit" value="Checkout antecipado"></p>
                                                        </form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <br>
                                                        <p class="card-text"><a class="btn btn-danger" href="cancelarReserva?id=${listaReservaId.quarto.id}&id_reserva=${listaReservaId.id}">Cancelar1 Reserva</a></p>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>
<script src="../bootstrap-4.3.1-dist/js/bootstrap.min.js" type="text/javascript"></script>