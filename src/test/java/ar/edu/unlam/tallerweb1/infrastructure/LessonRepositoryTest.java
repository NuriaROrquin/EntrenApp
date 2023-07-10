package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LessonRepositoryTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void whenICompleteTheLessonFormShouldCreateALesson() {
        BasicData data = new BasicData();
        Rol rolProfesor = data.createRole(3L, "profesor");
        Usuario profesor = data.createUser(3L, "profesor@unlam.com", "1234", "Santiago", rolProfesor, true);
        Usuario profesor2 = data.createUser(3L, "profesor@unlam.com", "1234", "Santiago", rolProfesor, true);
        Disciplina disciplina = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Lugar place = data.createPlace(1L, 90, 69, "Club Argentinos del Oeste", "Social Club");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Estado state = data.createState(1L, "Pendiente");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), null, detail, place, difficulty, disciplina, profesor, state, "Natacion", 18, 50);

        session().save(rolProfesor);
        session().save(profesor);
        session().save(disciplina);
        session().save(detail);
        session().save(place);
        session().save(difficulty);
        session().save(state);
        session().save(lesson);
        session().save(profesor2);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> claseRoot = criteriaQuery.from(Clase.class);

        List<Clase> lessons = session().createQuery(criteriaQuery).getResultList();

        assertThat(lessons).isNotNull();
        assertThat(lessons).isNotEmpty();
        assertThat(lessons).hasSize(1);
        assertThat(lessons).extracting("professor").contains(profesor);

    }

    @Test
    @Transactional
    @Rollback
    public void whenINeedAClassListShouldShowMeClassListReferToAlumno() {
        BasicData data = new BasicData();
        Rol rolAlumno = data.createRole(2L, "alumno");
        Rol rolProfesor = data.createRole(3L, "profesor");
        Usuario alumno = data.createUser(2L, "alumno@unlam.com", "1234", "Juan", rolAlumno, true);
        Usuario profesor = data.createUser(3L, "profesor@unlam.com", "1234", "Santiago", rolProfesor, true);
        Disciplina disciplina = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Lugar place = data.createPlace(1L, 90, 69, "Club Argentinos del Oeste", "Social Club");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Estado state = data.createState(1L, "Pendiente");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), null, detail, place, difficulty, disciplina, profesor, state, "Natacion", 18, 50);
        AlumnoClase studentLesson = data.createAlumnoClase(1L, alumno, lesson);

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
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", professorRole, true);
        Usuario professor2 = data.createUser(2L, "pablo2@hotmail.com", "1234", "Juan", professorRole, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Finalizada");
        Estado state2 = data.createState(2L, "Cancelada");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        Clase lesson2 = data.createLesson(new Date(2023, 11, 10), new Date(2023, 11, 10), new Date(2024, 05, 30), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        Clase lesson3 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor2, state2, "Natacion", 18, 40);

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
        Root<Clase> LessonRoot = criteriaQuery.from(Clase.class);

        Join<Clase, Usuario> professorJoin = LessonRoot.join("professor");
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(professorJoin.get("email"), professor.getEmail()));
        criteriaQuery.where(predicate);
        criteriaQuery.select(LessonRoot);

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
    public void whenINeedALessonShouldShowAllLessonsByStateFromProfessor() {
        BasicData data = new BasicData();
        Rol role = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", role, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Finalizada");
        Estado state2 = data.createState(2L, "Cancelada");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        Clase lesson2 = data.createLesson(new Date(2023, 11, 10), new Date(2023, 11, 10), new Date(2024, 05, 30), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        Clase lesson3 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state2, "Natacion", 18, 40);

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
        Root<Clase> LessonRoot = criteriaQuery.from(Clase.class);

        Join<Clase, Usuario> professorJoin = LessonRoot.join("professor");
        Join<Clase, Estado> stateJoin = LessonRoot.join("state");
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(professorJoin.get("email"), professor.getEmail()),
                criteriaBuilder.equal(stateJoin.get("description"), state.getDescription())
        );
        criteriaQuery.where(predicate);
        criteriaQuery.select(LessonRoot);

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
    public void whenIDropALessonByProfessorShouldSetItInStateCanceladaAndSetFechaBaja() {

        BasicData data = new BasicData();
        Rol role = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", role, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Pendiente");
        Estado state4 = data.createState(4L, "CANCELADA");
        Date finalDate = new Date();
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);

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
        CriteriaBuilder criteriaBuilderLesson = session().getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQueryLesson = criteriaBuilderLesson.createQuery(Clase.class);
        Root<Clase> LessonRoot = criteriaQueryLesson.from(Clase.class);
        Join<Clase, Usuario> professorJoin = LessonRoot.join("professor");
        Predicate predicateLesson = criteriaBuilderLesson.and(
                criteriaBuilderLesson.equal(professorJoin.get("id"), professor.getId()), criteriaBuilderLesson.equal(LessonRoot.get("idClass"), lesson.getIdClass()));
        criteriaQueryLesson.where(predicateLesson);
        criteriaQueryLesson.select(LessonRoot);
        TypedQuery<Clase> typedQueryLesson = session().createQuery(criteriaQueryLesson); // lo agrego para limitar el largo del resultado
        typedQueryLesson.setMaxResults(1); // especifico el largo
        Clase lessonResult = typedQueryLesson.getSingleResult();

        // Traigo el estado
        CriteriaBuilder criteriaBuilderState = session().getCriteriaBuilder();
        CriteriaQuery<Estado> criteriaQueryState = criteriaBuilderState.createQuery(Estado.class);
        Root<Estado> StateRoot = criteriaQueryState.from(Estado.class);
        Predicate predicateState = criteriaBuilderState.and(criteriaBuilderState.equal(StateRoot.get("description"), "CANCELADA"));
        criteriaQueryState.where(predicateState);
        criteriaQueryState.select(StateRoot);
        TypedQuery<Estado> typedQueryState = session().createQuery(criteriaQueryState);
        typedQueryState.setMaxResults(1);
        Estado stateResult = typedQueryState.getSingleResult();

        lessonResult.setState(stateResult);
        lessonResult.setClosingDate(finalDate);
        session().save(lessonResult);

        assertThat(lessonResult).isNotNull();
        assertThat(lessonResult).extracting("idClass").contains(lesson.getIdClass());
        assertThat(lessonResult).extracting("state").contains(state4);
        assertThat(lessonResult).extracting("closingDate").contains(finalDate);


    }

    @Test
    @Transactional
    @Rollback
    public void whenINeedALessonShouldShowAllLessonsByStateFromStudent() {
        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", roleProfessor, true);
        Rol roleStudent = data.createRole(1L, "alumno");
        Usuario student = data.createUser(1L, "nuri@hotmail.com", "1234", "Nuri", roleStudent, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Finalizada");
        Estado state2 = data.createState(2L, "Cancelada");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        Clase lesson2 = data.createLesson(new Date(2023, 11, 10), new Date(2023, 11, 10), new Date(2024, 05, 30), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        Clase lesson3 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state2, "Natacion", 18, 40);
        AlumnoClase alumnoClase = data.createAlumnoClase(1L, student, lesson);
        AlumnoClase alumnoClase3 = data.createAlumnoClase(4L, student, lesson2);
        AlumnoClase alumnoClase2 = data.createAlumnoClase(3L, student, lesson3);


        session().save(roleProfessor);
        session().save(professor);
        session().save(roleStudent);
        session().save(student);
        session().save(place);
        session().save(difficulty);
        session().save(discipline);
        session().save(detail);
        session().save(state);
        session().save(state2);
        session().save(lesson);
        session().save(lesson2);
        session().save(lesson3);
        session().save(alumnoClase);
        session().save(alumnoClase2);
        session().save(alumnoClase3);


        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<AlumnoClase> criteriaQuery = criteriaBuilder.createQuery(AlumnoClase.class);
        Root<AlumnoClase> StudentRoot = criteriaQuery.from(AlumnoClase.class);

        Join<Clase, AlumnoClase> lessonJoin = StudentRoot.join("lesson");
        Join<Usuario, AlumnoClase> userJoin = StudentRoot.join("user");
        Join<Clase, Estado> stateJoin = lessonJoin.join("state");
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(userJoin.get("email"), student.getEmail()),
                criteriaBuilder.equal(stateJoin.get("description"), state.getDescription())
        );
        criteriaQuery.where(predicate);
        criteriaQuery.select(StudentRoot);

        List<AlumnoClase> studentLessons = session().createQuery(criteriaQuery).getResultList();

        List<Clase> convertedLessons = studentLessons.stream().map(AlumnoClase::getLesson).collect(Collectors.toList());

        assertThat(convertedLessons).isNotNull();
        assertThat(convertedLessons).isNotEmpty();
        assertThat(convertedLessons).extracting("state").contains(state);
        assertThat(convertedLessons).extracting("state").doesNotContain(state2);
        assertThat(convertedLessons).hasSize(2);

    }

    @Test
    @Transactional
    @Rollback
    public void whenINeedToModifyALessonShouldShowTheLessonReferToProfessor() {
        BasicData data = new BasicData();
        Rol role = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", role, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Pendiente");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);

        session().save(role);
        session().save(professor);
        session().save(place);
        session().save(difficulty);
        session().save(discipline);
        session().save(detail);
        session().save(state);
        session().save(lesson);

        CriteriaBuilder criteriaBuilderLesson = session().getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQueryLesson = criteriaBuilderLesson.createQuery(Clase.class);
        Root<Clase> LessonRoot = criteriaQueryLesson.from(Clase.class);
        Join<Clase, Usuario> professorJoin = LessonRoot.join("professor");
        Predicate predicateLesson = criteriaBuilderLesson.and(
                criteriaBuilderLesson.equal(professorJoin.get("id"), professor.getId()), criteriaBuilderLesson.equal(LessonRoot.get("idClass"), lesson.getIdClass()));
        criteriaQueryLesson.where(predicateLesson);
        criteriaQueryLesson.select(LessonRoot);
        TypedQuery<Clase> typedQueryLesson = session().createQuery(criteriaQueryLesson);
        typedQueryLesson.setMaxResults(1);
        Clase lessonResult = typedQueryLesson.getSingleResult();

        place.setDescription("Aire Libre");
        place.setName("SportClub");
        /*place.setLatitude(2500);
        place.setLongitude(3000);*/
        difficulty.setDescription("Intermedio");
        discipline.setDescription("Exigencia Extrema");
        LocalTime newStartTime = data.setHourMinutes(14, 30);
        LocalTime newEndTime = data.setHourMinutes(16, 00);
        detail.setStartHour(newStartTime);
        detail.setEndHour(newEndTime);

        lessonResult.setDate(new Date(2024, 12, 20));
        lessonResult.setPlace(place);
        lessonResult.setDifficulty(difficulty);
        lessonResult.setDiscipline(discipline);
        lessonResult.setDetail(detail);

        assertThat(lessonResult).isNotNull();
        assertThat(lessonResult).extracting("idClass").contains(lesson.getIdClass());
        assertThat(lessonResult).extracting("place").extracting("description").contains("Aire Libre");
        assertThat(lessonResult).extracting("detail").extracting("endHour").contains(newEndTime);

    }

    @Test
    @Transactional
    @Rollback
    public void whenIWantToCalificateALessonShouldShowAllTheLessonsReferToAlumno() {
        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", roleProfessor, true);
        Rol roleStudent = data.createRole(1L, "alumno");
        Usuario student = data.createUser(1L, "nuri@hotmail.com", "1234", "Nuri", roleStudent, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Finalizada");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        AlumnoClase alumnoClase = data.createAlumnoClase(1L, student, lesson);

        String description = "La mejor clase!";
        int score = 5;

        Calificacion calification = data.createCalification(1L, description, score, student, lesson);

        session().save(roleProfessor);
        session().save(roleStudent);
        session().save(student);
        session().save(professor);
        session().save(place);
        session().save(difficulty);
        session().save(discipline);
        session().save(detail);
        session().save(state);
        session().save(lesson);
        session().save(calification);

        assertThat(calification).isNotNull();
        assertThat(calification.getDescription()).isNotEmpty();
        assertThat(calification.getDescription()).isEqualTo(description);
        assertThat(calification).extracting("user").contains(student);
        assertThat(calification).extracting("lesson").contains(lesson);

    }

    @Test
    @Transactional
    @Rollback
    public void whenINeedToKnowAllTheAvailableClassesToSingInAsStudent() {

        BasicData data = new BasicData();
        Rol rolAlumno = data.createRole(2L, "Alumno");
        session().save(rolAlumno);
        Rol role = data.createRole(3L, "Professor");
        session().save(role);
        Usuario alumno = data.createUser(2L, "sopera@gmail.com", "1234", "Santiago", rolAlumno, true);
        session().save(alumno);
        Usuario alumno2 = data.createUser(3L, "ffagnano@gmail.com", "1234", "Facundo", rolAlumno, true);
        session().save(alumno2);
        //Usuario
        Usuario professor = data.createUser(4L, "pabloantunez@mail.com", "1234", "Pablo", role, true);
        session().save(professor);
        //state
        Estado state = data.createState(1L, "PENDIENTE");
        session().save(state);
        // Lugar
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        session().save(place);
        // Dificultad
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        session().save(difficulty);
        // Disciplina
        Disciplina discipline = data.createDiscipline(1L, "Deporte Acuatico");
        session().save(discipline);
        // Detalle
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        session().save(detail);
        // Clase 1
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 12, 30), new Date(2023, 10, 20), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        session().save(lesson);
        // Clase 2
        Clase lesson2 = data.createLesson(new Date(2023, 11, 10), new Date(2023, 11, 10), new Date(2023, 11, 10), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        session().save(lesson2);
        // Clase 3
        Clase lesson3 = data.createLesson(new Date(2023, 11, 10), new Date(2023, 11, 10), new Date(2023, 11, 10), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        session().save(lesson3);
        // Clase 4
        Clase lesson4 = data.createLesson(new Date(2023, 11, 10), new Date(2023, 11, 10), new Date(2023, 11, 10), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        session().save(lesson4);


        AlumnoClase nuevoAlumnoClase = new AlumnoClase();

        nuevoAlumnoClase.setLesson(lesson2);
        nuevoAlumnoClase.setUser(alumno2);
        nuevoAlumnoClase.setIdUserClass(1L);

        session().save(nuevoAlumnoClase);

        List<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);
        expectingLessons.add(lesson3);
        expectingLessons.add(lesson4);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> claseRoot = criteriaQuery.from(Clase.class);

        Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
        Root<AlumnoClase> alumnoClaseRoot = subquery.from(AlumnoClase.class);
        subquery.select(alumnoClaseRoot.get("lesson").get("idClass")) // Obtengo un Long , Seleccionar numero from alumnoClase
                .where(criteriaBuilder.equal(alumnoClaseRoot.get("user"), alumno2.getId())); // Me va a traer todos los idClass que tengan a ese usuario.

        criteriaQuery.select(claseRoot)
                .where(criteriaBuilder.not(claseRoot.get("idClass").in(subquery)), criteriaBuilder.equal(claseRoot.get("state").get("description"), "PENDIENTE"));
        List<Clase> lessons = session().createQuery(criteriaQuery).getResultList();

        assertThat(lessons).isNotNull();
        assertThat(lessons).isNotEmpty();
        assertThat(lessons).hasSize(3);
        assertThat(lessons).isEqualTo(expectingLessons);
    }

    @Test
    @Transactional
    @Rollback
    public void whenIAskForTheSuggestedLessonsItShouldAppear() {

        BasicData data = new BasicData();

        Rol role = data.createRole(2L, "Alumno");
        session().save(role);

        Rol roleProffesor = data.createRole(3L, "Proffesor");
        session().save(roleProffesor);

        Usuario alumno = data.createUser(1L, "alumno@unlam.edu.ar", "1234", "Alumno", role, true);
        session().save(alumno);

        Usuario proffesor = data.createUser(2L, "profesor@unlam.edu.ar", "1234", "Profesor", roleProffesor, true);
        session().save(proffesor);

        Lugar place = data.createPlace(1, 12345, 54321, "Cancha de Patos - Libertad", "Cancha de Patos");
        session().save(place);

        Disciplina disciplineOne = data.createDiscipline(1L, "Fútbol");
        Disciplina disciplineTwo = data.createDiscipline(1L, "Básquet");
        Disciplina disciplineThree = data.createDiscipline(1L, "Rugby");
        Disciplina disciplineFour = data.createDiscipline(1L, "Yoga");
        session().save(disciplineOne);
        session().save(disciplineTwo);
        session().save(disciplineThree);
        session().save(disciplineFour);

        Dificultad difficulty = data.createDifficulty(1L, "Fácil");
        session().save(difficulty);

        Estado state = data.createState(1L, "Pendiente");
        Estado cancel = data.createState(4L, "Cancelada");
        session().save(state);
        session().save(cancel);

        LocalTime startTime = data.setHourMinutes(20, 30);
        LocalTime endTime = data.setHourMinutes(22, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        session().save(detail);

        Clase lesson1 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 12, 30), new Date(2023, 10, 20), detail, place, difficulty, disciplineOne, proffesor, state, "Futbol", 18, 40);
        Clase lessonTwo = data.createLesson(new Date(2023, 12, 30), new Date(2023, 12, 30), new Date(2023, 10, 20), detail, place, difficulty, disciplineTwo, proffesor, state, "Básquet", 20, 30);
        Clase lessonThree = data.createLesson(new Date(2023, 12, 30), new Date(2023, 12, 30), new Date(2023, 10, 20), detail, place, difficulty, disciplineThree, proffesor, cancel, "Rugby", 20, 30);
        Clase lessonFour = data.createLesson(new Date(2023, 12, 30), new Date(2023, 12, 30), new Date(2023, 10, 20), detail, place, difficulty, disciplineFour, proffesor, state, "Yoga", 20, 30);
        session().save(lesson1);
        session().save(lessonTwo);
        session().save(lessonThree);
        session().save(lessonFour);

        Preferencias preferenceOne = data.createPreferences(1L, alumno, disciplineOne);
        Preferencias preferenceTwo = data.createPreferences(1L, alumno, disciplineTwo);
        Preferencias preferenceThree = data.createPreferences(1L, alumno, disciplineThree);
        Preferencias preferenceFour = data.createPreferences(1L, alumno, disciplineFour);
        session().save(preferenceOne);
        session().save(preferenceTwo);
        session().save(preferenceThree);
        session().save(preferenceFour);

        List<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson1);
        expectingLessons.add(lessonTwo);
        expectingLessons.add(lessonFour);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> claseRoot = criteriaQuery.from(Clase.class);

        Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
        Root<AlumnoClase> alumnoClaseRoot = subquery.from(AlumnoClase.class);
        subquery.select(alumnoClaseRoot.get("lesson").get("idClass"))
                .where(criteriaBuilder.equal(alumnoClaseRoot.get("user"), alumno.getId()));

        Subquery<Long> subqueryTwo = criteriaQuery.subquery(Long.class);
        Root<Preferencias> preferencesRoot = subqueryTwo.from(Preferencias.class);
        subqueryTwo.select(preferencesRoot.get("discipline").get("idDiscipline"))
                .where(criteriaBuilder.equal(preferencesRoot.get("user"), alumno.getId()));

        criteriaQuery.select(claseRoot)
                .where(criteriaBuilder.not(claseRoot.get("idClass").in(subquery)),
                        criteriaBuilder.equal(claseRoot.get("state").get("description"), "Pendiente"));
        criteriaBuilder.in(claseRoot.get("discipline").get("idDiscipline")).value(subqueryTwo);

        List<Clase> lessonsByPreferences = session().createQuery(criteriaQuery).getResultList();

        assertThat(lessonsByPreferences).isNotNull();
        assertThat(lessonsByPreferences).isNotEmpty();
        assertThat(lessonsByPreferences).hasSize(3);
        assertThat(lessonsByPreferences).isEqualTo(expectingLessons);
    }

    @Test
    @Transactional
    @Rollback
    public void whenICalificateALessonShouldUpdateCalificationStatusInLesson() {

        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(1L, "profesor");
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", roleProfessor, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Crossfit");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Finalizada");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
        Boolean isCalificated = true;

        session().save(roleProfessor);
        session().save(professor);
        session().save(place);
        session().save(difficulty);
        session().save(discipline);
        session().save(detail);
        session().save(state);
        session().save(lesson);

        lesson.setCalificated(isCalificated);
        session().save(lesson);

        assertThat(lesson).isNotNull();
        assertThat(lesson).extracting("professor").contains(professor);
        assertThat(lesson).extracting("isCalificated").contains(isCalificated);
    }

    @Test
    @Transactional
    @Rollback
    public void whenINeedAListOfLessonClasificatedShouldBringAllTheLessons() {
        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(1L, "profesor");
        Rol studentRole = data.createRole(2L, "alumno");
        Usuario student = data.createUser(2L, "pablo@alumno.com", "1234", "Pablo", studentRole, true);
        Usuario student2 = data.createUser(3L, "juan@alumno.com", "1234", "Pablo", studentRole, true);
        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Juan", roleProfessor, true);
        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Crossfit");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "FINALIZADA");

        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
        Clase lesson2 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
        Clase lesson3 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
        Clase lesson4 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);


        Boolean isCalificated = true;
        Calificacion calification1 = data.createCalification(1L, "Muy buena", 5, student, lesson);
        Calificacion calification2 = data.createCalification(2L, "Mala", 4, student, lesson2);
        Calificacion calification3 = data.createCalification(3L, "Regular", 3, student2, lesson3);


        session().save(roleProfessor);
        session().save(studentRole);
        session().save(student);
        session().save(student2);
        session().save(professor);
        session().save(place);
        session().save(difficulty);
        session().save(discipline);
        session().save(detail);
        session().save(state);
        session().save(lesson);
        session().save(lesson2);
        session().save(lesson3);
        session().save(lesson4);
        session().save(calification1);
        session().save(calification2);
        session().save(calification3);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Calificacion> calificationRoot = criteriaQuery.from(Calificacion.class);
        Join<Calificacion, Clase> lessonJoin = calificationRoot.join("lesson");
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(calificationRoot.get("user"), student));
        criteriaQuery.where(predicate);
        criteriaQuery.select(lessonJoin);
        List<Clase> lessons = session().createQuery(criteriaQuery).getResultList();

        assertThat(lessons).isNotNull();
        assertThat(lessons).isNotEmpty();
        assertThat(lessons).extracting("professor").contains(professor);
        assertThat(lessons).hasSize(2);

    }

    @Test
    @Transactional
    @Rollback
    public void whenINeedACalificationShouldBringAllTheCalificationsReferToAlumno() {

        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(1L, "profesor");
        Rol roleStudent = data.createRole(2L, "alumno");

        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", roleProfessor, true);
        Usuario student = data.createUser(2L, "alumno@unlam.com", "1234", "Estudiante 1 ", roleStudent, true);
        Usuario student2 = data.createUser(3L, "alumno2@unlam.com", "1234", "Estudiante 2 ", roleStudent, true);

        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Un lugar unico", "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Crossfit");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Pendiente");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
        Clase lesson2 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
        Clase lesson3 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);

        Calificacion calification = data.createCalification(1L, "La mejor clase", 5, student, lesson);
        Calificacion calification2 = data.createCalification(2L, "Regular", 3, student, lesson2);
        Calificacion calification3 = data.createCalification(3L, "Mala", 1, student, lesson3);
        Calificacion calification4 = data.createCalification(3L, "Mala", 1, student2, lesson3);

        List<Calificacion> califications = new ArrayList<>();
        califications.add(calification4);

        session().save(roleProfessor);
        session().save(roleStudent);
        session().save(student);
        session().save(student2);
        session().save(professor);
        session().save(place);
        session().save(difficulty);
        session().save(discipline);
        session().save(detail);
        session().save(state);
        session().save(lesson);
        session().save(lesson2);
        session().save(lesson3);
        session().save(calification);
        session().save(calification2);
        session().save(calification3);
        session().save(calification4);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Calificacion> criteriaQuery = criteriaBuilder.createQuery(Calificacion.class);
        Root<Calificacion> calificationRoot = criteriaQuery.from(Calificacion.class);
        Join<Calificacion, Usuario> userJoin = calificationRoot.join("user");
        Predicate predicate = criteriaBuilder.equal(userJoin, student2);
        criteriaQuery.where(predicate);
        criteriaQuery.select(calificationRoot);
        List<Calificacion> calificationsResult = session().createQuery(criteriaQuery).getResultList();

        assertThat(calificationsResult).isNotNull();
        assertThat(calificationsResult).isNotEmpty();
        assertThat(calificationsResult).extracting("lesson").contains(lesson3);
        assertThat(calificationsResult).isEqualTo(califications);
    }




}
