<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<div class = "container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <form:form action="registrarme" method="POST" modelAttribute="registerLessonProfessor">
            <h3 class="form-signin-heading">Nueva Clase</h3>
            <hr class="colorgraph"><br>



            <form:label path="date">Fecha:</form:label>
            <form:input path="date" type="date" id="date" class="form-control" />

            <form:label path="capacity">Capacidad: </form:label>
            <form:input path="capacity" type="capacity" id="capacity" class="form-control" />

            <form:label path="hour_ini">Hora Inicio: </form:label>
            <form:input path="hour_ini" type="houri" id="hour_ini" class="form-control" />

             <form:label path="hour_fin">Hora Fin: </form:label>
             <form:input path="hour_fin" type="hour_fin" id="hour_fin-" class="form-control" />

             <form:label path="name">Nombre: </form:label>
             <form:input path="name" type="name" id="name" class="form-control" />

            <form:label path="age_max">Edad maxima: </form:label>
            <form:input path="age_max" type="age_max" id="age_max" class="form-control" />

            <form:label path="age_min">Edad minima: </form:label>
            <form:input path="age_min" type="age_min" id="age_min" class="form-control" />


            <form:select name="difficulty" path="difficulty" id="difficulty" class="form-control">
                 <form:option value="Principiante">Principiante</form:option>
                 <form:option value="Facil">Facil</form:option>
                 <form:option value="Mediano">Mediano</form:option>
                 <form:option value="Dificil">Dificil</form:option>
                 <form:option value="Avanzado">Avanzado</form:option>
             </form:select>


             <textarea name="description" rows="5" cols="10" class="form-control">


             </textarea>

             <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block" Type="Submit"/>Registrarme</button>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>