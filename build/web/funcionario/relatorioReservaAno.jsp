<%-- 
    Document   : relatorioReservaAno
    Created on : 25/08/2020, 18:43:24
    Author     : Gabriel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatorio reservas mensais</title>
    </head>
    <body>
        <div class="container">
            <h1 class="my-4">Reservas do ano de ${ano}</h1>
            <p class="badge-success"><b>${lista.size()}</b> reservas ocorreram em ${ano}</p>
            <p>Exportar em:</p>
            <table id="tabela_export"  class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>Nº quarto</th>
                        <th>Nome do usuario</th>
                        <th>Data de entrada</th>
                        <th>Data de saída</th>
                        <th>Situação</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lista}" var="reserva">
                        <tr>
                            <td>${reserva.id}</td>
                            <td>${reserva.quarto.getNumero()}</td>
                            <td>${reserva.usuario.getNome()}</td>
                            <td>${reserva.dt_entrada}</td>
                            <td>${reserva.dt_saida}</td>
                            <td>${reserva.getSituacao()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
