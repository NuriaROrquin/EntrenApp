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
    <title>Tus clases</title>
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


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function () {
        $('select[name="states"]').change(function () {
            $.ajax({
                url: '/lessonsByState?idState=' + $(this).val(),
                type: 'GET',
                success: function (response) {
                    var $responseHtml = $(response);
                    var $newBodyContent = $responseHtml.find('#lessonsContainer').html();
                    $('#lessonsContainer').html($newBodyContent);
                },
                error: function (xhr, status, error) {
                    console.error(error);
                }
            });
        });

        $(document).on('click', '.cancel-button', function () {
            var selectedValue = $(this).attr('name');
            $.ajax({
                url: '/cancelLesson',
                type: 'POST',
                data: {lessonId: selectedValue},
                success: function (response) {
                    var $responseHtml = $(response);
                    var $newBodyContent = $responseHtml.find('#lessonsContainer').html();
                    $('#lessonsContainer').html($newBodyContent);
                    $('[name="states"]').val('0');
                },
                error: function (xhr, status, error) {
                    console.error(error);
                }
            });
        });
        /*$(document).on('click', '.modify-button', function () {
            var selectedValue = $(this).attr('name');
            $.ajax({
                url: '/getDataLesson',
                type: 'POST',
                data: {lessonId: selectedValue},
                success: function (response) {
                    $(".first-section").html(response);
                },
                error: function (xhr, status, error) {
                    console.error(error);
                }
            });
        });*/

    });
</script>


<main class="lessons">
    <section class="table">
        <div class="container table-responsive" style="width: 90%;">
            <c:if test="${not empty success}">
                <div class="alert alert-success" role="alert">
                    ${success}
                </div>
            </c:if>
            <label>Filtrar por estado:</label>
            <select name="states">
                <option value=0>Mostrar todas</option>
                <option value=1>Pendiente</option>
                <option value=2>En Curso</option>
                <option value=3>Finalizada</option>
                <option value=4>Cancelada</option>
            </select>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col" style="text-align: center">Fecha</th>
                    <th scope="col" style="text-align: center">Actividad</th>
                    <th scope="col" style="text-align: center">Disciplina</th>
                    <th scope="col" style="text-align: center">Hora comienzo</th>
                    <th scope="col" style="text-align: center">Hora final</th>
                    <th scope="col" style="text-align: center">Lugar</th>
                    <th scope="col" style="text-align: center">Dificultad</th>
                    <th scope="col" style="text-align: center">Capacidad</th>
                    <th scope="col" style="text-align: center">Estado</th>
                    <th scope="col" style="text-align: center">Edad Min</th>
                    <th scope="col" style="text-align: center">Edad Max</th>
                    <th scope="col" style="text-align: center">Cancelar</th>
                    <th scope="col" style="text-align: center">Modificar</th>
                </tr>
                </thead>
                <tbody id="lessonsContainer">
                <c:forEach var="clase" items="${lessons}">
                    <tr>
                        <td style="text-align: center"><fmt:formatDate value="${clase.date}" pattern="dd-MM"/></td>
                        <td style="text-align: center">${clase.name}</td>
                        <td style="text-align: center">${clase.discipline.description}</td>
                        <td style="text-align: center">${clase.detail.startHour}</td>
                        <td style="text-align: center">${clase.detail.endHour}</td>
                        <td style="text-align: center">${clase.place.name}</td>
                        <td style="text-align: center">${clase.difficulty.description}</td>
                        <td style="text-align: center">${clase.detail.capacity}</td>
                        <td style="text-align: center">${clase.state.description}</td>
                        <td style="text-align: center">${clase.minimum_age}</td>
                        <td style="text-align: center">${clase.maximum_age}</td>
                        <td style="text-align: center">
                            <c:if test="${clase.state.description == 'PENDIENTE'}">
                                <button type="button" class="btn"
                                        name="${clase.idClass}" style="margin: 0">X
                                </button>
                            </c:if>
                        </td>
                        <td style="text-align: center">
                            <c:if test="${clase.state.description == 'PENDIENTE'}">
                                <a class="btn"
                                   href="/getDataLesson?lessonId=${clase.idClass}" >✎</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>

</main>

<footer>
    <p>Derechos de autor &copy; 2023 | Mi Página de Inicio</p>
</footer>

</body>
</html>
