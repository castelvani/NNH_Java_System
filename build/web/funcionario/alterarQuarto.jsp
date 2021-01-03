<%-- 
    Document   : alterarQuarto
    Created on : 13/11/2019, 19:55:40
    Author     : Gabriel
--%>
<%@page import="java.io.PrintWriter"%>
<%@page import="Model.Quarto"%>
<%@include file="../header.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../assets/bootstrap-4.3.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../assets/css/margin.css" rel="stylesheet" type="text/css"/>
        <title>Alterar Quarto</title>
    </head>
    <body>
        <div class="pt-5 mt-5">
            <div class="row form_gerir">
                <div class="col-md-7 pt-5 shadow-lg">
                    <h1 class="pb-3">Alterar Quarto</h1>
                    <form class="form-group" action="<%=request.getContextPath()%>/atualizar" method="post">
                        <input type="hidden" value="${quarto.id}" name="id"><br>
                        <label>Numero: </label><input class="form-control" type="text" value="${quarto.numero}" name="numero" required><br>
                        <label>Valor diaria: </label><input class="form-control" type="text" value="${quarto.valorDiaria}" name="valorDiaria" required><br>
                        <label>Tipo: </label>
                        <small>(corrente: ${quarto.tipo})</small>
                        <select class="form-control" name="tipo">
                            <option>Executivo</option>
                            <option>Economico</option>
                        </select><br>
                        <label>Status: </label>
                        <small>(corrente: ${quarto.status})</small>
                        <select class="form-control" name="status">
                            <option>Disponivel</option>
                            <option>Ocupado</option>
                        </select><br>
                        <label>Desconto (%): </label><input class="form-control" type="number" value="${quarto.desconto}" name="desconto"><br>
                        <input class="btn btn-success form-control" type="submit" value="Alterar"/>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
<script src="../assets/bootstrap-4.3.1-dist/js/bootstrap.min.js" type="text/javascript"></script>