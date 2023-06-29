<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="clase" items="${classes}">
    <tr>
        <td><fmt:formatDate value="${clase.date}" pattern="dd-MM" /></td>
        <td>${clase.place.name}</td>
        <td>${clase.discipline.name}</td>
        <td>${clase.difficulty.description}</td>
        <td>${clase.detail.startHour}</td>
        <td>${clase.detail.endHour}</td>
        <td>${clase.detail.capacity}</td>
        <td>
            <button type="button" class="btn btn-primary btn-sm">X</button>
        </td>
    </tr>
</c:forEach>
</body>
</html>
