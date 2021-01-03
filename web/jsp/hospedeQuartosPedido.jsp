<%-- 
    Document   : hospedeQuartosPedido
    Created on : 24/09/2020, 21:05:09
    Author     : Gabriel
--%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@include file="../header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h1>Selecione o quarto que deseja consultar/realizar os pedidos.</h1>
            <br>
            <%
                Date dt = new Date();
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTime(dt);
                int horas = calendar.get(Calendar.HOUR_OF_DAY);
                if (horas <= 00 || horas >= 6) {
            %>
            <div class="row">
                <c:forEach items="${listaReservaIdPedido}" var="listaReservaIdPedido">
                    <div class="card mb-4" style="width:250px; margin-right: 3%;">
                        <div class="card-body">
                            <h4 class="card-title">Quarto Nº</h4>
                            <p class="card-text">${listaReservaIdPedido.quarto.numero}</p>
                            <h4 class="card-title">Sobre</h4>
                            <p class="card-text">${listaReservaIdPedido.quarto.tipo}</p>
                            <div class="card">
                                <div class="card-body">
                                    Entrada <p class="card-text"><fmt:formatDate type = "date" dateStyle = "short" value = "${listaReservaIdPedido.dt_entrada}" /></p>
                                    Saida <p class="card-text"><fmt:formatDate type = "date" dateStyle = "short" value = "${listaReservaIdPedido.dt_saida}" /></p>
                                </div>
                            </div>
                            <hr>
                            <form action="<%= request.getContextPath()%>/consultarPedidos" method="post">
                                <input type="hidden" value="${id}" name="id_usuario">
                                <input type="hidden" value="${listaReservaIdPedido.id}" name="id_reserva">
                                <input type="hidden" value="${listaReservaIdPedido.quarto.id}" name="id_quarto">
                                <input class="btn btn-success form-control" type="submit" value="Consultar/Realizar pedidos"/>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <%
            } else {
            %>
            <h1 class="text-center badge-danger">Pedidos disponiveis apenas a partir das 06h00</h1>
            <%
                }
            %>
        </div>       
    </body>
</html>

