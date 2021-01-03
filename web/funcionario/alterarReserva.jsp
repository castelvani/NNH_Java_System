<%-- 
    Document   : alterarReserva
    Created on : 01/04/2020, 19:21:30
    Author     : Gabriel
--%>
<%@include file="../header.jsp"%>
<%@page import="Model.Reserva"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/bootstrap-4.3.1-dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/bootstrap-4.3.1-dist/css/login.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Reserva</title>
    </head>
    <body>
        <div class="pt-5 mt-5">
            <div class="row form_gerir">
                <div class="col-md-7 pt-5 shadow-lg">
                    <div class="card">
                        <h1 class="card-title ml-3">Reserva corrente:</h1>
                        <div  class="card-text ml-3" >
                            <label>Situacao: ${reserva.situacao}</label><br>
                            <label>Nome do usuário: ${reserva.usuario.nome}</label><br>
                            <label>Número do quarto: ${reserva.quarto.numero}</label><br>
                            <label>Tipo do quarto: ${reserva.quarto.tipo}</label>
                        </div>
                    </div>
                    <h1 class="mt-2">Alterar Reserva</h1>
                    <form class="form-group" method="post" action="<%=request.getContextPath()%>/alterarReservaInsert">
                        <input type="hidden" value="${id}" name="id">
                        <input type="hidden" value="${reserva.usuario.id}" name="idUsuario">
                        <input type="hidden" value="${reserva.quarto.id}" name="idQuarto">
                        <label>Situacao:</label>
                        <select name="reservaSituacao" class="form-control">
                            <option>Finalizada</option>
                            <option>Checkout antecipado</option>
                            <option>Cancelada</option>
                            <option>Ocorrendo</option>
                            <option>Realizada</option>
                        </select>

                        <br>
                        <label>Nome do usuário:</label>
                        <select name="hospedeNome" class="form-control">
                            <c:forEach items="${hospedes}" var="hospedes">
                                <option>${hospedes.nome}</option>
                            </c:forEach>
                        </select><br>

                        <label>Número do quarto:</label>
                        <select name="quartoNumero" class="form-control">
                            <c:forEach items="${quartos}" var="quartos">
                                <option>${quartos.numero}</option>
                            </c:forEach>
                        </select><br>                
                        <button class="form-control btn btn-success" type="submit">Alterar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
