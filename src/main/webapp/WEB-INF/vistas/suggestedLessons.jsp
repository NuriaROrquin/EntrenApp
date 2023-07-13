<%@ page import="ar.edu.unlam.tallerweb1.delivery.models.DataLesson" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="first-section">
    <div class="container">
        <div class="container" id="suggested-by-preferences">
            <h3>Según tus preferencias, te pueden gustar: </h3>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Fecha</th>
                    <th scope="col">Dirección</th>
                    <th scope="col">Actividad</th>
                    <th scope="col">Disciplina</th>
                    <th scope="col">Hora comienzo</th>
                    <th scope="col">Hora final</th>
                    <th scope="col">Dificultad</th>
                    <th scope="col">Capacidad</th>
                    <th scope="col"></th>
                </tr>
                </thead>

                <tbody id="lessonsPreferencesContainer">
                <c:forEach var="lessonP" items="${byPreferences}">
                    <tr>
                        <td><fmt:formatDate value="${lessonP.date}" pattern="dd-MM"/></td>
                        <td><a style="color:white;" href="/lessondetail?lessonId=${lessonP.idClass}">${lessonP.name}</a>
                        </td>
                        <td>${lessonP.name}</td>
                        <td>${lessonP.discipline.description}</td>
                        <td style="text-align: center">${lessonP.detail.startHour}</td>
                        <td style="text-align: center">${lessonP.detail.endHour}</td>
                        <td>${lessonP.difficulty.description}</td>
                        <td style="text-align: center">${lessonP.detail.capacity}</td>
                        <td>
                            <button type="button" class="btn">Anotarme</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="container">
            <h3>Por las clases que tomaste, te pueden gustar:</h3>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Fecha</th>
                    <th scope="col">Dirección</th>
                    <th scope="col">Actividad</th>
                    <th scope="col">Disciplina</th>
                    <th scope="col">Hora comienzo</th>
                    <th scope="col">Hora final</th>
                    <th scope="col">Dificultad</th>
                    <th scope="col">Capacidad</th>
                    <th scope="col"></th>
                </tr>
                </thead>

                <tbody id="lessonsTakenContainer">
                <c:forEach var="lessonT" items="${byTaken}">
                    <tr>
                        <td><fmt:formatDate value="${lessonT.date}" pattern="dd-MM"/></td>
                        <td>${lessonT.place.name}</td>
                        <td><a style="color:white;" href="/lessondetail?lessonId=${lessonT.idClass}">${lessonT.name}</a>
                        </td>
                        <td>${lessonT.discipline.description}</td>
                        <td style="text-align: center">${lessonT.detail.startHour}</td>
                        <td style="text-align: center">${lessonT.detail.endHour}</td>
                        <td>${lessonT.difficulty.description}</td>
                        <td style="text-align: center">${lessonT.detail.capacity}</td>
                        <td>
                            <button type="button" class="btn">Anotarme</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>