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
    <script src='https://unpkg.com/leaflet@1.3.3/dist/leaflet.js'></script>
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
            <form:form cssClass="lesson-form" action="validate-lesson" method="POST" modelAttribute="registerLesson">
                <h3 class="form-signin-heading">Nueva Clase</h3>
                <div>
                    <form:label path="dateStr">Fecha: </form:label>
                    <form:input path="dateStr" type="date" id="date" class="form-control"/>
                </div>
                <div>
                    <form:label path="name">Nombre de la actividad: </form:label>
                    <form:input path="name" type="text" id="name" class="form-control"/>
                </div>
                <div>
                    <form:label path="idDiscipline">Disciplina: </form:label>
                    <form:select path="idDiscipline" id="idDiscipline" class="form-control">

                        <c:forEach items="${disciplines}" var="disciplines">
                            <form:option value="${disciplines.idDiscipline}">${disciplines.description}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
                <div>
                    <form:label path="capacity">Capacidad: </form:label>
                    <form:input path="capacity" type="number" id="capacity" class="form-control"/>
                </div>
                <div>
                    <form:label path="hour_iniString">Hora de inicio: </form:label>
                    <form:input path="hour_iniString" type="time" id="hour_ini" class="form-control"/>
                </div>
                <div>
                    <form:label path="hour_finString">Hora de fin: </form:label>
                    <form:input path="hour_finString" type="time" id="hour_fin" class="form-control"/>
                </div>
                <div>
                    <form:label path="age_min">Edad mínima: </form:label>
                    <form:input path="age_min" type="number" id="age_min" class="form-control"/>
                </div>
                <div>
                    <form:label path="age_max">Edad máxima: </form:label>
                    <form:input path="age_max" type="number" id="age_max" class="form-control"/>
                </div>
                <div>
                    <form:label path="idDifficulty">Dificultad: </form:label>
                    <form:select path="idDifficulty" id="idDifficulty" class="form-control">

                        <c:forEach items="${dificulties}" var="dificulties">
                            <form:option value="${dificulties.idDifficulty}">${dificulties.description}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
                <div>
                    <form:label path="address">Dirección: </form:label>
                    <form:input path="address" type="text" id="address" class="form-control"/>
                </div>
                <div>
                    <form:label path="lat">Latitud: </form:label>
                    <form:input path="lat" type="text" id="latInput" class="form-control"/>
                </div>
                <div>
                    <form:label path="lng">Longitud: </form:label>
                    <form:input path="lng" type="text" id="lngInput" class="form-control"/>
                </div>

                <div id="map" style="width: 100%;height: 300px;"></div>

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
        document.getElementById("cancel-btn").addEventListener("click", function () {
            var previousPage = document.referrer;

            window.location.href = previousPage;
        });
    </script>
    <script>
        const fechaInput = document.getElementById('date');

        const fechaActual = new Date();
        const fechaMaxima = new Date(fechaActual.getFullYear(), fechaActual.getMonth() + 1, fechaActual.getDate());

        const fechaMaximaISO = fechaMaxima.toISOString().split('T')[0];
        const fechaMinimaISO = fechaActual.toISOString().split('T')[0];

        fechaInput.max = fechaMaximaISO;
        fechaInput.min = fechaMinimaISO;
    </script>

    <script>

        var map;
        var marker;

        function initMap() {
            var initialLatLng = { lat: -34.67019736429832, lng: -58.56253720848442 };

            map = new google.maps.Map(document.getElementById('map'), {
                center: initialLatLng,
                zoom: 16
            });

            map.addListener('click', function (e) {
                var lat = e.latLng.lat();
                var lng = e.latLng.lng();

                if (marker) {
                    marker.setMap(null);
                }

                var latInput = document.getElementById('latInput');
                var lngInput = document.getElementById('lngInput');

                latInput.value = lat;
                lngInput.value = lng;

                marker = new google.maps.Marker({
                    position: { lat: lat, lng: lng },
                    map: map
                });
            });
        }

    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAtBFAnDiYmJfv-JmOOJUAsTXJr307FiK8&callback=initMap" async defer></script>


    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
</div>
</body>
</html>