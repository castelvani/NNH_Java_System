<%-- 
    Document   : gerirQuarto
    Created on : 10/11/2019, 17:01:28
    Author     : Gabriel
--%>
<%@include file="../header.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="../bootstrap-4.3.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../assets/css/gerenciamento.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar item</title>
    </head>
    <body>
        <div class="pt-5 mt-5">
            <div class="row form_gerir">
                <div class="col-md-7 pt-5 shadow-lg">
                    <h1 class="pb-3">Alterar item</h1>
                    <form class="form-group" action="<%= request.getContextPath()%>/atualizarItem" method="post">
                        <input type="hidden" value="${item.id}" name="id"><br>
                        <label>Nome: </label>
                        <input type="text" class="form-control" name="nome" value="${item.nome}"/><br>
                        <label>Preço: </label><br>
                        <input type="number" name="preco" class="form-control" value="${item.preco}"><br>
                        <label>Tipo: </label>
                        <small>(corrente: ${item.tipo})</small>
                        <select class="form-control" name="tipo">
                            <option>Bebida</option>
                            <option>Comida</option>
                            <option>Outro</option>
                        </select>
                        <br>
                        <label>Descrição: </label>
                        <textarea class="form-control" name="descricao" rows="3">${item.descricao}</textarea>
                        <br>
                        <input class="btn btn-success form-control" type="submit" value="Inserir">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
<script src="../bootstrap-4.3.1-dist/js/bootstrap.min.js" type="text/javascript"></script>