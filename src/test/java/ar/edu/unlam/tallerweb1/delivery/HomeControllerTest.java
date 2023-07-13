package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.association.CalificationService;
import ar.edu.unlam.tallerweb1.domain.association.StudentLessonService;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.LoginService;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HomeControllerTest {

    private HomeController homeController;
    private LessonService lessonService;
    private LoginService loginService;
    private HttpServletRequest request;
    private HttpSession session;
    private StudentLessonService studentLessonService;

    private CalificationService calificationService;

    @Before
    public void init() {
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        calificationService = mock(CalificationService.class);
        studentLessonService = mock(StudentLessonService.class);
        calificationService = mock(CalificationService.class);
        homeController = new HomeController(lessonService, loginService, studentLessonService, calificationService);
    }

    @Test
    public void dadoUnAlumnoQueSeQuiereIrASuHome() {
        //preparacion de datos
        long rol = 2;

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(any())).thenReturn(rol);

        //llamo al controlador - metodos
        ModelAndView vista = homeController.goToHome(request);

        //assert
        assertThat(rol).isNotNull();
        assertThat(rol).isEqualTo(2);
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isNotEmpty();
        assertThat(vista.getViewName()).isEqualTo("studentHome");
    }

    @Test
    public void dadoUnProfesorQueSeQuiereIrASuHome() {
        //preparacion de datos
        long rol = 3;

        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(1L, "profesor");
        Rol roleStudent = data.createRole(2L, "alumno");

        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", roleProfessor, true, 50L);
        Usuario student = data.createUser(2L, "alumno@unlam.com", "1234", "Estudiante 1 ", roleStudent, true, 50L);

        Lugar place = data.createPlace(34615743.0, 58503336.0, "Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L, "Crossfit");
        LocalTime startTime = data.setHourMinutes(2, 30);
        LocalTime endTime = data.setHourMinutes(4, 00);
        Detalle detail = data.createDetail(1L, startTime, endTime, 50);
        Estado state = data.createState(1L, "Pendiente");

        Clase lesson = data.createLesson(new Date(2023, 12, 30), new Date(2023, 10, 20), new Date(2024, 12, 31), detail, place, difficulty, discipline, professor, state, "Yoga", 20, 30);

        Calificacion calification = new Calificacion();
        calification.setScore(5);
        calification.setDescription("lalala");
        calification.setUser(student);
        calification.setLesson(lesson);

        List<Calificacion> califications = new ArrayList<>();
        califications.add(calification);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(any())).thenReturn(rol);
        when(calificationService.getProfessorCalifications(1L, 3)).thenReturn(califications);

        //llamo al controlador - metodos
        ModelAndView vista = homeController.goToHome(request);

        //assert
        assertThat(rol).isNotNull();
        assertThat(rol).isEqualTo(3);
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isNotEmpty();
        assertThat(vista.getViewName()).isEqualTo("professorHome");
    }

    @Test
    public void whenILoginWithStudentRoleShouldShowTheThreeLessonsCalificatedWithMoreScore() {
        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(1L, "profesor");
        Rol roleStudent = data.createRole(2L, "alumno");

        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", roleProfessor, true, 50L);
        Usuario student = data.createUser(2L, "alumno@unlam.com", "1234", "Estudiante 1 ", roleStudent, true, 50L);

        Lugar place = data.createPlace(34615743.0, 58503336.0, "Club Buenos Aires");
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

        AlumnoClase studentLesson = data.createAlumnoClase(1L, student, lesson, calification);
        AlumnoClase studentLesson2 = data.createAlumnoClase(2L, student, lesson2, calification2);
        AlumnoClase studentLesson3 = data.createAlumnoClase(3L, student, lesson3, calification3);
        AlumnoClase studentLesson4 = data.createAlumnoClase(4L, student, lesson4, calification4);
        AlumnoClase studentLesson5 = data.createAlumnoClase(5L, student, lesson5, calification5);

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

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(student.getId());
        when(session.getAttribute("ROLE")).thenReturn(student.getRol().getIdRole());
        when(studentLessonService.getStudentLessonsCalificated(student.getId())).thenReturn(studentLessonList);
        ModelAndView view = homeController.goToHome(request);

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();
    }

    @Test
    public void whenILoginWithProfessorShouldShowHisAverageOfLessons() {
        BasicData data = new BasicData();
        Rol roleProfessor = data.createRole(1L, "profesor");
        Rol roleStudent = data.createRole(2L, "alumno");

        Usuario professor = data.createUser(1L, "pablo@hotmail.com", "1234", "Pablo", roleProfessor, true, 50L);
        Usuario student = data.createUser(2L, "alumno@unlam.com", "1234", "Estudiante 1 ", roleStudent, true, 50L);

        Lugar place = data.createPlace(34615743.0, 58503336.0,  "Club Buenos Aires");
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

        Double averageEstimated = 3.0;
        Integer limit = 3;

        List<Calificacion> calificationList = new ArrayList<>();
        calificationList.add(calification);
        calificationList.add(calification2);
        calificationList.add(calification3);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(professor.getId());
        when(session.getAttribute("ROLE")).thenReturn(professor.getRol().getIdRole());
        when(calificationService.getProfessorCalificationsAverage(professor.getId())).thenReturn(averageEstimated);
        when(calificationService.getProfessorCalifications(professor.getId(), limit)).thenReturn(calificationList);
        ModelAndView view = homeController.goToHome(request);

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("professorHome");
    }
}
