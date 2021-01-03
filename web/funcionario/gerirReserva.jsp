<%-- 
    Document   : gerirReserva
    Created on : 25/03/2020, 19:32:37
    Author     : Gabriel
--%>
<%@include file="../header.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="../bootstrap-4.3.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciador de reservas</title>
    </head>
    <body>
        <div class="container shadow-lg bg-white login-box">
            <h1>Gerenciador de reservas</h1>
            <form action="../inserirQuarto" method="post">
                Número do quarto: <input type="number" id="numero" name="numero"/><br>
                Valor da diária: <input type="number" id="valorDiaria" name="valorDiaria"/><br>
                Tipo: <select name="tipo">
                    <option>Executivo</option>
                    <option>Economico</option>
                </select><br>
                Status: <select name="status">
                    <option>Disponivel</option>
                    <option>Ocupado</option>
                    <option>Reservado</option>
                </select><br>
            </form>
            <!--                          \/ Erro aqui-->
            <form action="<%= request.getContextPath()%>/consultarReserva" method="get">
                <input type="submit" value="Listar reservas"/>
            </form>
        </div>
    </body>
</html>
<script src="../bootstrap-4.3.1-dist/js/bootstrap.min.js" type="text/javascript"></script>