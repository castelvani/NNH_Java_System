<%-- 
    Document   : consultarQuarto
    Created on : 10/11/2019, 18:48:04
    Author     : Gabriel
--%>
<%@include file="../header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar quartos</title>
    </head>
    <body>
        <div class="container">
            <h1>Quartos</h1>            
            <table id="tabela_consulta" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>Número</th>
                        <th>Tipo</th>
                        <th>Valor diaria</th>
                        <th>Status</th>
                        <th>Alteração</th>
                        <th>Exclusão</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lista}" var="quarto">
                        <tr>
                            <td>${quarto.id}</td>
                            <td>${quarto.numero}</td>
                            <td>${quarto.valorDiaria}</td>
                            <td>${quarto.tipo}</td>
                            <td>${quarto.status}</td>
                    <form action="<%=request.getContextPath()%>/alterarQuarto" method="post">
                        <input name="quarto_id" type="hidden" value="${quarto.id}">
                        <td>
                            <button class="btn btn-warning"type="submit">Alterar</button>
                    </form>
                    <td><a class="btn btn-danger" href="excluirQuarto?id=${quarto.getId()}">Excluir</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
