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
<div class="container-login">
    <div class="container-img">
        <img src="/assets/exercise.jpeg"/>
    </div>
    <div class="login">
        <div class="container-form">
            <h1>¡Entrenemos!</h1>
            <form:form action="login-validate" method="POST" modelAttribute="dataLogin" cssClass="form">
                <form:input path="email" id="email" type="email" class="form-control"/>
                <form:input path="password" type="password" id="password" class="form-control"/>

                <button class="profesor-color-secundario" Type="Submit"/>
                Login
                </button>
            </form:form>
            <div class="container-link">
                <a href="register">
                    No tengo una cuenta
                    <img src="/assets/arrow-right-blue.png"
                         style="width: 1.5rem"/>
                </a>
            </div>
            <c:if test="${not empty error}">
                <h4><span>${error}</span></h4>
                <br>
            </c:if>
            ${msg}
        </div>
    </div>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
