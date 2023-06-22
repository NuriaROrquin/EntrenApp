<%--
  Created by IntelliJ IDEA.
  User: leafnoise
  Date: 20/06/2023
  Time: 02:27 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<header class="profesor-color">
    <h1>Bienvenido Alberto!</h1>
    <img src="assets/profile.jpg">
</header>

<main>
    <section class="first-section">
        <div class="container">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Lugar</th>
                    <th scope="col">Disiplina</th>
                    <th scope="col">Cancelar</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>23/6/2023</td>
                    <td>Plaza Mitre</td>
                    <td>Running</td>
                    <td><button type="button" class="btn btn-primary btn-sm">X</button></td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>24/6/2023</td>
                    <td>Plaza Bomberitos</td>
                    <td>Funcional</td>
                    <td><button type="button" class="btn btn-primary btn-sm">X</button></td>
                </tr>
                <tr>
                    <th scope="row">3</th>
                    <td>27/6/2023</td>
                    <td>Indoor Training Haedo</td>
                    <td>Funcional</td>
                    <td><button type="button" class="btn btn-primary btn-sm">X</button></td>
                </tr>
                <tr>
                    <th scope="row">4</th>
                    <td>28/6/2023</td>
                    <td>Plaza Mitre</td>
                    <td>Running</td>
                    <td><button type="button" class="btn btn-primary btn-sm">X</button></td>
                </tr>
                <tr>
                    <th scope="row">5</th>
                    <td>30/6/2023</td>
                    <td>Plaza Barrio Sere</td>
                    <td>Running</td>
                    <td><button type="button" class="btn btn-primary btn-sm">X</button></td>
                </tr>
                </tbody>
            </table>
        </div>
    </section>

</main>

<footer>
    <p>Derechos de autor &copy; 2023 | Mi Página de Inicio</p>
</footer>

</body>
</html>
