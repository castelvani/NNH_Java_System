<%-- 
    Document   : atualizarPedido
    Created on : 03/10/2020, 19:50:32
    Author     : Gabriel
--%>

<%@include file="../header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Atualizar Pedido</title>
    </head>
    <body>
        <div class="p-4 mt-5">
            <div class="row form_gerir">
                <div class="col-md-7 pt-5 shadow-lg">
                    <h1 class="p-3">Atualizar Pedido</h1>
                    <b><label>Nº</label></b>
                    <input class="form-control" disabled value="${pedido.id}">
                    <b><label>Quantidade</label></b>
                    <input class="form-control" disabled value="${pedido.quantidade}">
                    <b><label>Status atual</label></b>
                    <input class="form-control" disabled value="${pedido.status}">
                    <br>
                    <form class="form-group" action="<%= request.getContextPath()%>/atualizarPedido" method="post">
                        Status: <select class="form-control" name="status">
                            <option>Realizado</option>
                            <option>Preparando</option>
                            <option>A caminho</option>
                            <option>Cancelado</option>
                        </select>
                        <br>
                        <input type="hidden" value="${id_reserva}" name="id_reserva">
                        <input type="hidden" value="${pedido.id}" name="id_pedido">
                        <input class="btn btn-success form-control" type="submit" value="Atualizar">
                    </form>
                    <br>
                </div>
            </div>
        </div>
    </body>
</html>
<script src="../bootstrap-4.3.1-dist/js/bootstrap.min.js" type="text/javascript"></script>
