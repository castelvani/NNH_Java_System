<%@page import="Model.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <header>
        <style>
            html{
                overflow-x: hidden;
            }
        </style>
        <link href="assets/css/footer.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap-4.3.1-dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/logado.css" rel="stylesheet" type="text/css"/>
        <link href="assets/datatable/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <script src="assets/datatable/jquery-3.5.1.min.js" type="text/javascript"></script>
        <script src="assets/datatable/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="assets/datatable/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="assets/datatable/buttons.html5.min.js" type="text/javascript"></script>
        <script src="assets/datatable/jszip.min.js" type="text/javascript"></script>
        <script src="assets/datatable/tabela_customizada.js" type="text/javascript"></script>
        <script src="assets/datatable/tabela_export.js" type="text/javascript"></script>
        <link href="assets/css/margin.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/gerenciamento.css" rel="stylesheet" type="text/css"/>
        <link href="assets/daterangepicker-master/daterangepicker.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/index.css" rel="stylesheet" type="text/css"/>    
        <link href="assets/daterangepicker-master/daterangepicker.css" rel="stylesheet" type="text/css"/>
        <div  class="index ">
            <nav class="navbar bg-dark navbar-dark">
                <a href="http://localhost:8080/NNH2/"><input class="form-control mr-sm-2" value="Home" type="button"></a>
                    <c:choose>
                        <c:when test="${usuarioLogado.getNome() == null}">
                        <div class="justify-content-end row">
                            <div class="pr-3 col-xs-6">
                                <a href="http://localhost:8080/NNH2/jsp/login.jsp" class="link"><input class="form-control mr-sm-2" value="Login"type="button"></a>
                            </div>
                            <div class="mr-3 col-xs-6">
                                <a href="http://localhost:8080/NNH2/jsp/cadastro.jsp" class="link"><input class="form-control mr-sm-2" value="Cadastre-se"type="button"></a>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <h4 class="nome_usuario"><a href="http://localhost:8080/NNH2/jsp/logado.jsp">Minha conta</a></h4>
                    </c:otherwise>
                </c:choose>
            </nav>
        </div>
    </header>