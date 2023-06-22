package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Basic;
import javax.persistence.criteria.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Assert;
import java.util.List;

public class LessonRepositoryTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void whenINeedAClassListShouldShowMeClassListReferToAlumno() {
        //rol Alumno
        Rol rolAlumno = new Rol();
        rolAlumno.setIdRole(2);

        session().save(rolAlumno);


        //rol Profesor
        Rol rolProfesor = new Rol();
        rolProfesor.setIdRole(3);

        session().save(rolProfesor);


        //alumno
        Usuario alumno = new Usuario();
        alumno.setId(2L);
        alumno.setRol(rolAlumno);
        alumno.setName("Pablo");

        session().save(alumno);


        //profesor
        Usuario profesor = new Usuario();
        profesor.setId(3L);
        profesor.setRol(rolProfesor);
        profesor.setName("Santi");

        session().save(profesor);


        //disciplina
        Disciplina disciplina = new Disciplina();
        disciplina.setName("Crossfit");

        session().save(disciplina);


        //detalle
        Detalle detail = new Detalle();
        detail.setStartHour(LocalTime.of(8, 00));
        detail.setEndHour(LocalTime.of(9, 00));

        session().save(detail);


        //clase
        Clase clase = new Clase();
        clase.setIdClass(1);
        clase.setDiscipline(disciplina);
        clase.setDate(new Date(2023, 06, 24));
        clase.setDetail(detail);
        clase.setProfesor(profesor);

        session().save(clase);


        //userclass
        AlumnoClase alumnoClaseAlumno = new AlumnoClase();
        alumnoClaseAlumno.setUser(alumno);
        alumnoClaseAlumno.setLesson(clase);
        session().save(alumnoClaseAlumno);

        AlumnoClase alumnoClaseProfesor = new AlumnoClase();
        alumnoClaseProfesor.setUser(profesor);
        alumnoClaseProfesor.setLesson(clase);
        session().save(alumnoClaseProfesor);


        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<AlumnoClase> criteriaQuery = criteriaBuilder.createQuery(AlumnoClase.class);
        Root<AlumnoClase> usuarioClaseRoot = criteriaQuery.from(AlumnoClase.class);
        Join<AlumnoClase, Clase> claseJoin = usuarioClaseRoot.join("lesson");
        Join<AlumnoClase, Usuario> alumnoJoin = usuarioClaseRoot.join("user");
        Join<Clase, Usuario> profesorJoin = claseJoin.join("professor");

        criteriaQuery.select(usuarioClaseRoot);

        List<AlumnoClase> lessons = session().createQuery(criteriaQuery).getResultList();

