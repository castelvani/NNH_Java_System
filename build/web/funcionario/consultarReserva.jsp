<%-- 
    Document   : consultarReserva
    Created on : 26/03/2020, 20:02:26
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
        <title>Consultar reservas</title>
    </head>
    <body>
        <div class="container">
            <h1 class="my-4">Consulta de reservas e relatórios</h1>
            <div class="form-group">
                <form class="form-group" action="<%= request.getContextPath()%>/relatorioReservaMes" method="get">
                    <div class="card">
                        <div class="card-body">
                            <b><label class="card-title">Relatório mensal</label></b>
                            <select name="mes" class="form-control">
                                <optgroup>
                                    <option value="1" selected>Janeiro</option>
                                    <option value="2">Fevereiro</option>
                                    <option value="3">Março</option>
                                    <option value="4">Abril</option>
                                    <option value="5">Maio</option>
                                    <option value="6">Junho</option>
                                    <option value="7">Julho</option>
                                    <option value="8">Agosto</option>
                                    <option value="9">Setembro</option>
                                    <option value="10">Outubro</option>
                                    <option value="11">Novembro</option>
                                    <option value="12">Dezembro</option>
                                </optgroup>
                            </select>
                            <br>
                            <input type="submit" class="btn btn-primary" value="Gerar">
                        </div>
                    </div>
                </form>
                <form class="form-group" action="<%= request.getContextPath()%>/relatorioReservaAno" method="get">
                    <div class="card">
                        <div class="card-body">
                            <b><label class="card-title">Relatório anual</label></b>
                            <input type="number" name="ano" class="form-control" min="1990" max="2050" placeholder="Insira o ano">
                            <br>
                            <input type="submit" class="btn btn-primary" value="Gerar">
                        </div>
                    </div>
                </form>
            </div>            
            <table id="tabela_consulta"  class="table table-striped table-bordered dt-responsive nowrap" style="width:100%">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>Nº quarto</th>
                        <th>Nome do usuario</th>
                        <th>Data de entrada</th>
                        <th>Data de saída</th>
                        <th>Situação</th>
                        <th>Alteração</th>
                        <th>Cancelamento</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${reservas}" var="reservas">
                        <tr>
                            <td>${reservas.id}</td>
                            <td>${reservas.quarto.getNumero()}</td>
                            <td>${reservas.usuario.getNome()}</td>
                            <td>${reservas.dt_entrada}</td>
                            <td>${reservas.dt_saida}</td>
                            <td>${reservas.getSituacao()}</td>
                    <form action="<%=request.getContextPath()%>/alterarReserva" method="post">
                        <input name="reserva_id" type="hidden" value="${reservas.id}">
                        <td>
                            <button class="btn btn-warning"type="submit">Alterar</button>
                        </td>
                    </form>
                    <td><a class="btn btn-danger" href="cancelarReserva?id_reserva=${reservas.id}">Cancelar Reserva</a></td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>


