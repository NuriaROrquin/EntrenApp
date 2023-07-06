package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
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
    public void whenICompleteTheLessonFormShouldCreateALesson(){
        BasicData data = new BasicData();
        Rol rolProfesor = data.createRole(3L, "profesor");
        Usuario profesor = data.createUser(3L, "profesor@unlam.com", "1234", "Santiago", rolProfesor, true);
        Usuario profesor2 = data.createUser(3L, "profesor@unlam.com", "1234", "Santiago", rolProfesor, true);
        Disciplina disciplina = data.createDiscipline(1L, "Crossfit", "Entrena tu cuerpo al maximo", 18, 50);
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Lugar place = data.createPlace(1L, 90, 69, "Club Argentinos del Oeste", "Social Club");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Estado state = data.createState(1L, "Pendiente");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), null, detail, place, difficulty, disciplina, profesor, state);

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
        Disciplina disciplina = data.createDiscipline(1L, "Crossfit", "Entrena tu cuerpo al maximo", 18, 50);
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Lugar place = data.createPlace(1L, 90, 69, "Club Argentinos del Oeste", "Social Club");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Estado state = data.createState(1L, "Pendiente");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), null, detail, place, difficulty, disciplina, profesor, state);
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
        Disciplina discipline = data.createDiscipline(1L, "Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Finalizada");
        Estado state2 = data.createState(2L, "Cancelada");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state);
        Clase lesson2 = data.createLesson(new Date(2023, 11, 10), new Date(2023, 11, 10), new Date(2024, 05, 30), detail, place, difficulty, discipline, professor, state);
        Clase lesson3 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor2, state2);

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
        Disciplina discipline = data.createDiscipline(1L, "Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Finalizada");
        Estado state2 = data.createState(2L, "Cancelada");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state);
        Clase lesson2 = data.createLesson(new Date(2023, 11, 10), new Date(2023, 11, 10), new Date(2024, 05, 30), detail, place, difficulty, discipline, professor, state);
        Clase lesson3 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state2);

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
        Disciplina discipline = data.createDiscipline(1L, "Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Pendiente");
        Estado state4 = data.createState(4L, "CANCELADA");
        Date finalDate = new Date();
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state);

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
        Disciplina discipline = data.createDiscipline(1L, "Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Finalizada");
        Estado state2 = data.createState(2L, "Cancelada");
        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state);
        Clase lesson2 = data.createLesson(new Date(2023, 11, 10), new Date(2023, 11, 10), new Date(2024, 05, 30), detail, place, difficulty, discipline, professor, state);
        Clase lesson3 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state2);
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
    public void whenINeedToKnowAllTheAvailableClassesToSingInAsStudent() {

        BasicData data = new BasicData();

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

        //alumno
        Usuario alumno2 = new Usuario();
        alumno2.setId(3L);
        alumno2.setRol(rolAlumno);
        alumno2.setName("Facundo");

        session().save(alumno2);


       //role
        Rol role = new Rol();
        role.setDescription("professor");
        role.setIdRole(3L);

        //Usuario
        Usuario professor = new Usuario();
        professor.setId(1L);
        professor.setEmail("pabloantunez@mail.com");
        professor.setRol(role);
        professor.setPassword("1234");

        //state
        Estado state = data.createState(1L, "PENDIENTE");
        session().save(state);

        // Lugar

        Lugar place = data.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        session().save(place);

        // Dificultad
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        session().save(difficulty);

        // Disciplina
        Disciplina discipline = data.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        session().save(discipline);


        // Detalle
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        session().save(detail);

        // Clase 1
        Clase lesson = data.createLesson(new Date(2023,12,30),new Date(2023,12,30), new Date(2023,10,20), detail, place, difficulty, discipline, professor, state);
        session().save(lesson);

        // Clase 2

        Clase lesson2 = data.createLesson(new Date(2023,11,10),new Date(2023,11,10), new Date(2023,11,10), detail, place, difficulty, discipline, professor, state);
        session().save(lesson2);

        AlumnoClase nuevoAlumnoClase = new AlumnoClase();

        nuevoAlumnoClase.setLesson(lesson2);
        nuevoAlumnoClase.setUser(alumno2);
        nuevoAlumnoClase.setIdUserClass(1L);

        session().save(nuevoAlumnoClase);

        List<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> claseRoot = criteriaQuery.from(Clase.class);

        Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
        Root<AlumnoClase> alumnoClaseRoot = subquery.from(AlumnoClase.class);
        subquery.select(alumnoClaseRoot.get("lesson").get("idClass"))
                .where(criteriaBuilder.equal(alumnoClaseRoot.get("user"), alumno2.getId()));

        criteriaQuery.select(claseRoot)
                .where(criteriaBuilder.not(claseRoot.get("idClass").in(subquery)));

        List<Clase> lessons = session().createQuery(criteriaQuery).getResultList();


        assertThat(lessons).isNotNull();
        assertThat(lessons).isNotEmpty();
        assertThat(lessons).hasSize(1);
        assertThat(lessons).isEqualTo(expectingLessons);
    }



}