        assertThat(lessons).isNotEmpty();
        assertThat(lessons).isNotNull();
        assertThat(lessons).extracting("lesson").contains(clase);
        assertThat(lessons).extracting("user").contains(alumno);
        assertThat(lessons).extracting("user").contains(profesor);

    }

    @Test
    @Transactional
    @Rollback

    public void whenINeedAClassListShouldShowMeAllTheClassesReferToProfessor() {

        //rol Alumno
        Rol rolAlumno = new Rol();
        rolAlumno.setIdRole(2);

        session().save(rolAlumno);

        //alumno
        Usuario alumno = new Usuario();
        alumno.setId(2L);
        alumno.setRol(rolAlumno);
        alumno.setName("Pablo");

        session().save(alumno);


        //rol Profesor
        Rol rolProfesor = new Rol();
        rolProfesor.setIdRole(3);

        session().save(rolProfesor);

        //profesor
        Usuario profesor = new Usuario();
        profesor.setId(3L);
        profesor.setRol(rolProfesor);
        profesor.setName("Santi");
        session().save(profesor);

        //disciplina
        Disciplina disciplina = new Disciplina();
        disciplina.setName("Crossfit");
        session().save(disciplina);


        //detalle
        Detalle detail = new Detalle();
        detail.setStartHour(LocalTime.of(8, 00));
        detail.setEndHour(LocalTime.of(9, 00));
        session().save(detail);


        //clase
        Clase clase = new Clase();
        clase.setIdClass(1);
        clase.setDiscipline(disciplina);
        clase.setDate(new Date(2023, 06, 24));
        clase.setDetail(detail);
        clase.setProfesor(profesor);
        session().save(clase);

        //clase
        Clase clase2 = new Clase();
        clase2.setIdClass(2);
        clase2.setDiscipline(disciplina);
        clase2.setDate(new Date(2024, 06, 24));
        clase2.setDetail(detail);
        clase2.setProfesor(profesor);
        session().save(clase2);


        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> ClaseRoot = criteriaQuery.from(Clase.class);

        Join<Clase, Usuario> profesorJoin = ClaseRoot.join("professor");
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(profesorJoin.get("rol"), 3));
        criteriaQuery.select(ClaseRoot);

        List<Clase> lessons = session().createQuery(criteriaQuery).getResultList();


        assertThat(lessons).isNotEmpty();
        assertThat(lessons).isNotNull();
        assertThat(lessons).extracting("idClass").contains(clase.getIdClass());
        assertThat(lessons).extracting("professor").contains(profesor);


    }

    public void whenINeedALessonShouldShowAllLessonsWithStateFinishedReferToProfessor(){
        BasicData dataRole = new BasicData();
        Rol role = dataRole.createRole(1L, "profesor");

        BasicData dataUser = new BasicData();
        Usuario professor = dataUser.createUser(1L, "pablo@hotmail.com", "1234","Pablo", role, true);

        // Lugar
        BasicData dataPlace = new BasicData();
        Lugar place = dataPlace.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");

        // Dificultad
        BasicData dataDifficulty = new BasicData();
        Dificultad difficulty = dataDifficulty.createDifficulty(1L, "Avanzado");


        // Disciplina
        BasicData dataDiscipline = new BasicData();
        Disciplina discipline = dataDiscipline.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);


        // Detalle
        BasicData dataDetail = new BasicData();
        BasicData detailStartHour = new BasicData();
        BasicData detailEndHour = new BasicData();
        LocalTime startTime = detailStartHour.setHourMinutes(2,30);
        LocalTime endTime = detailEndHour.setHourMinutes(4,00);
        Detalle detail = dataDetail.createDetail(1L,startTime,endTime,50 );

        // Estado
        BasicData dataState = new BasicData();
        Estado state = dataState.createState(1L,"Finalizada");

        // Estado 2
        BasicData dataState2 = new BasicData();
        Estado state2 = dataState2.createState(2L,"Cancelada");

        // Clase 1
        BasicData dataLesson = new BasicData();
        Clase lesson = dataLesson.createClase(1,new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);

        // Clase 2
        BasicData dataLesson2 = new BasicData();
        Clase lesson2 = dataLesson2.createClase(1,new Date(2023,11,10), new Date(2023,11,10),new Date(2024,05,30), detail, place, difficulty, discipline, professor, state);

        // Clase 3
        BasicData dataLesson3 = new BasicData();
        Clase lesson3 = dataLesson.createClase(1,new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state2);

        List<Clase> finishedLessons = new ArrayList<>();
        finishedLessons.add(lesson);
        finishedLessons.add(lesson2);
        finishedLessons.add(lesson3);


        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> ClaseRoot = criteriaQuery.from(Clase.class);
        Join<Clase, Usuario> profesorJoin = ClaseRoot.join("professor");
        //Join<Clase, Estado> estadoJoin = ClaseRoot.join("state");
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(profesorJoin.get("id"), 1));
        criteriaQuery.where(predicate);
        criteriaQuery.select(ClaseRoot);

        List<Clase> lessons = session().createQuery(criteriaQuery).getResultList();

        // criteriaBuilder.equal(estadoJoin.get("idState"),1)



        /*SELECT *
        FROM clase c join usuario u on c.professor_id = u.id join estado e on c.state_id_estado = e.id_estado
        WHERE u.id = 3 and e.id_estado = 3*/

        assertThat(lessons).isNotNull();
        assertThat(lessons).isNotEmpty();
        //assertThat(lessons).hasSize(3);
        //Assert.assertEquals(1, lessons.size());
/*
        assertThat(lessons).extracting("idClass").contains(clase.getIdClass());
        assertThat(lessons).extracting("professor").contains(profesor);*/




    }

}