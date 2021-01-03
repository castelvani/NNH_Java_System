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
        <title>Gerenciador de usuarios</title>
    </head>
    <body>
        <div class="p-4 mt-5">
            <div class="row form_gerir">
                <div class="col-md-7 pt-5 shadow-lg">
                    <h1 class="p-3">Gerenciador de Usuarios</h1>
                    <form class="form-group" action="<%= request.getContextPath()%>/inserirUsuario" method="post">
                        <div>
                            <label>Nome</label>
                            <input class="form-control" type="text" placeholder="João Exemplo" name="nome">
                        </div>            
                        <label>Email</label>
                        <input class="form-control" type="email" placeholder="exemplo@email.com" name="email">
                        <br>
                        <label>Data de nascimento</label>
                        <input class="form-control" type="date" name="dtNasc">
                        <br>
                        <label>Senha</label>            
                        <input class="form-control" type="password" placeholder="Senha" name="senha">
                        <br>
                        Perfil: <select class="form-control" name="perfil">
                            <option>Administrador</option>
                            <option>Comum</option>
                        </select>
                        <br>
                        <input class="btn btn-success form-control" type="submit" value="Cadastrar">
                    </form>
                    <!--                          \/ Erro aqui-->
                    <form class="form-group" action="<%= request.getContextPath()%>/consultarUsuario" method="get">
                        <input class="btn btn-secondary form-control" type="submit" value="Listar usuarios"/>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
<script src="../bootstrap-4.3.1-dist/js/bootstrap.min.js" type="text/javascript"></script>
