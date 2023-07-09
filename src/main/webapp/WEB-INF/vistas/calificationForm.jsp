<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<header class="alumno-color">
    <h1>Calificá tu clase</h1>
    <img src="assets/profile.jpg">
</header>


<main>
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <form:form action="calificateLesson" method="POST" modelAttribute="dataCalification">
            <h3 class="form-signin-heading">Calificar clase</h3>
            <hr class="colorgraph">
            <br>

            <form:hidden value="${idLesson}" path="lessonId" id="lessonId" class="form-control"/>

            <form:label path="score">Seleccioná la calificacion de la clase</form:label>
            <form:select path="score" id="score" class="form-control">
                <form:option value="1">Muy Mala</form:option>
                <form:option value="2">Mala</form:option>
                <form:option value="3">Regular</form:option>
                <form:option value="4">Buena</form:option>
                <form:option value="5">Muy Buena</form:option>
            </form:select>

            <form:label path="description">Comentario: </form:label>
            <form:input path="description" type="text" id="description" class="form-control"/>
            <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block" type="Submit"/>Publicar</button>
        </form:form>

    </div>
</main>

<footer>
    <p>Derechos de autor &copy; 2023 | Mi Página de Inicio</p>
</footer>

</body>
</html>
