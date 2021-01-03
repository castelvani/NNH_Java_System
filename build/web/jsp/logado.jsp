<%@page import="java.io.PrintWriter"%>
<%@page import="Model.Usuario"%>
<%@page import="Fachada.Fachada"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../bootstrap-4.3.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../assets/css/logado.css" rel="stylesheet" type="text/css"/>        

        <title>Logado</title>
    </head>
    <body>
        <%
            Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

            String perfil = usuarioLogado.getPerfil().toString();

            if (perfil.equalsIgnoreCase("administrador")) {
        %>
        <div  class="index">            
            <nav class="navbar bg-dark navbar-dark justify-content-end ">
                <a href="http://localhost:8080/NNH2/" class="mr-auto"><input class="form-control mr-sm-2" value="Home" type="button"></a>
                <h3 class="nome_usuario">Olá, <%= usuarioLogado.getNome()%>!</h3>
                <form method="post" action="../logout">
                    <input class="btn btn-danger form-control mr-sm-2" value="Sair"type="submit">
                </form>
            </nav>
        </div>
        <div class="container">
            <h1 class="my-4">Gerenciamento geral - Administração</h1>
            <div class="row">
                <div class="col-lg-4 mb-4 ">
                    <div class="card h-100">
                        <img class="card-img-top" src="../assets/images/quartos.jpg" alt="">
                        <div class="card-body">
                            <h4 class="card-title">
                                <a href="../funcionario/gerirQuarto.jsp"><input class="btn btn-success" type="button" value="Gerenciar quartos"/></a>
                            </h4>
                            <p class="card-text">Gerenciamento de quartos</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 mb-4">
                    <div class="card h-100">
                        <img class="card-img-top" src="../assets/images/usuario.jpg" alt="">
                        <div class="card-body">
                            <h4 class="card-title">
                                <a href="../funcionario/gerirUsuario.jsp"><input class="btn btn-success" type="button" value="Gerenciar usuários"/></a><br>
                            </h4>
                            <p class="card-text">Gerenciamento de usuários.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 mb-4">
                    <div class="card h-100">
                        <img class="card-img-top" src="../assets/images/reserva.jpg" alt="">
                        <div class="card-body">
                            <h4 class="card-title">
                                <form action="<%= request.getContextPath()%>/consultarReserva" method="get">
                                    <input class="btn btn-success" type="submit" value="Gerenciar reservas"/>
                                </form>
                            </h4>
                            <p class="card-text">Gerenciamento de reservas.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 mb-4">
                    <div class="card h-100">
                        <img class="card-img-top" src="../assets/images/items.png" alt="">
                        <div class="card-body">
                            <h4 class="card-title">
                                <a href="../funcionario/gerirItem.jsp"><input class="btn btn-success" type="button" value="Gerenciar itens"/></a><br>
                            </h4>
                            <p class="card-text">Gerenciamento de itens.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 mb-4">
                    <div class="card h-100">
                        <img class="card-img-top" src="../assets/images/comida.jpg" alt="">
                        <div class="card-body">
                            <h4 class="card-title">
                                <form action="<%= request.getContextPath()%>/gerenciarPedidosReserva" method="post">
                                    <input class="btn btn-success" type="submit" value="Gerenciar pedidos"/>
                                </form>
                            </h4>
                            <p class="card-text">Gerenciamento de pedidos.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
        } else {
        %>
        <nav class="navbar bg-dark navbar-dark justify-content-end ">
            <a href="http://localhost:8080/NNH2/" class="mr-auto"><input class="form-control mr-sm-2" value="Home" type="button"></a>
            <h3 class="nome_usuario">Olá, <%= usuarioLogado.getNome()%>!</h3>
            <form method="post" action="../logout">
                <input class="btn btn-danger form-control mr-sm-2" value="Sair"type="submit">
            </form>
        </nav>
        <div class="container">
            <h1 class="my-4">Gerenciar conta</h1>
            <div class="row">
                <div class="col-lg-6 mb-4">
                    <div class="card h-100">
                        <a href="#"><img class="card-img-top" src="../assets/images/quartos.jpg" alt=""></a>
                        <div class="card-body">
                            <h4 class="card-title">
                                <form action="<%=request.getContextPath()%>/usuarioReservas" method="post">
                                    <input class="btn btn-success" type="submit" value="Quartos">
                                </form>
                            </h4>
                            <p class="card-text">Visualizar os quartos que foram reservados por você, ver contas e realizar check-out.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 mb-4">
                    <div class="card h-100">
                        <a href="#"><img class="card-img-top" src="../assets/images/comida.jpg" alt=""></a>
                        <div class="card-body">
                            <h4 class="card-title">
                                <form action="<%=request.getContextPath()%>/consultarQuartoPedido" method="post">
                                    <input class="btn btn-success" type="submit" value="Pedidos">
                                </form>
                            </h4>
                            <p class="card-text">Clique aqui para visualizar os pedidos que foram feitos por você.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </body>
</html>
<script src="../bootstrap-4.3.1-dist/js/bootstrap.min.js" type="text/javascript"></script>