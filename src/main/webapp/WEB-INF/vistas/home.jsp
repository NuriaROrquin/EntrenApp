<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Language" content="es">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Styles -->
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>
<header>
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
        <div class="class">
            <h1>¡Reservá tu próxima clase!</h1>
            <span>No te quedes afuera, ¿Qué esperás para sentirte mejor?</span>
            <button class="button primary">Reservá</button>
        </div>
        <div class="qualification">
            <h2>Tu calificación</h2>
            <div class="container">
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
    <div class="classes">
        <div>Clase1</div>
        <div>Clase2</div>
        <div>Clase3</div>
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