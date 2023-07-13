<%@ page import="ar.edu.unlam.tallerweb1.delivery.models.DataLesson" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Language" content="es">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@200;300;400;500;600;700;800&display=swap"
          rel="stylesheet">
    <link rel="icon" type="image/x-icon" href="/assets/logo-secondary.png"/>
    <title>Bienvenido!</title>
</head>
<body class="yoga">
<div class="body-overlay">
    <header class="site-navbar alumno-color" role="banner">
        <div class="container">
            <div class="row align-items-center">
                <div class="container-logo">
                    <img src="/assets/logo-white.png">
                    <h1 class="mb-0 site-logo"><a href="/home" class="title-topbar">¡Entrenemos!</a></h1>
                </div>
                <div class="col-12 col-md-10 d-none d-xl-block">
                    <nav class="site-navigation position-relative text-right" role="navigation">
                        <ul class="site-menu js-clone-nav mr-auto d-none d-lg-block">
                            <li class="has-children">
                                <a href="/lessons"><span>Mis Clases</span></a>
                                <ul class="dropdown arrow-top">
                                    <li><a href="/lessonsByState?idState=1">Pendientes</a></li>
                                    <li><a href="/lessonsByState?idState=2">En curso</a></li>
                                    <li><a href="/lessonsByState?idState=3">Finalizadas</a></li>
                                    <li><a href="/lessonsByState?idState=4">Canceladas</a></li>
                                </ul>
                            </li>
                            <li><a href="califications"><span>Calificaciones</span></a></li>
                            <li><a href="register-lesson"><span>Cargar</span></a></li>
                            <li><a href="/profile"><img
                                    style="width: 21px; height: 21px; margin-right: 2rem; margin-left: 2rem"
                                    src="/assets/user.png"/></a></li>
                            <li><a href="/logout"><img style="width: 21px; height: 21px" src="/assets/logout.png"/></a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="d-inline-block d-xl-none ml-md-0 mr-auto py-3" style="position: relative; top: 3px;"><a
                        href="#"
                        class="site-menu-toggle js-menu-toggle text-white"><span
                        class="icon-menu h3"></span></a></div>
            </div>
        </div>

    </header>

    <main>
        <div id="form" class="col-md-6">
            <form:form cssClass="lesson-form" action="updateState" method="POST" modelAttribute="stateLesson">
                <h3 class="form-signin-heading">Cambiar estado</h3>
                <div>
                    <form:label path="idState">Estado: </form:label>
                    <form:select path="idState" id="idState" class="form-control">
                            <form:option value="2">EN CURSO</form:option>
                            <form:option value="3">FINALIZADA</form:option>
                    </form:select>
                </div>
                <form:hidden value="${idLesson}" cssStyle="display: none" path="lessonId" id="lessonId" class="form-control"/>

                <button id="btn-lesson" class="btn" type="Submit">
                    Publicar
                </button>
                <button id="cancel-btn" class="btn btn-cancel" type="button">
                    Cancelar
                </button>
            </form:form>

            <%--Bloque que es visible si el elemento error no esta vacio	--%>
            <c:if test="${not empty preferencesSaved}">
                <h4><span>${preferencesSaved}</span></h4>
                <br>
            </c:if>
            ${msg}
        </div>
    </main>

    <footer>
        <p>¡Entrenemos! &copy; 2023 | Los Borbotones</p>
    </footer>

    <script>
        const fechaInput = document.getElementById('date');

        const fechaActual = new Date();
        const fechaMaxima = new Date(fechaActual.getFullYear(), fechaActual.getMonth() + 1, fechaActual.getDate());

        const fechaMaximaISO = fechaMaxima.toISOString().split('T')[0];
        const fechaMinimaISO = fechaActual.toISOString().split('T')[0];

        fechaInput.max = fechaMaximaISO;
        fechaInput.min = fechaMinimaISO;
    </script>

    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
</div>
</body>
</html>