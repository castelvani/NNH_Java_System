<%-- 
    Document   : alterarQuarto
    Created on : 13/11/2019, 19:55:40
    Author     : Gabriel
--%>
<%@include file="../header.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Usuario</title>
    </head>
    <body>
        <div class="pt-5 mt-5">
            <div class="row form_gerir">
                <div class="col-md-7 pt-5 shadow-lg">
                    <h1>Alterar Usuario</h1>
                    <form class="form-group" method="post" action="<%=request.getContextPath()%>/atualizarUsuario">
                        <input type="hidden" value="${hospede.id}" name="id"><br>
                        <label>Nome: </label>
                        <input class="form-control" type="text" placeholder="João Exemplo" value="${hospede.nome}" name="nome"><br>
                        <label>Email: </label>
                        <input class="form-control" type="email" placeholder="exemplo@email.com" value="${hospede.email}" name="email" required="true">
                        <br>
                        <label>Data de nascimento: </label>
                        <input class="form-control" type="date" name="dtNasc" value="${hospede.dtNasc}">
                        <br>      
                        <label>Senha: </label>
                        <input class="form-control" type="password" placeholder="Senha" value="${hospede.senha}" name="senha" required="true">
                        <br>
                        <label>Perfil: </label>
                        <small>(corrente: ${hospede.perfil})</small>
                        <select class="form-control" name="perfil">
                            <option>Comum</option>
                            <option>Administrador</option>                            
                        </select>
                        <br>
                        <button class="btn btn-success form-control" type="submit">Alterar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
