<%-- 
    Document   : gerirPedidos
    Created on : 03/10/2020, 17:08:09
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
                        <th>Nº Quarto</th>
                        <th>Item</th>
                        <th>Quantidade</th>
                        <th>Status</th>
                        <th>Atualização</th>
                        <th>Cancelamento</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pedidos}" var="pedidos">
                        <tr>
                            <c:choose>
                                <c:when test="${pedidos.status == 'Realizado'}">
                                    <td>${pedidos.id}  <span class="badge badge-success">NOVO PEDIDO</span></td>
                                </c:when>
                                <c:otherwise>
                                    <td>${pedidos.id}</td>
                                </c:otherwise>
                            </c:choose>
                            <td>${pedidos.reserva.quarto.numero}</td>
                            <td>${pedidos.item.nome}</td>
                            <td>${pedidos.quantidade}</td>
                            <td>${pedidos.status}</td>
                    <form action="<%=request.getContextPath()%>/consultarPedidoId" method="post">
                        <input name="id_pedido" type="hidden" value="${pedidos.id}">
                        <input name="id_reserva" type="hidden" value="${pedidos.reserva.id}">
                        <td>
                            <button class="btn btn-primary"type="submit">Atualizar</button>
                    </form>
                    <form action="<%=request.getContextPath()%>/cancelarPedidoReserva" method="post">
                        <input name="id_pedido" type="hidden" value="${pedidos.id}">
                        <input name="id_reserva" type="hidden" value="${pedidos.reserva.id}">
                        <td><input type="submit" class="btn btn-danger" value="Cancelar"></td>
                    </form>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
