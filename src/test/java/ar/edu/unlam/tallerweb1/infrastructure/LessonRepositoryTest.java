package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    /*@Test
    @Transactional
    @Rollback
    public void whenINeedToKnowAllTheAvailableClassesToSingInAsStudent() {

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

        Rol role = new Rol();
        role.setDescription("professor");
        role.setIdRole(3L);

        Usuario professor = new Usuario();
        professor.setId(1L);
        professor.setEmail("pabloantunez@mail.com");
        professor.setRol(role);
        professor.setPassword("1234");

        // Lugar
        BasicData dataPlace = new BasicData();
        Lugar place = dataPlace.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        session().save(place);

        // Dificultad
        BasicData dataDifficulty = new BasicData();
        Dificultad difficulty = dataDifficulty.createDifficulty(1L, "Avanzado");
        session().save(difficulty);

        // Disciplina
        BasicData dataDiscipline = new BasicData();
        Disciplina discipline = dataDiscipline.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        session().save(discipline);


        // Detalle
        BasicData dataDetail = new BasicData();
        BasicData detailStartHour = new BasicData();
        BasicData detailEndHour = new BasicData();
        LocalTime startTime = detailStartHour.setHourMinutes(2,30);
        LocalTime endTime = detailEndHour.setHourMinutes(4,00);
        Detalle detail = dataDetail.createDetail(1L,startTime,endTime,50 );
        session().save(detail);

        // Clase 1
        BasicData dataLesson = new BasicData();
        Clase lesson = dataLesson.createClase(1,new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor);
        session().save(lesson);

        // Clase 2
        BasicData dataLesson2 = new BasicData();
        Clase lesson2 = dataLesson2.createClase(1,new Date(2023,11,10), new Date(2023,11,10),new Date(2024,05,30), detail, place, difficulty, discipline, professor);
        session().save(lesson2);

        List<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);
        expectingLessons.add(lesson2);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> usuarioClaseRoot = criteriaQuery.from(Clase.class);
        Join<Clase, AlumnoClase> claseJoin = usuarioClaseRoot.join("lesson",JoinType.INNER);
        //Join<AlumnoClase, Usuario> alumnoJoin = usuarioClaseRoot.join("user");
        //Join<Clase, Usuario> profesorJoin = claseJoin.join("professor");

        criteriaQuery.select(usuarioClaseRoot).where(criteriaBuilder.equal(claseJoin.get("user"),alumno));

        List<Clase> lessons = session().createQuery(criteriaQuery).getResultList();

        assertThat(lessons).isNotEmpty();
        //assertThat(lessons).isNotNull();*/


}

}