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

<section class="first-section">
    <div class="container">
        <div class="class alumno-color">
            <h1>¡Reservá tu próxima clase!</h1>
            <span>No te quedes afuera, ¿Qué esperás para sentirte mejor?</span>
            <a href="availableLessons" class="button alumno-color-secundario">Reservá</a>
        </div>
        <div class="qualification alumno-color">
            <h2>Tu calificación</h2>
            <div class="container alumno-color">
                <div class="rank">
                    <img src="assets/star.png">
                    <h3>4.9</h3>
                </div>
                <div class="qualification-people">
                    <div class="person">
                        <div class="icon">
                            <img src="assets/user.png">
                        </div>
                        <div class="text">
                            <span>"Tu dedicación y participación en clase son impresionantes. Has demostrado un gran progreso en tu habilidad y conocimiento. ¡Felicitaciones por tu excelente trabajo!"</span>
                        </div>
                    </div>
                    <div class="person">
                        <div class="icon">
                            <img src="assets/user.png">
                        </div>
                        <div class="text">
                            <span>"Quiero reconocer su perseverancia y crecimiento. Ha sido un alumno ejemplar en todos los aspectos. ¡Sigue superándote y alcanzarás grandes logros!"</span>
                        </div>
                    </div>
                    <div class="person">
                        <div class="icon">
                            <img src="assets/user.png">
                        </div>
                        <div class="text">
                            <span>"Quiero reconocer su perseverancia y crecimiento. Ha sido un alumno ejemplar en todos los aspectos. ¡Sigue superándote y alcanzarás grandes logros!"</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="classes alumno-color">
        <h2>Clases Reservadas</h2>
        <div class="container-classes">
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
            </div>
        </div>
    </div>
</section>


<footer>
    <p>Derechos de autor &copy; 2023 | Mi Página de Inicio</p>
</footer>

<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>