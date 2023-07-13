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
    <title>Calificaciones</title>
</head>
<body class="elements">
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
                            <li><a id="califications-link" href="/home"><span id="califications-menu"></span></a></li>
                            <li><a id="signin-link" href="/home"><span id="signin-menu"></span></a></li>
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
        <section class="lesson-detail">
            <div class="container">
                <h2>¿Te interesó esta clase?</h2>
                <p>¡Acá te facilitamos el detalle!</p>
                <div class="container-detail">
                    <div>
                        <span class="description">Nombre de la clase: </span>
                        <span class="detail">${lesson.name}</span>
                    </div>
                    <div>
                        <span class="description">Clase a cargo de: </span>
                        <span class="detail">${lesson.profesor.name}</span>
                    </div>
                    <div>
                        <span class="description">Fecha, hora y capacidad: </span>
                        <span class="detail">El <fmt:formatDate value="${lesson.date}"
                                                                pattern="dd-MM"/> de ${lesson.detail.startHour}
                        a ${lesson.detail.endHour}. Dictada para ${lesson.detail.capacity} personas.</span>
                    </div>
                    <div>
                        <span class="description">Para alumnos de: </span>
                        <span class="detail">${lesson.minimum_age} a ${lesson.maximum_age} años</span>
                    </div>
                    <div>
                        <span class="description">Grado de dificultad: </span>
                        <span class="detail">${lesson.difficulty.description}</span>
                    </div>
                    <div>
                        <span class="description">Disciplina: </span>
                        <span class="detail">${lesson.discipline.description}</span>
                    </div>
                    <div>
                        <span class="description">Dirección: </span>
                        <span class="detail">${lesson.place.name}</span>
                    </div>
                    <div>
                        <span class="description">Estado: </span>
                        <span class="detail">${lesson.state.description}</span>
                    </div>
                    <div>
                        <div id="map" style="width: 100%;height: 450px;border-radius: 10px;"></div>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <dl class="site-menu js-clone-nav mr-auto d-none d-lg-block">

    </dl>
    <footer>
        <p>¡Entrenemos! &copy; 2023 | Los Borbotones</p>
    </footer>

    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>

    <script>
        $(document).ready(function () {
            $.ajax({
                url: '/getRole',
                type: 'GET',
                success: function (response) {
                    if (response == 2) {
                        $('#califications-menu').html("Calificar");
                        $('#califications-link').attr("href", "/lessonsByState?idState=3");
                        $('#signin-menu').html("Anotate");
                        $('#signin-link').attr("href", "availableLessons");
                    } else {
                        $('#califications-menu').html("Calificaciones");
                        $('#califications-link').attr("href", "/califications");
                        $('#signin-menu').html("Cargar");
                        $('#signin-link').attr("href", "/register-lesson");
                    }
                },
                error: function (xhr, status, error) {
                    console.error(error);
                }
            });
        });
    </script>

    <script>

        var map;
        var marker;

        function initMap() {
            var initialLatLng = {lat: ${lesson.place.latitude}, lng: ${lesson.place.longitude}};

            map = new google.maps.Map(document.getElementById('map'), {
                center: initialLatLng,
                zoom: 16
            });

            marker = new google.maps.Marker({
                position: {lat: ${lesson.place.latitude}, lng: ${lesson.place.longitude}},
                map: map
            });
        }

    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAtBFAnDiYmJfv-JmOOJUAsTXJr307FiK8&callback=initMap"
            async defer></script>
</div>
</body>

</html>
