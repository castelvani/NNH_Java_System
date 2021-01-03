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
        <title>Gerenciador de quartos</title>
    </head>
    <body>
        <div class="pt-5 mt-5">
            <div class="row form_gerir">
                <div class="col-md-7 pt-5 shadow-lg">
                    <h1 class="pb-3">Gerenciador de quartos</h1>
                    <form class="form-group" action="../inserirQuarto" method="post">
                        <label>Número: </label><input type="number" class="form-control" id="numero" name="numero"/><br>
                        <label>Valor da diária: </label><input class="form-control" type="number" id="valorDiaria" name="valorDiaria"/><br>
                        <label>Tipo: </label>
                        <select class="form-control" name="tipo">
                            <option>Executivo</option>
                            <option>Economico</option>
                        </select><br>
                        <label>Status: </label>
                        <select class="form-control" name="status">
                            <option>Disponivel</option>
                            <option>Ocupado</option>
                            <option>Reservado</option>
                        </select><br>
                        <input class="btn btn-success form-control" type="submit" value="Inserir">
                    </form>
                    <form action="<%= request.getContextPath()%>/consultarQuarto" method="get">
                        <input class="btn btn-secondary form-control" type="submit" value="Listar quartos"/>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
<script src="../bootstrap-4.3.1-dist/js/bootstrap.min.js" type="text/javascript"></script>