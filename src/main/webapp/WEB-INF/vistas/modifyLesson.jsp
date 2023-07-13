<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Language" content="es">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- Styles -->
    <link href="css/styles.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <title>Modificar Clase</title>
</head>

<body>
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
    });
</script>

<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <form:form action="modifyLesson" method="POST" modelAttribute="lesson">
        <h3 class="form-signin-heading">Modifica tu Clase</h3>
            <hr class="colorgraph">
            <br>

            <form:hidden value="${param.lessonId}" path="lessonId" id="lessonId" class="form-control"/>

            <form:label path="date">Fecha:</form:label>
            <fmt:formatDate value="${lesson.date}" pattern="yyyy-MM-dd" var="myDate" />
            <form:input path="date" id="date" type="date" value="${myDate}" class="form-control" />

            <form:label path="name">Nombre de la actividad: </form:label>
            <form:input value="${lesson.name}" path="name" type="text" id="name" class="form-control"/>

            <form:label path="capacity">Capacidad: </form:label>
            <form:input value="${lesson.capacity}" path="capacity" type="number" id="capacity" class="form-control"/>

            <form:label path="hour_iniString">Hora Inicio: </form:label>
            <form:input value="${lesson.hour_iniString}" path="hour_iniString" type="time" id="hour_ini" class="form-control"/>

            <form:label path="hour_finString">Hora Fin: </form:label>
            <form:input value="${lesson.hour_finString}" path="hour_finString" type="time" id="hour_fin" class="form-control"/>

            <form:label path="idDifficulty">Seleccionar dificultad de la clase</form:label>
            <form:select defaultValue="${lesson.idDifficulty}" path="idDifficulty" id="idDifficulty"
                         class="form-control">
                <c:forEach items="${dificulties}" var="dificulties">
                    <form:option value="${dificulties.idDifficulty}">${dificulties.description}</form:option>
                </c:forEach>
            </form:select>

            <form:label path="idDiscipline">Disciplina: </form:label>
            <form:select path="idDiscipline" id="idDiscipline" class="form-control">

                <c:forEach items="${disciplines}" var="disciplines">
                    <form:option value="${disciplines.idDiscipline}">${disciplines.description}</form:option>
                </c:forEach>
            </form:select>

            <form:label path="idLugar">Seleccionar lugar de la clase</form:label>
            <form:select defaultValue="${lesson.idLugar}" path="idLugar" id="idLugar" class="form-control">
                <c:forEach items="${places}" var="places">
                    <form:option value="${places.idPlace}">${places.name}</form:option>
                </c:forEach>
            </form:select>

            <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block" type="Submit">
            Publicar
            </button>
        </form:form>
    </div>
</div>

<script>
    const fechaInput = document.getElementById('date');

    const fechaActual = new Date();
    const fechaMaxima = new Date(fechaActual.getFullYear(), fechaActual.getMonth() + 1, fechaActual.getDate());

    const fechaMaximaISO = fechaMaxima.toISOString().split('T')[0];
    const fechaMinimaISO = fechaActual.toISOString().split('T')[0];

    fechaInput.max = fechaMaximaISO;
    fechaInput.min = fechaMinimaISO;
</script>


</body>
</html>
