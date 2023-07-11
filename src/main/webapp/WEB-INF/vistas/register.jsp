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
    <link href="css/styles.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@200;300;400;500;600;700;800&display=swap"
          rel="stylesheet">
</head>
<body>
<div class="container-register">
    <div class="container-img">
        <img src="/assets/exercise.jpeg"/>
    </div>
    <div class="register">
        <div class="container-form">
            <h1>¡Entrenemos!</h1>
            <form:form action="register-validate" method="POST" modelAttribute="register" cssClass="form">
                <div>
                    <form:label path="email">Correo electronico:</form:label>
                    <form:input path="email" id="email" class="form-control"/>
                </div>
                <div>
                    <form:label path="password">Contrasena:</form:label>
                    <form:input path="password" type="password" id="password" class="form-control"/>
                </div>
                <div>
                    <form:label path="verificatedPassword">Confirmar contraseña:</form:label>
                    <form:input path="verificatedPassword" type="password" id="verificatedPassword"
                                class="form-control"/>
                </div>
                <div>
                    <form:label path="role">Seleccioná tu rol:</form:label>
                    <form:select name="role" path="role" id="role" class="form-control">
                        <form:option value="2">Alumno</form:option>
                        <form:option value="3">Profesor</form:option>
                    </form:select>
                </div>
                <c:if test="${not empty error}">
                    <div class="container-error">
                        <span class="error">
                            <img src="/assets/exclamation.png" style="width: 2rem; margin-bottom: 0.5rem;"/>
                                ${error}
                        </span>
                    </div>
                </c:if>
                <button class="profesor-color-secundario button" type="Submit"/>
                Registrarme
                </button>
            </form:form>
            <div class="container-link">
                <a href="/">
                    Tengo una cuenta
                    <img src="/assets/arrow-right-blue.png"
                         style="width: 1.5rem"/>
                </a>
            </div>
        </div>


    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>