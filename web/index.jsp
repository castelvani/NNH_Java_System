<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<head>
    <title>NNH</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="assets/daterangepicker-master/daterangepicker.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/index.css" rel="stylesheet" type="text/css"/>
    <script src="assets/daterangepicker-master/jquery.min.js" type="text/javascript"></script>
    <script src="assets/daterangepicker-master/moment.min.js" type="text/javascript"></script>
    <script src="assets/daterangepicker-master/daterangepicker.min.js" type="text/javascript"></script>
    <link href="assets/daterangepicker-master/daterangepicker.css" rel="stylesheet" type="text/css"/>
    <script src="assets/daterangepicker-master/calendario_nosso.js" type="text/javascript"></script>
    <script src="assets/daterangepicker-master/jquery.daterangepicker.js" type="text/javascript"></script>
    <script src="assets/js/avisoSucesso.js" type="text/javascript"></script>
</head>
<body>
    <div class="aviso">
        <h1 class="text-center badge-success">${aviso}</h1>
    </div>
    <%
        request.getSession().removeAttribute("aviso");
    %>
    <h5 class="text-center titulo_index">ESCOLHA A DATA QUE DESEJA FAZER A SUA RESERVA</h5>
    <div class="filtro_busca">
        <form id="two-inputs" class="form_busca" action="/NNH2/consultarQuartosDisponiveis" method="get">
            <input id="daterange" class="btnDatas"/>
            <input type="hidden" name="dt_entrada" id="dt_entrada" class="dt_entrada"/>
            <input type="hidden" name="dt_saida" id="dt_saida" class="dt_saida"/>
            <input class="btnBuscar btn btn-success mb-3" type="submit" value="Buscar Quarto">
        </form>
    </div>
    <div class="container-fluid">
        <div id="demo" class="carousel slide mt-5" data-ride="carousel">
            <ul class="carousel-indicators">
                <li data-target="#demo" data-slide-to="0" class="active"></li>
                <li data-target="#demo" data-slide-to="1"></li>
                <li data-target="#demo" data-slide-to="2"></li>
            </ul>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="assets/images/quartoslide1.jpg" alt="Quarto 1">
                </div>
                <div class="carousel-item">
                    <img src="assets/images/quartoslide2.jpg" alt="Quarto 2">
                </div>
                <div class="carousel-item">
                    <img src="assets/images/quartoslide3.jpg" alt="Quarto 3">
                </div>
            </div>
            <a class="carousel-control-prev" href="#demo" data-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next" href="#demo" data-slide="next">
                <span class="carousel-control-next-icon"></span>
            </a>
        </div>
    </div>
</body>
<script src="assets/bootstrap-4.3.1-dist/js/bootstrap.min.js" type="text/javascript"></script>