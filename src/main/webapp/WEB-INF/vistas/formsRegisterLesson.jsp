<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset=”utf-8″>
    <meta http-equiv="Content-Language" content="es">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <form:form action="validate-lesson" method="POST" modelAttribute="registerLesson">
            <h3 class="form-signin-heading">Nueva Clase</h3>
            <hr class="colorgraph">
            <br>

            <form:label path="dateStr">Fecha: </form:label>
            <form:input path="dateStr" type="date" id="date" class="form-control"/>

            <form:label path="name">Nombre de la actividad: </form:label>
            <form:input path="name" type="text" id="name" class="form-control"/>

            <form:label path="capacity">Capacidad: </form:label>
            <form:input path="capacity" type="number" id="capacity" class="form-control"/>

            <form:label path="hour_iniString">Hora de inicio: </form:label>
            <form:input path="hour_iniString" type="time" id="hour_ini" class="form-control"/>

            <form:label path="hour_finString">Hora de fin: </form:label>
            <form:input path="hour_finString" type="time" id="hour_fin" class="form-control"/>

            <form:label path="age_max">Edad máxima: </form:label>
            <form:input path="age_max" type="number" id="age_max" class="form-control"/>

            <form:label path="age_min">Edad mínima: </form:label>
            <form:input path="age_min" type="number" id="age_min" class="form-control"/>

            <form:label path="idDifficulty">Dificultad: </form:label>
            <form:select path="idDifficulty" id="idDifficulty" class="form-control">
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

            <form:label path="idLugar">Lugar: </form:label>
            <form:select path="idLugar" id="idLugar" class="form-control">
                <c:forEach items="${places}" var="places">
                    <form:option value="${places.idPlace}">${places.name}</form:option>
                </c:forEach>
            </form:select>

            <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block" type="Submit"/>Publicar</button>
        </form:form>

        <%--Bloque que es visible si el elemento error no esta vacio	--%>
        <c:if test="${not empty preferencesSaved}">
            <h4><span>${preferencesSaved}</span></h4>
            <br>
        </c:if>
        ${msg}
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

<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>