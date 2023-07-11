<%@ page import="ar.edu.unlam.tallerweb1.delivery.models.DataLesson" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Anotate</title>
</head>
<body class="exercise">
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
                            <li><a href="califications"><span>Calificar</span></a></li>
                            <li><a href="/availableLessons"><span>Anotate</span></a></li>
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
            <form:form cssClass="calificate-lesson" action="calificateLesson" method="POST"
                       modelAttribute="dataCalification">
                <h3 class="form-signin-heading">Calificar clase</h3>

                <form:hidden value="${idLesson}" cssStyle="display: none" path="lessonId" id="lessonId"
                             class="form-control"/>

                <div>
                    <form:label path="score">Seleccioná la calificacion de la clase</form:label>
                    <form:select path="score" id="score" class="form-control">
                        <form:option value="1">Muy Mala</form:option>
                        <form:option value="2">Mala</form:option>
                        <form:option value="3">Regular</form:option>
                        <form:option value="4">Buena</form:option>
                        <form:option value="5">Muy Buena</form:option>
                    </form:select>
                </div>

                <div>
                    <form:label path="description">Comentario: </form:label>
                    <form:input path="description" type="text" id="description" class="form-control"/>
                </div>

                <button id="btn-calificar" class="btn" type="Submit">
                    Publicar
                </button>
                <button id="cancel-btn" class="btn btn-cancel" type="button">
                    Cancelar
                </button>
            </form:form>

        </div>
    </main>

    <footer>
        <p>Derechos de autor &copy; 2023 | Mi Página de Inicio</p>
    </footer>

    <script>
        document.getElementById("cancel-btn").addEventListener("click", function () {
            var previousPage = document.referrer;

            window.location.href = previousPage;
        });
    </script>
</div>
</body>
</html>
