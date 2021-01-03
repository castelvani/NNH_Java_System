<%-- 
    Document   : consultarQuarto
    Created on : 10/11/2019, 18:48:04
    Author     : Gabriel
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar quartos</title>
    </head>
    <body>
        <h1>Quartos</h1>
        <table>
            <c:forEach items="${lista}" var="quarto">
                <tr>
                    <td>${quarto.id}</td>
                    <td>${quarto.numero}</td>
                    <td>${quarto.valorDiaria}</td>
                    <td>${quarto.tipo}</td>
                    <td>${quarto.status}</td>
                    <td><a href="alterarQuarto?id=${quarto.getId()}">Alterar</a>
                        <a href="excluirQuarto?id=${quarto.getId()}">Excluir</a></td>
                    <td>
                </tr>
            </c:forEach>
        </table>
        <a href="./funcionario/gerirQuarto.jsp"><input type="button" value="Voltar"/></a>
    </body>
</html>
