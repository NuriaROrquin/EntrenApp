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
    <!-- Styles -->
    <link href="css/styles.css" rel="stylesheet">

</head>

<header class="alumno-color">
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

<body>


<section class="first-section">

    <div class = "container">
        <div id="loginbox" style="" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <form:form action="registrarme" method="POST" modelAttribute="registerStudentLesson">
                <h3 class="form-signin-heading">Inscribirme a una nueva clase</h3>
                <hr class="colorgraph"><br>

                <div class="mb-3">
                    <div class="form-check">
                        <form:label path="date">Busca tu clase por fecha:</form:label>
                        <form:input path="date" type="date" id="date" class="form-control" />

                        <form:label path="professor">Busca tu clase por profesor: </form:label>
                        <form:select name="professor" path="professor" id="professor" class="form-control"  >
                            <form:option value="">Nuria</form:option>
                            <form:option value="">Pablo</form:option>
                            <form:option value="">Facundo</form:option>
                            <form:option value="">Stefania</form:option>
                            <form:option value="">Santiago</form:option>
                        </form:select>

                        <form:label path="disiplina">Busca tu clase por profesor: </form:label>
                        <form:select name="disiplina" path="disiplina" id="disiplina" class="form-control">
                            <form:option value="Karate">Karate</form:option>
                            <form:option value="Running">Running</form:option>
                            <form:option value="Yoga">Yoga</form:option>
                            <form:option value="Calistemia">Calistemia</form:option>
                            <form:option value="Gimnasia rítmica">Gimnasia rítmica</form:option>
                        </form:select>
                    </div>
                </div>

            </form:form>

                <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block btn-margin" Type="Submit"/>Registrarme</button>
        </div>
    </div>

    <div class="classes alumno-color">
        <h2>Clases Disponibles</h2>
        <div class="container-classes">
            <div class="class">
                <div class="first-container alumno-color-secundario">
                    <h4>Funcional</h4>
                    <img src=""/>
                </div>
                <div class="second-container">
                    <span class="alumno-color-font">Jueves 18</span>
                    <span class="alumno-color-font">Carlos Rodriguez</span>
                    <span class="alumno-color-font">10:30 - 11:30</span>
                </div>
            </div>
            <div class="class">
                <div class="first-container alumno-color-secundario">
                    <h4>Funcional</h4>
                    <img src=""/>
                </div>
                <div class="second-container">
                    <span class="alumno-color-font">Jueves 18</span>
                    <span class="alumno-color-font">Carlos Rodriguez</span>
                    <span class="alumno-color-font">10:30 - 11:30</span>
                </div>
            </div>
            <div class="class">
                <div class="first-container alumno-color-secundario">
                    <h4>Funcional</h4>
                    <img src=""/>
                </div>
                <div class="second-container">
                    <span class="alumno-color-font">Jueves 18</span>
                    <span class="alumno-color-font">Carlos Rodriguez</span>
                    <span class="alumno-color-font">10:30 - 11:30</span>
                </div>
            </div>
        </div>

    </div>




</section>

        <%--Bloque que es visible si el elemento error no esta vacio	--%>
        <c:if test="${not empty error}">
            <h4><span>${error}</span></h4>
            <br>|
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