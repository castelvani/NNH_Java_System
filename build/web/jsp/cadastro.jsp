<%-- 
    Document   : cadastro
    Created on : 31/10/2019, 20:59:37
    Author     : alunocmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="../assets/bootstrap-4.3.1-dist/js/bootstrap.min.js" type="text/javascript"></script>
        <link href="../assets/bootstrap-4.3.1-dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="../assets/css/cadastro.css" rel="stylesheet" type="text/css"/>
        <title>Cadastro</title>
    </head>
    <body>
        <nav class="navbar bg-dark navbar-dark">
            <a href="../"><input class="form-control mr-sm-2" value="Home" type="button"></a>

            <div class="justify-content-end row">
                <div class="pr-3 col-xs-6">
                    <a href="login.jsp" class="link"><input class="form-control mr-sm-2" value="Login"type="button"></a>
                </div>
                <div class="mr-3 col-xs-6">
                    <a href="cadastro.jsp" class="link"><input class="form-control mr-sm-2" value="Cadastre-se"type="button"></a>
                </div>
            </div>
        </nav>
        <div class="container shadow-lg bg-white login-box">
            <h4 class="pt-3 text-center">Cadastro</h4>
            <form action="/NNH2/cadastro" method="post">
                <div class="cadastro_esquerda">
                    <label>Nome*</label>
                    <input class="form-control" type="text" placeholder="João Exemplo" name="nome" required>
                    <label>Email*</label>
                    <input class="form-control" type="email" placeholder="exemplo@email.com" name="email" required>
                </div>   
                <div class="cadastro_direita">
                    <label>Data de nascimento*</label>
                    <input class="form-control nasc" type="date" name="dtNasc" required>
                    <label>Senha*</label>
                    <input class="form-control nasc" type="password" placeholder="Senha" minlength="6" name="senha" required>
                </div>
                <div style="text-align: center;">
                    <input class="btn btn-success teste" type="submit" value="Cadastrar">
                </div>
            </form>
        </div>
    </body>
</html>
