# Proyecto Taller Web I - UNLaM

## Descripción

Este proyecto fue desarrollado como parte de la materia Taller Web I de la carrera Tecnicatura en Desarrollo Web de la Universidad Nacional de La Matanza (UNLaM). Se trata de una aplicación web implementada utilizando el framework Spring, Mockito para pruebas unitarias, y sigue una arquitectura MVC (Modelo-Vista-Controlador) junto con tecnología JSP (JavaServer Pages).

La finalidad de esta aplicación es permitir a los profesores ofrecer clases y a los alumnos inscribirse en ellas, proporcionando un sistema eficiente para la gestión de clases y la interacción entre profesores y estudiantes.

## Características Principales

- **Registro de Profesores**: Los profesores pueden registrarse en la plataforma proporcionando su información personal y credenciales de acceso.

- **Registro de Alumnos**: Los alumnos también pueden registrarse en la plataforma con sus datos personales y credenciales de acceso.

- **Creación de Clases**: Los profesores pueden crear clases especificando detalles como fecha, hora, duración, ubicación, y una descripción detallada de la clase.

- **Inscripción en Clases**: Los alumnos pueden buscar y ver las clases disponibles y, si lo desean, inscribirse en ellas.

- **Gestión de Clases**: Los profesores pueden ver una lista de las clases que han creado y realizar acciones como editar, cancelar o ver detalles de cada clase.

- **Interfaz Amigable**: La interfaz de usuario se ha diseñado pensando en la facilidad de uso tanto para profesores como para alumnos.

## Tecnologías Utilizadas

- **Spring**: Framework de desarrollo de aplicaciones Java.

- **Mockito**: Mocking Framework para pruebas unitarias.

- **JSP (JavaServer Pages)**: Tecnología utilizada para la creación de las vistas web.

- **Arquitectura MVC**: El proyecto sigue el patrón Modelo-Vista-Controlador para mantener una estructura organizada y separación de responsabilidades.

## Configuración y Uso

1. Cloná este repositorio en tu máquina local.

```
git clone https://github.com/tu-usuario/nombre-del-repositorio.git
```

3. Abrí el proyecto en tu IDE.

4. Configurá la base de datos según las especificaciones en `src/main/resources/sql/createdb.sql`.

5. Ejecuta la aplicación desde tu IDE con Tomcat.

6. Podés ejecutar los tests con Maven.

```
mvn test
```

8. Accede a la aplicación a través de tu navegador web en la dirección `http://localhost:8080`.

## Autor

Este proyecto fue creado por Nuria Orquin, Pablo Antunez, Facundo Fagnano y Santiago Opera como parte de la materia Taller Web I en la UNLaM.
