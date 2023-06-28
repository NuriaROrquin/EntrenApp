<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="Content-Language" content="es">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap"
        rel="stylesheet">
  <!-- Bootstrap core CSS -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- Bootstrap theme -->
  <link href="css/bootstrap-theme.min.css" rel="stylesheet">
  <!-- Styles -->
  <link href="css/styles.css" rel="stylesheet">
</head>
<body>
<header class="class alumno-color">
  <h1>Bienvenido Juan!</h1>
  <img src="assets/profile.jpg">
</header>

<main>
  <div class="container">
    <h2>Buscar Clase</h2>
    <p>Podras buscar por profesor, disiplina o lugar. Encontra lo mejor para vos</p>
    <input class="form-control" id="myInput" type="text" placeholder="Search..">
    <br>
    <table class="table table-bordered table-striped">
      <thead>
      <tr>
        <th>Profesor</th>
        <th>Disciplina</th>
        <th>Lugar</th>
        <th>Fecha</th>
        <th>Hora de Inicio</th>
        <th>Hora de Fin</th>
        <th>Dificultad</th>
        <th>Anotarme</th>
      </tr>
      </thead>
      <tbody id="myTable">
      <tr>
        <td>John</td>
        <td>Doe</td>
        <td>john@example.com</td>
        <td>john@example.com</td>
        <td></td>
        <td></td>
        <td></td>
        <td>
          <button type="button" class="btn btn-primary btn-sm">Tomar Clase</button>
        </td>
      </tr>
      <tr>
        <td>Mary</td>
        <td>Moe</td>
        <td>mary@mail.com</td>
        <td>mary@mail.com</td>
        <td></td>
        <td></td>
        <td></td>
        <td>
          <button type="button" class="btn btn-primary btn-sm">Tomar Clase</button>
        </td>
      </tr>
      <tr>
        <td>July</td>
        <td>Dooley</td>
        <td>july@greatstuff.com</td>
        <td>july@greatstuff.com</td>
        <td></td>
        <td></td>
        <td></td>
        <td>
          <button type="button" class="btn btn-primary btn-sm">Tomar Clase</button>
        </td>
      </tr>
      <tr>
        <td>Anja</td>
        <td>Ravendale</td>
        <td>a_r@test.com</td>
        <td>a_r@test.com</td>
        <td></td>
        <td></td>
        <td></td>
        <td>
          <button type="button" class="btn btn-primary btn-sm">Tomar Clase</button>
        </td>
      </tr>
      </tbody>
    </table>

  </div>

</main>

<footer>
  <p>Derechos de autor &copy; 2023 | Mi Página de Inicio</p>
</footer>

</body>
</html>