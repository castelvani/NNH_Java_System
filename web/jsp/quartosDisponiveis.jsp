<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            html{
                overflow-x: hidden;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/bootstrap-4.3.1-dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="assets/daterangepicker-master/daterangepicker.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/index.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
        <script src="assets/daterangepicker-master/moment.min.js" type="text/javascript"></script>
        <script src="assets/daterangepicker-master/calendario_nosso.js" type="text/javascript"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
        <link href="assets/css/reserva_disponiveis.css" rel="stylesheet" type="text/css"/>
        <title>Quartos Disponiveis</title>
    </head>
    <body>
        <nav class="navbar bg-dark navbar-dark justify-content-end">
            <a href="http://localhost:8080/NNH2/" class="mr-auto"><input class="form-control mr-sm-2" value="Home" type="button"></a>
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
        <h1 class="titulo_quartos">Quartos disponíveis: </h1>

        <table>
            <div class="container">
                <div class="row">
                    <c:forEach items="${listaQuarto}" var="quarto">
                        <br>
                        <div class="card" style="width:400px; margin-right: 3%;">
                            <img class="card-img-top" src="./assets/images/quartod1.jpg" alt="Card image" style="width:100%">
                            <div class="card-body">
                                <h4 class="card-title">Sobre</h4>
                                <p class="card-text">Número do quarto: ${quarto.numero}</p>
                                <p class="card-text">Valor da diária: ${quarto.valorDiaria}</p>
                                <p class="card-text">Tipo: ${quarto.tipo}</p>
                                <a href="verificaSessao?idQuarto=${quarto.id}"><input type="submit" class="btn btn-success" value="Reservar"></a>
                                <c:if test="${quarto.desconto > 0}">
                                    <span class="badge badge-warning">Desconto de ${quarto.desconto}%</span>
                                </c:if>
                            </div>
                        </div>
                        <br>
                    </c:forEach>
                </div>
            </div>
        </table>
        <table>                           
            <c:forEach items="${listaQuartosFinais}" var="listaQuartosFinais">
                <br>
                <div class="container">
                    <div class="card" style="width:400px">
                        <img class="card-img-top" src="./assets/images/quartod1.jpg" alt="Card image" style="width:100%">
                        <div class="card-body">
                            <h4 class="card-title">Sobre</h4>
                            <p class="card-text">Número do quarto: ${listaQuartosFinais.numero}</p>
                            <p class="card-text">Valor da diária: ${listaQuartosFinais.valorDiaria}</p>
                            <p class="card-text">Tipo: ${listaQuartosFinais.tipo}</p>
                            <a href="verificaSessao?idQuarto=${listaQuartosFinais.id}"><input type="submit" class="btn btn-success" value="Reservar"></a>
                            <c:if test="${listaQuartosFinais.desconto > 0}">
                                <span class="badge badge-warning">Desconto de ${listaQuartosFinais.desconto}%</span>
                            </c:if>
                        </div>
                    </div>
                    <br>
                </div>
            </c:forEach>
        </table>
        <c:if test="${listaQuartosFinais.isEmpty()}">
            <h1 class="text-center badge-danger">Não há quartos disponiveis nas datas desejadas.</h1>
        </c:if>
    </body>
</html>
