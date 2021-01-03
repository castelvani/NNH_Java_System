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
        <br>
        <div class="container">
            <ul class="nav nav-pills" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="pill" href="#itens">Itens</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="pill" href="#pedidos">Meus pedidos</a>
                </li>
            </ul>
            <br>
            <div class="tab-content">
                <div id="itens" class="container tab-pane active"><br>
                    <div class="row">
                        <c:forEach items="${itens}" var="item">
                            <br>
                            <div class="card mb-4" style="width:250px; margin-right: 3%;">
                                <img class="card-img-top" src="./assets/images/noimage.jpg" alt="Card image" style="width:100%">
                                <div class="card-body">                            
                                    <h4 class="card-title">${item.nome}</h4>
                                    <p class="card-text">Tipo: ${item.tipo}</p>
                                    <p class="card-text">Preço: R$${item.preco}</p>
                                    <p class="card-text">Sobre: ${item.descricao}</p>
                                    <form action="<%= request.getContextPath()%>/inserirPedido" method="post">
                                        <input type="hidden" value="${item.id}" name="id_item">
                                        <input type="hidden" value="${id}" name="id_usuario">
                                        <input type="hidden" value="${id_reserva}" name="id_reserva" >
                                        <input type="hidden" value="${id_quarto}" name="id_quarto">
                                        <input class="form-control" placeholder="Quantidade" type="number" name="quantidade">
                                        <br>
                                        <input class="btn btn-secondary form-control" type="submit" value="Pedir"/>
                                    </form>
                                </div>
                            </div>
                            <br>
                        </c:forEach>
                    </div>
                </div>
                <div id="pedidos" class="container tab-pane fade"><br>
                    <div class="row">
                        <c:choose>
                            <c:when test="${mensagemVazio == 1}">
                                <p>Não há pedidos realizados por você.</p>
                            </c:when>    
                            <c:otherwise>
                                <c:forEach items="${pedidos}" var="pedidos">

                                    <div class="card mb-4" style="width:250px; margin-right: 3%;">
                                        <img class="card-img-top" src="./assets/images/noimage.jpg" alt="Card image" style="width:100%">
                                        <div class="card-body">
                                            <h4 class="card-title">${pedidos.item.nome}</h4>
                                            <p class="card-text">Tipo: ${pedidos.item.tipo}</p>
                                            <p class="card-text">Preço: R$${pedidos.item.preco}</p>
                                            <p class="card-text">Sobre: ${pedidos.item.descricao}</p>
                                            <p class="card-text">Quantidade: ${pedidos.quantidade}</p>
                                            <c:set var="total" value="${pedidos.item.preco * pedidos.quantidade}"/>
                                            <p class="card-text">Total: R$${total}</p>
                                            <b class="card-text">Status: <span class="badge badge-success">${pedidos.status}</span></b>
                                            <hr>
                                            <form action="<%= request.getContextPath()%>/cancelarPedido" method="post">
                                                <input type="hidden" value="${pedidos.reserva.id}" name="id_reserva">
                                                <input type="hidden" value="${pedidos.item.id}" name="id_item">
                                                <input type="hidden" value="${pedidos.id}" name="id_pedido">
                                                <input type="hidden" value="${id}" name="id_usuario">
                                                <input type="hidden" value="${id_quarto}" name="id_quarto">
                                                <input class="btn btn-danger form-control" type="submit" value="Cancelar"/>
                                            </form>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>