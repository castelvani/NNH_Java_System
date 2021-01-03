<%-- 
    Document   : relatorioItensMaisPedidos
    Created on : 21/10/2020, 20:31:00
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
            <h1 class="my-4">Lista de itens mais pedidos</h1>
            <p>Exportar em:</p>
            <table id="tabela_export"  class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>Nome</th>
                        <th>Tipo</th>
                        <th>Preco</th>
                        <th>Descrição</th>
                        <th>Vezes pedido</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${itens}" var="itens">
                        <tr>
                            <td>${itens.id}</td>
                            <td>${itens.nome}</td>
                            <td>${itens.tipo}</td>
                            <td>${itens.preco}</td>
                            <td>${itens.descricao}</td>
                            <td>${itens.vezesPedido}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>