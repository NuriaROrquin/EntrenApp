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
</head>
<body>
<header class="site-navbar alumno-color" role="banner">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-11 col-xl-2">
                <h1 class="mb-0 site-logo"><a href="index.html" class="text-white mb-0">Brand</a></h1>
            </div>
            <div class="col-12 col-md-10 d-none d-xl-block">
                <nav class="site-navigation position-relative text-right" role="navigation">
                    <ul class="site-menu js-clone-nav mr-auto d-none d-lg-block">
                        <li class="has-children">
                            <a href="/lessons"><span>Clases</span></a>
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
            <div class="d-inline-block d-xl-none ml-md-0 mr-auto py-3" style="position: relative; top: 3px;"><a href="#" class="site-menu-toggle js-menu-toggle text-white"><span class="icon-menu h3"></span></a></div>
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

    });
</script>

<section class="first-section professor">
    <div class="container">
        <div class="class profesor-color">
            <h1>¡No te olvides de cargar clases!</h1>
            <span>Cuanta mas anticipación las cargues, mas alumnos tendrás.</span>
            <a href="register-lesson" class="button profesor-color-secundario">Cargá</a>
        </div>
        <div class="qualification profesor-color">
            <h2>Calificaciones recibidas</h2>
            <span>¡Estas son las mejores clases que diste!</span>
            <div class="container profesor-color">
                <div class="qualification-people">

                    <div class="person">
                        <div class="icon">
                            <img src="assets/star.png">
                            <span class="score">4.9</span>
                        </div>
                        <div>
                            <div class="lesson">
                                <span>Natación - 24/06/2023</span>
                            </div>
                            <div class="text">
                                <span>"Estoy impresionado por la organización y estructura de esta clase. El profesor tiene un enfoque claro y bien planificado para cada clase y eso me ayuda a maximizar mi tiempo de ejercicio y obtener los mejores resultados. "</span>
                            </div>
                        </div>
                    </div>
                    <div class="person">
                        <div class="icon">
                            <img src="assets/star.png">
                            <span class="score">4.9</span>
                        </div>
                        <div>
                            <div class="lesson">
                                <span>Funcional - 03/06/2023</span>
                            </div>
                            <div class="text">
                                <span>"Esta clase es realmente interesante y desafiante"</span>
                            </div>
                        </div>
                    </div>
                    <div class="person">
                        <div class="icon">
                            <img src="assets/star.png">
                            <span class="score">4.9</span>
                        </div>
                        <div>
                            <div class="lesson">
                                <span>Funcional - 03/06/2023</span>
                            </div>
                            <div class="text">
                                <span>"El profesor demuestra un profundo conocimiento y siempre nos motiva a superar nuestros límites. Me siento inspirado y energizado después de cada clase"</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="classes profesor-color">
        <h2>Mis Próximas Clases</h2>
        <div id="content-classes" class="container-classes">
            <div class="class">
                <div class="first-container profesor-color-secundario">
                    <h4>Funcional</h4>
                </div>
                <div class="second-container">
                    <div class="container-span"><span class="profesor-color-font">18/07</span></div>
                    <div class="container-span"><span class="profesor-color-font">18 alumnos</span></div>
                    <div class="container-span"><span class="profesor-color-font">10:30 - 11:30</span></div>
                </div>
            </div>
            <div class="class">
                <div class="first-container profesor-color-secundario">
                    <h4>Crossfit</h4>
                    <img src=""/>
                </div>
                <div class="second-container">
                    <div class="container-span"><span class="profesor-color-font">19/07</span></div>
                    <div class="container-span"><span class="profesor-color-font">5 alumnos</span></div>
                    <div class="container-span"><span class="profesor-color-font">13:00 - 14:00</span></div>
                </div>
            </div>
            <div class="class">
                <div class="first-container profesor-color-secundario">
                    <h4>Natación</h4>
                    <img src=""/>
                </div>
                <div class="second-container">
                    <div class="container-span"><span class="profesor-color-font">20/07</span></div>
                    <div class="container-span"><span class="profesor-color-font">10 alumnos</span></div>
                    <div class="container-span"><span class="profesor-color-font">10:30 - 11:30</span></div>
                </div>
            </div>
        </div>
        <a href="/lessonsByState?idState=1" class="button profesor-color-secundario"
           style="text-align: center; width: 80%;">Ver más</a>
    </div>
</section>


<footer>
    <p>Derechos de autor &copy; 2023 | Mi Página de Inicio</p>
</footer>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>