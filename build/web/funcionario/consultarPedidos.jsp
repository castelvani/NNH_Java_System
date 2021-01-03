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
        <title>Consultar itens</title>
    </head>
    <body>
        <div class="container">
            <h1>Itens</h1>            
            <table id="tabela_consulta" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>Nome</th>
                        <th>Tipo</th>
                        <th>Preço</th>
                        <th>Descrição</th>
                        <th>Alteração</th>
                        <th>Exclusão</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${itens}" var="item">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.nome}</td>
                            <td>${item.tipo}</td>
                            <td>R$${item.preco}</td>
                            <td>${item.descricao}</td>
                    <form action="<%=request.getContextPath()%>/alterarItem" method="post">
                        <input name="item_id" type="hidden" value="${item.id}">
                        <td>
                            <button class="btn btn-warning"type="submit">Alterar</button>
                    </form>
                    <td><a class="btn btn-danger" href="excluirItem?id=${item.getId()}">Excluir</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
