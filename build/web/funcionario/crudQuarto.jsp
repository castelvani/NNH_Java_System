<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>        
    <link href="../style.css" rel="stylesheet" type="text/css"/>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Área restrita</title> 
    </head>
    <body>
        <h1>Área de acesso restrito aos administradores!</h1>
        <h1>Cadastro de novo usuário!</h1>
        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>
        <font color="blue"><%=msg%></font>
        <%}%>
        <div class="login-box">
            <h1>Cadastrar usuario</h1>
            <form action="<%= request.getContextPath()%>/ControleUsuario" method="POST">
                <div class="textbox">
                    <input type="text" placeholder="Nome" name="txtNome">
                </div>
                <div class="textbox">
                    <input type="date" placeholder="Data de nascimento" name="dtNasc">
                </div>
                <div class="textbox">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="Login" name="txtLogin">
                </div>
                <div class="textbox">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="Senha" name="txtSenha">
                </div>
                <div class="textbox">
                    <select name="optPerfil">
                        <option>COMUM</option>
                        <option>ADMINISTRADOR</option>
                    </select><br/>
                </div>
                <input type="submit" class="btn" value="Cadastrar" name="acao">
            </form>
            <a href="../LivroCRUD.jsp"><input type="submit" class="btn" value="Inserir Livro"></a>
            <a href="../principal.jsp"><input type="submit" class="btn" value="Pagina principal"></a>
        </div>
    </body>
</html>
