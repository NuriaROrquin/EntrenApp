<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
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

            <form:label path="dateStr">Fecha:</form:label>
            <form:input path="dateStr" type="date" id="date" class="form-control"/>

            <form:label path="capacity">Capacidad: </form:label>
            <form:input path="capacity" type="number" id="capacity" class="form-control"/>

            <form:label path="hour_iniString">Hora Inicio: </form:label>
            <form:input path="hour_iniString" type="time" id="hour_ini" class="form-control"/>

            <form:label path="hour_finString">Hora Fin: </form:label>
            <form:input path="hour_finString" type="time" id="hour_fin" class="form-control"/>

            <form:label path="name">Nombre: </form:label>
            <form:input path="name" type="text" id="name" class="form-control"/>

            <form:label path="age_max">Edad maxima: </form:label>
            <form:input path="age_max" type="number" id="age_max" class="form-control"/>

            <form:label path="age_min">Edad minima: </form:label>
            <form:input path="age_min" type="number" id="age_min" class="form-control"/>

            <form:label path="idDifficulty">Seleccionar dificultad de la clase</form:label>
            <form:select path="idDifficulty" id="idDifficulty" class="form-control">
                <c:forEach items="${dificulties}" var="dificulties">
                    <form:option value="${dificulties.idDifficulty}">${dificulties.description}</form:option>
                </c:forEach>
            </form:select>

            <form:label path="idDiscipline">Seleccionar disciplina de la clase</form:label>
            <form:select path="idDiscipline" id="idDiscipline" class="form-control">
                <c:forEach items="${disciplines}" var="disciplines">
                    <form:option value="${disciplines.idDiscipline}">${disciplines.name}</form:option>
                </c:forEach>
            </form:select>

            <form:label path="idLugar">Seleccionar lugar de la clase</form:label>
            <form:select path="idLugar" id="idLugar" class="form-control">
                <c:forEach items="${places}" var="places">
                    <form:option value="${places.idPlace}">${places.name}</form:option>
                </c:forEach>
            </form:select>

            <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block" type="Submit"/>Publicar</button>
        </form:form>

        <%--Bloque que es visible si el elemento error no esta vacio	--%>
        <c:if test="${not empty error}">
            <h4><span>${error}</span></h4>
            <br>
        </c:if>
        ${msg}
    </div>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>