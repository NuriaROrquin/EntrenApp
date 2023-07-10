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
<body class="natacion">
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
                                <a href="/lessons"><span>Clases</span></a>
                                <ul class="dropdown arrow-top">
                                    <li><a href="/lessonsByState?idState=1">Pendientes</a></li>
                                    <li><a href="/lessonsByState?idState=2">En curso</a></li>
                                    <li><a href="/lessonsByState?idState=3">Finalizadas</a></li>
                                    <li><a href="/lessonsByState?idState=4">Canceladas</a></li>
                                </ul>
                            </li>
                            <li><a href="/lessonsByState?idState=3"><span>Calificar</span></a></li>
                            <li><a href="availableLessons"><span>Anotate</span></a></li>
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

    <section class="first-section">
        <div class="container">
            <div class="class alumno-color">
                <h1>¡Reservá tu próxima clase!</h1>
                <span>No te quedes afuera, ¿Qué esperás para sentirte mejor?</span>
                <a href="availableLessons" class="button alumno-color-secundario">Reservá</a>
            </div>
            <div class="qualification glass">
                <h2>Tus calificaciones</h2>
                <span>Las puntuaciones mas altas que dejaste</span>
                <div class="container">
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
        <div class="classes glass">
            <h2>Clases Reservadas</h2>
            <div class="container-classes">
                <div class="class">
                    <div class="first-container profesor-color-secundario">
                        <h4>Funcional</h4>
                        <img src=""/>
                    </div>
                    <div class="second-container">
                        <span class="profesor-color-font">Jueves 18</span>
                        <span class="profesor-color-font">Carlos Rodriguez</span>
                        <span class="profesor-color-font">10:30 - 11:30</span>
                    </div>
                </div>
                <div class="class">
                    <div class="first-container profesor-color-secundario">
                        <h4>Funcional</h4>
                        <img src=""/>
                    </div>
                    <div class="second-container">
                        <span class="profesor-color-font">Jueves 18</span>
                        <span class="profesor-color-font">Carlos Rodriguez</span>
                        <span class="profesor-color-font">10:30 - 11:30</span>
                    </div>
                </div>
                <div class="class">
                    <div class="first-container profesor-color-secundario">
                        <h4>Funcional</h4>
                        <img src=""/>
                    </div>
                    <div class="second-container">
                        <span class="profesor-color-font">Jueves 18</span>
                        <span class="profesor-color-font">Carlos Rodriguez</span>
                        <span class="profesor-color-font">10:30 - 11:30</span>
                    </div>
                </div>
            </div>
        </div>
    </section>

<section class="second-section">
    <div class="classes alumno-color">
        <h2>Calificá las clases finalizadas</h2>
        <div class="container-classes">
            <div class="class">
                <div class="first-container glass">
                    <h4>Funcional</h4>
                    <img src=""/>
                </div>
                <div class="second-container">
                    <span class="profesor-color-font">Jueves 18</span>
                    <span class="profesor-color-font">Carlos Rodriguez</span>
                    <span class="profesor-color-font">10:30 - 11:30</span>
                </div>
            </div>
            <div class="class">
                <div class="first-container glass">
                    <h4>Funcional</h4>
                    <img src=""/>
                </div>
                <div class="second-container">
                    <span class="profesor-color-font">Jueves 18</span>
                    <span class="profesor-color-font">Carlos Rodriguez</span>
                    <span class="profesor-color-font">10:30 - 11:30</span>
                </div>
            </div>
            <div class="class">
                <div class="first-container glass">
                    <h4>Funcional</h4>
                    <img src=""/>
                </div>
                <div class="second-container">
                    <span class="profesor-color-font">Jueves 18</span>
                    <span class="profesor-color-font">Carlos Rodriguez</span>
                    <span class="profesor-color-font">10:30 - 11:30</span>
                </div>
            </div>
        </div>
        <a href="lessonsByState?idState=3" class="button alumno-color-secundario">Calificá</a>
    </div>
</section>


    <footer>
        <p>Derechos de autor &copy; 2023 | Mi Página de Inicio</p>
    </footer>

    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
</div>
</body>
</html>