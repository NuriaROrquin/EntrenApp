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
        <form:form action="registrarme" method="POST" modelAttribute="register">
            <h3 class="form-signin-heading">Nuevo Usuario</h3>
            <hr class="colorgraph"><br>

            <form:label path="email">Correo electronico:</form:label>
            <form:input path="email" id="email" class="form-control" />

            <form:label path="password">Contrasena:</form:label>
            <form:input path="password" type="password" id="password" class="form-control" />

            <form:label path="verificatedPassword">Confirmar contrasena:</form:label>
            <form:input path="verificatedPassword" type="password" id="verificatedPassword" class="form-control" />

            <form:select name="role" path="role" id="role" class="form-control">
                <form:option value="Alumno">Alumno</form:option>
                <form:option value="Profesor">Profesor</form:option>
            </form:select>

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