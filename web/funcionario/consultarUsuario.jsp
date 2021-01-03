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
        <title>Consultar usuarios</title>
    </head>
    <body>
        <div class="container">
            <h1 class="my-4">Usuarios</h1>
            <table id="tabela_consulta" class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>Nome do usuario</th>
                        <th>Data de nascimento</th>
                        <th>Email</th>
                        <th>Perfil/Permissão</th>
                        <th>Alteração</th>
                        <th>Exclusão</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lista}" var="usuario">
                        <tr>
                            <td>${usuario.id}</td>
                            <td>${usuario.nome}</td>
                            <td>${usuario.dtNasc}</td>
                            <td>${usuario.email}</td>
                            <td>${usuario.perfil}</td>
                    <form action="<%=request.getContextPath()%>/alterarUsuario" method="post">
                        <input name="usuario_id" type="hidden" value="${usuario.id}">
                        <td>
                            <button class="btn btn-warning"type="submit">Alterar</button>
                        </td>
                    </form>
                    <td><a class="btn btn-danger" href="excluirUsuario?id=${usuario.getId()}">Excluir</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
