<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../assets/bootstrap-4.3.1-dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="../assets/css/login.css" rel="stylesheet" type="text/css"/>
        <title>Página de Login</title>
    </head>
    <body>
        <div  class="index ">
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
        </div>
        <div class="container shadow-lg bg-white login-box">
            <h4 class="pt-3">Login</h4>
            <form action="<%= request.getContextPath()%>/login" method="post">
                <label>E-mail</label>
                <input class="form-control" type="text" name="email" id="email">
                <label>Senha</label>
                <input class="form-control mb-3" type="password" name="senha" id="senha">
                <input class="btn btn-success teste" type="submit" value="Entrar">
            </form>
        </div>
    </body>
</html>
