<%@ page import="ar.edu.unlam.tallerweb1.delivery.models.DataLesson" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $.ajax({
                url: '/lessonsByState',
                type: 'GET',
                data: {idState: 1},
                success: function (response) {
                    var $response = $(response);

                    // Encuentra todos los elementos <tr> dentro del objeto jQuery
                    var $rows = $response.find('tr');

                    let lessonsArray = [];

                    // Itera sobre cada fila encontrada
                    $rows.each(function () {
                        // Accede a los datos de cada columna dentro de la fila
                        var $columns = $(this).find('td');

                        // Accede a los valores individuales de cada columna
                        var date = $columns.eq(0).text();
                        var place = $columns.eq(1).text();
                        var activity = $columns.eq(2).text();
                        var startHour = $columns.eq(4).text();
                        var endHour = $columns.eq(5).text();

                        const lesson = {
                            date: date,
                            place: place,
                            activity: activity,
                            startHour: startHour,
                            endHour: endHour
                        };

                        lessonsArray.push(lesson);
                    });

                    console.log(lessonsArray)

                },
                error: function (xhr, status, error) {
                    console.error(error);
                }
            });

            console.log(${average} ${califications})
        });
    </script>

    <section class="first-section professor">
        <div class="container">
            <div class="class alumno-color">
                <h1>¡Cargá tus clases!</h1>
                <span>Cuanta mas anticipación las cargues, mas alumnos tendrás.</span>
                <a href="register-lesson" class="button alumno-color-secundario">Cargá</a>
            </div>

            <div class="qualification glass">
                <c:if test="${fn:length(califications) == 0 || califications == null}">
                    <div class="no-data">
                        <span class="title">No se encontraron calificaciones</span>
                        <span class="description">Aún no calificaron ninguna de tus clases.</span>
                    </div>
                </c:if>
                <c:if test="${fn:length(califications) > 0 && average != 0}">
                <h2>Calificaciones recibidas</h2>
                <span>¡Estos son algunos de los comentarios de tus alumnos!</span>
                <div class="container" style="justify-content: flex-start;">
                    <div class="rank">
                        <img src="assets/star.png">
                        <fmt:formatNumber value="${average}" pattern="#0.00" var="doubleRedondeado" />
                        <h3>${doubleRedondeado}</h3>
                    </div>
                    <div class="qualification-people">
                        <c:forEach items="${califications}" var="calification">
                            <div class="person">
                                <div class="icon">
                                    <img src="assets/star.png">
                                    <span class="score">${calification.score}</span>
                                </div>
                                <div class="text" id="${calification.idCalification}">
                                    <span style="font-weight: 600">${calification.user.name}</span>
                                    <span style="font-weight: 500">Clase dictada: ${calification.lesson.name} - ${calification.lesson.discipline.description}</span>
                                    <span style="font-weight: 400">Comentario: ${calification.description}</span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                </c:if>
            </div>
        </div>
        <div class="classes glass">
            <h2>Mis Próximas Clases</h2>
            <div id="content-classes" class="container-classes">
                <div class="class">
                    <div class="first-container profesor-color-secundario">
                        <h4>Futbol 5</h4>
                    </div>
                    <div class="second-container">
                        <div class="container-span"><span class="profesor-color-font">1/8</span></div>
                        <div class="container-span"><span class="profesor-color-font">15 alumnos</span></div>
                        <div class="container-span"><span class="profesor-color-font">06:00 - 07:30</span></div>
                    </div>
                </div>
                <div class="class">
                    <div class="first-container profesor-color-secundario">
                        <h4>Crossfit</h4>
                        <img src=""/>
                    </div>
                    <div class="second-container">
                        <div class="container-span"><span class="profesor-color-font">2/8</span></div>
                        <div class="container-span"><span class="profesor-color-font">10 alumnos</span></div>
                        <div class="container-span"><span class="profesor-color-font">08:00 - 09:30</span></div>
                    </div>
                </div>
                <div class="class">
                    <div class="first-container profesor-color-secundario">
                        <h4>Natación</h4>
                        <img src=""/>
                    </div>
                    <div class="second-container">
                        <div class="container-span"><span class="profesor-color-font">3/8</span></div>
                        <div class="container-span"><span class="profesor-color-font">20 alumnos</span></div>
                        <div class="container-span"><span class="profesor-color-font">10:00 - 11:30</span></div>
                    </div>
                </div>
            </div>
            <a href="/lessonsByState?idState=1" class="button profesor-color-secundario"
               style="text-align: center; width: 80%;">Ver más</a>
        </div>
    </section>


    <footer>
        <p>¡Entrenemos! &copy; 2023 | Los Borbotones</p>
    </footer>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
</div>
</body>
</html>