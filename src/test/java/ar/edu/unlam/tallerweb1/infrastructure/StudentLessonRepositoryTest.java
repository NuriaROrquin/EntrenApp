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

public class StudentLessonRepositoryTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void whenIwantLessonsCalificatedByStudentShouldBringOnlyTheThreeWithBestScore(){
        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(1L, "profesor");
        Rol roleStudent = data.createRole(2L, "alumno");

        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", roleProfessor, true, 50L);
        Usuario student = data.createUser(2L, "alumno@unlam.com", "1234", "Estudiante 1 ", roleStudent, true, 50L);

        Lugar place = data.createPlace(1L, 34615743L, 58503336L, "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Crossfit");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Pendiente");

        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
        Clase lesson2 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
        Clase lesson3 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
        Clase lesson4 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);
        Clase lesson5 = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);

        lesson.setIdClass(1L);
        lesson2.setIdClass(2L);
        lesson3.setIdClass(3L);
        lesson4.setIdClass(4L);
        lesson5.setIdClass(5L);

        Calificacion calification = data.createCalification(1L, "La mejor clase", 5, student, lesson);
        Calificacion calification2 = data.createCalification(2L, "Medio", 3, student, lesson2);
        Calificacion calification3 = data.createCalification(3L, "Mala", 2, student, lesson3);
        Calificacion calification4 = data.createCalification(4L, "Mala", 1, student, lesson4);
        Calificacion calification5 = data.createCalification(5L, "Mala", 3, student, lesson5);

        AlumnoClase studentLesson = data.createAlumnoClase(1L,student,lesson,calification);
        AlumnoClase studentLesson2 = data.createAlumnoClase(2L,student,lesson2,calification2);
        AlumnoClase studentLesson3 = data.createAlumnoClase(3L,student,lesson3,calification3);
        AlumnoClase studentLesson4 = data.createAlumnoClase(4L,student,lesson4,calification4);
        AlumnoClase studentLesson5 = data.createAlumnoClase(5L,student,lesson5,calification5);

        List<AlumnoClase> studentLessonList = new ArrayList<>();
        studentLessonList.add(studentLesson);
        studentLessonList.add(studentLesson2);
        studentLessonList.add(studentLesson3);
        studentLessonList.add(studentLesson4);
        studentLessonList.add(studentLesson5);

        List<Calificacion> calificationList = new ArrayList<>();
        calificationList.add(calification);
        calificationList.add(calification2);
        calificationList.add(calification3);
        calificationList.add(calification4);
        calificationList.add(calification5);

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
        session().save(lesson2);
        session().save(lesson3);
        session().save(lesson4);
        session().save(lesson5);
        session().save(calification);
        session().save(calification2);
        session().save(calification3);
        session().save(calification4);
        session().save(calification5);
        session().save(studentLesson);
        session().save(studentLesson2);
        session().save(studentLesson3);
        session().save(studentLesson4);
        session().save(studentLesson5);

        CriteriaBuilder criteriaBuilder = session().getCriteriaBuilder();
        CriteriaQuery<AlumnoClase> criteriaQuery = criteriaBuilder.createQuery(AlumnoClase.class);
        Root<AlumnoClase> studentLessonRoot = criteriaQuery.from(AlumnoClase.class);
        Join<AlumnoClase,Calificacion> calificationJoin = studentLessonRoot.join("calification");
        Join<AlumnoClase,Clase> lessonJoin = studentLessonRoot.join("lesson");
        Join<AlumnoClase,Usuario> userJoin = studentLessonRoot.join("user");
        Predicate predicate = criteriaBuilder.equal(userJoin, student);
        criteriaQuery.where(predicate);
        criteriaQuery.select(studentLessonRoot);
        criteriaQuery.orderBy(criteriaBuilder.desc(calificationJoin.get("score")));
        List<AlumnoClase> calificationsResult = session().createQuery(criteriaQuery)
                .setFirstResult(0)
                .setMaxResults(3)
                .getResultList();

        assertThat(calificationsResult).isNotNull();
        assertThat(calificationsResult).hasSize(3);
        assertThat(calificationsResult).extracting("lesson").contains(lesson5);
        assertThat(calificationsResult).extracting("lesson").doesNotContain(lesson3);
    }
}
