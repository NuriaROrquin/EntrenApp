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
<div style="min-height: 100vh; min-width: 100vw; display: flex; flex-direction: row">
    <div style="display: flex; width: 60%; height: 100%; min-height: 100vh;">
        <img style="object-fit: cover; " src="/assets/exercise.jpeg"/>
    </div>
    <div style="display: flex; width: 40%; height: 100%;min-height: 100vh; justify-content: center; align-content: center;">
        <div id="loginbox" style="width: 100%; display: flex; justify-content: center; align-content: center; flex-direction: column; flex-wrap: wrap-reverse;">
            <form:form action="login-validate" method="POST" modelAttribute="dataLogin" cssStyle="gap: 2rem;width: 60%; display: flex; flex-direction: column; justify-content: center; align-content: center">
                <form:input path="email" id="email" type="email" class="form-control"/>
                <form:input path="password" type="password" id="password" class="form-control"/>

                <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>
                Login
                </button>
            </form:form>
            <a href="register">Registrarme</a>
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
