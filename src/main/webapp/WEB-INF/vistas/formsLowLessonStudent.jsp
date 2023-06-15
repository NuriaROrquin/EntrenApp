<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Styles -->
    <link href="css/styles.css" rel="stylesheet">

</head>

<header class="alumno-color">
    <h1>Bienvenido Juan!</h1>
    <img src="assets/profile.jpg">
    <!--<nav>
        <ul>
            <li><a href="#">Inicio</a></li>
            <li><a href="#">Acerca de</a></li>
            <li><a href="#">Contacto</a></li>
        </ul>
    </nav>-->
</header>

<body>

    <section class="first-section">
        <div class="classes alumno-color">
            <h2>Clases Reservadas</h2>
            <div class="container-classes center-block">
                <div class="class">
                    <div class="first-container alumno-color-secundario">
                        <h4>Funcional</h4>
                        <img src=""/>
                    </div>
                    <div class="second-container">
                        <span class="alumno-color-font">Jueves 18</span>
                        <span class="alumno-color-font">Carlos Rodriguez</span>
                        <span class="alumno-color-font">10:30 - 11:30</span>
                    </div>
                    <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block btn-margin" Type="Submit"/>
                    Dar de Baja</button>
                </div>
                <div class="class">
                    <div class="first-container alumno-color-secundario">
                        <h4>Funcional</h4>
                        <img src=""/>
                    </div>
                    <div class="second-container">
                        <span class="alumno-color-font">Jueves 18</span>
                        <span class="alumno-color-font">Carlos Rodriguez</span>
                        <span class="alumno-color-font">10:30 - 11:30</span>
                    </div>
                    <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block btn-margin" Type="Submit"/>
                    Dar de Baja</button>
                </div>
                <div class="class">
                    <div class="first-container alumno-color-secundario">
                        <h4>Funcional</h4>
                        <img src=""/>
                    </div>
                    <div class="second-container">
                        <span class="alumno-color-font">Jueves 18</span>
                        <span class="alumno-color-font">Carlos Rodriguez</span>
                        <span class="alumno-color-font">10:30 - 11:30</span>
                    </div>
                    <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block btn-margin" Type="Submit"/>
                    Dar de Baja</button>
                </div>
            </div>
        </div>

    </section>

<%--Bloque que es visible si el elemento error no esta vacio	--%>
<c:if test="${not empty error}">
    <h4><span>${error}</span></h4>
    <br>|
</c:if>
${msg}
</div>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>