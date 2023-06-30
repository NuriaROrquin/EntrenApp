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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class LessonRepositoryTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void whenINeedAClassListShouldShowMeClassListReferToAlumno() {
        BasicData data = new BasicData();
        Rol rolAlumno = data.createRole(2L, "alumno");
        Rol rolProfesor = data.createRole(3L, "profesor");
        Usuario alumno = data.createUser(2L, "alumno@unlam.com", "1234", "Juan", rolAlumno,true);
        Usuario profesor = data.createUser(3L, "profesor@unlam.com", "1234","Santiago", rolProfesor,true);
        Disciplina disciplina = data.createDiscipline(1L,"Crossfit","Entrena tu cuerpo al maximo", 18, 50);
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        Lugar place = data.createPlace(1L,90,69,"Club Argentinos del Oeste", "Social Club");
        Dificultad difficulty = data.createDifficulty(1L,"Avanzado");
        Estado state = data.createState(1L, "Pendiente");
        Clase lesson = data.createClase(new Date(2023,12,30), new Date(2023,10,20),null, detail,place,difficulty,disciplina,profesor,state);
        AlumnoClase studentLesson = data.createAlumnoClase(1L,alumno,lesson);

        session().save(rolProfesor);
        session().save(rolAlumno);
        session().save(alumno);
        session().save(profesor);
        session().save(disciplina);
        session().save(detail);
        session().save(place);
        session().save(difficulty);
        session().save(state);
        session().save(lesson);
        session().save(studentLesson);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<AlumnoClase> criteriaQuery = criteriaBuilder.createQuery(AlumnoClase.class);
        Root<AlumnoClase> usuarioClaseRoot = criteriaQuery.from(AlumnoClase.class);
        Join<AlumnoClase, Clase> claseJoin = usuarioClaseRoot.join("lesson");
        Join<AlumnoClase, Usuario> alumnoJoin = usuarioClaseRoot.join("user");
        Join<Clase, Usuario> profesorJoin = claseJoin.join("professor");

        Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(alumnoJoin.get("id"), alumno.getId()));
        criteriaQuery.where(predicate);
        criteriaQuery.select(usuarioClaseRoot);

        List<AlumnoClase> lessons = session().createQuery(criteriaQuery).getResultList();

        assertThat(lessons).isNotEmpty();
        assertThat(lessons).isNotNull();
        assertThat(lessons).extracting("lesson").contains(lesson);
        assertThat(lessons).extracting("user").contains(alumno);
        assertThat(lessons).extracting("lesson").extracting("professor").contains(profesor);

    }

    @Test
    @Transactional
    @Rollback
    public void whenINeedAClassListShouldShowMeAllTheClassesReferToProfessor() {
        BasicData data = new BasicData();
        Rol professorRole = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234","Pablo", professorRole, true);
        Usuario professor2 = data.createUser(2L, "pablo2@hotmail.com", "1234","Juan", professorRole, true);
        Lugar place = data.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        Estado state = data.createState(1L,"Finalizada");
        Estado state2 = data.createState(2L,"Cancelada");
        Clase lesson = data.createClase(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);
        Clase lesson2 = data.createClase(new Date(2023,11,10), new Date(2023,11,10),new Date(2024,05,30), detail, place, difficulty, discipline, professor, state);
        Clase lesson3 = data.createClase(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor2, state2);

        session().save(professorRole);
        session().save(professor);
        session().save(professor2);
        session().save(place);
        session().save(difficulty);
        session().save(discipline);
        session().save(detail);
        session().save(state);
        session().save(state2);
        session().save(lesson);
        session().save(lesson2);
        session().save(lesson3);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> ClaseRoot = criteriaQuery.from(Clase.class);

        Join<Clase, Usuario> profesorJoin = ClaseRoot.join("professor");
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(profesorJoin.get("email"), professor.getEmail()));
        criteriaQuery.where(predicate);
        criteriaQuery.select(ClaseRoot);

        List<Clase> lessons = session().createQuery(criteriaQuery).getResultList();


        assertThat(lessons).isNotEmpty();
        assertThat(lessons).isNotNull();
        assertThat(lessons).extracting("idClass").contains(lesson.getIdClass());
        assertThat(lessons).extracting("professor").contains(professor);
        assertThat(lessons).extracting("professor").doesNotContain(professor2);


    }

    @Test
    @Transactional
    @Rollback
    public void whenINeedALessonShouldShowAllLessonsWithStateFinishedReferToProfessor(){
        BasicData data = new BasicData();
        Rol role = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234","Pablo", role, true);
        Lugar place = data.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        Estado state = data.createState(1L,"Finalizada");
        Estado state2 = data.createState(2L,"Cancelada");
        Clase lesson = data.createClase(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);
        Clase lesson2 = data.createClase(new Date(2023,11,10), new Date(2023,11,10),new Date(2024,05,30), detail, place, difficulty, discipline, professor, state);
        Clase lesson3 = data.createClase(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state2);

        session().save(role);
        session().save(professor);
        session().save(place);
        session().save(difficulty);
        session().save(discipline);
        session().save(detail);
        session().save(state);
        session().save(state2);
        session().save(lesson);
        session().save(lesson2);
        session().save(lesson3);


        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> ClaseRoot = criteriaQuery.from(Clase.class);

        Join<Clase, Usuario> profesorJoin = ClaseRoot.join("professor");
        Join<Clase, Estado> estadoJoin = ClaseRoot.join("state");
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(profesorJoin.get("email"), professor.getEmail()),
                criteriaBuilder.equal(estadoJoin.get("description"),state.getDescription() )
        );
        criteriaQuery.where(predicate);
        criteriaQuery.select(ClaseRoot);

        List<Clase> lessons = session().createQuery(criteriaQuery).getResultList();

        assertThat(lessons).isNotNull();
        assertThat(lessons).isNotEmpty();
        assertThat(lessons).hasSize(2);
        assertThat(lessons).extracting("idClass").contains(lesson.getIdClass());
        assertThat(lessons).extracting("professor").contains(professor);
        assertThat(lessons).extracting("state").contains(state);
        assertThat(lessons).extracting("state").doesNotContain(state2);

    }

    @Test
    @Transactional
    @Rollback
    public void whenIDropALessonByProfessorShouldSetItInStateCanceladaAndSetFechaBaja(){

        BasicData data = new BasicData();
        Rol role = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234","Pablo", role, true);
        Lugar place = data.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        Estado state = data.createState(1L,"Pendiente");
        Estado state4 = data.createState(4L,"CANCELADA");
        Date finalDate = new Date();
        Clase lesson = data.createClase(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);

        session().save(role);
        session().save(professor);
        session().save(place);
        session().save(difficulty);
        session().save(discipline);
        session().save(detail);
        session().save(state);
        session().save(lesson);
        session().save(state4);

        // Traigo la clase
        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> ClaseRoot = criteriaQuery.from(Clase.class);
        Join<Clase, Usuario> profesorJoin = ClaseRoot.join("professor");
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(profesorJoin.get("id"), professor.getId()),criteriaBuilder.equal(ClaseRoot.get("idClass"),lesson.getIdClass()));
        criteriaQuery.where(predicate);
        criteriaQuery.select(ClaseRoot);
        TypedQuery<Clase> typedQuery = session().createQuery(criteriaQuery); // lo agrego para limitar el largo del resultado
        typedQuery.setMaxResults(1); // especifico el largo
        Clase lessonResult = typedQuery.getSingleResult();

        // Traigo el estado
        CriteriaBuilder criteriaBuilder2 = session().getCriteriaBuilder();
        CriteriaQuery<Estado> criteriaQuery2 = criteriaBuilder2.createQuery(Estado.class);
        Root<Estado> EstadoRoot = criteriaQuery2.from(Estado.class);
        Predicate predicate2 = criteriaBuilder2.and(criteriaBuilder2.equal(EstadoRoot.get("description"),"CANCELADA"));
        criteriaQuery2.where(predicate2);
        criteriaQuery2.select(EstadoRoot);
        TypedQuery<Estado> typedQuery2 = session().createQuery(criteriaQuery2);
        typedQuery2.setMaxResults(1);
        Estado stateResult = typedQuery2.getSingleResult();

        lessonResult.setState(stateResult);
        lessonResult.setClosingDate(finalDate);
        session().save(lessonResult);

        assertThat(lessonResult).isNotNull();
        assertThat(lessonResult).extracting("idClass").contains(lesson.getIdClass());
        assertThat(lessonResult).extracting("state").contains(state4);
        assertThat(lessonResult).extracting("closingDate").contains(finalDate);


    }
}