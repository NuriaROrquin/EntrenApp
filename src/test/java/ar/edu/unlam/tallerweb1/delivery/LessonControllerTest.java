package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataCalification;
import ar.edu.unlam.tallerweb1.delivery.models.DataLesson;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.delivery.models.DataLessonRegistration;
import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
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

public class LessonControllerTest {

    private LessonController lessonController;
    private LessonService lessonService;
    private HttpServletRequest request;
    private HttpSession session;

    @Before
    public void init(){
        lessonService = mock(LessonService.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        lessonController = new LessonController(this.lessonService);
    }

    @Test
    public void havingAProfessorIdShowMeTheForms(){

        long rol = 3;

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(any())).thenReturn(rol);

        //llamo al controlador - metodos
        ModelAndView vista = lessonController.goToRegisterLesson(request);

        //assert
        assertThat(rol).isNotNull();
        assertThat(rol).isEqualTo(3);
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isNotEmpty();
        assertThat(vista.getViewName()).isEqualTo("formsRegisterLesson");


    }

    @Test
    public void havingAStudentIdDontShowMeTheForms(){

        long rol = 2;

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(any())).thenReturn(rol);

        //llamo al controlador - metodos
        ModelAndView vista = lessonController.goToRegisterLesson(request);

        //assert
        assertThat(rol).isNotNull();
        assertThat(rol).isEqualTo(2);
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isNotEmpty();
        assertThat(vista.getViewName()).isEqualTo("noaccess");

    }

    @Test
    public void havingAProfessorIdShouldCreateALesson(){

        BasicData data = new BasicData();
        Rol role = data.createRole(3L, "professor");
        Usuario professor = data.createUser(1l, "profeunlam@gmail.com","1234","Facundo", role, true);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(any())).thenReturn(professor.getId());

        ModelAndView view = lessonController.validate(new DataLessonRegistration(), request);

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("registerLesson");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();

    }

    @Test
    public void havingAProfessorIdShouldShowTheirLessons(){
        BasicData data = new BasicData();
        Rol role = data.createRole(3L,"professor");
        Usuario professor = data.createUser(1L,"profesor@unlam.com","1234","Juan", role, true);
        Lugar place = data.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L,"Crossfit");
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        Estado state = data.createState(1L,"pendiente");
        Clase lesson = data.createLesson(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        Clase lesson2 = data.createLesson(new Date(2023,11,10), new Date(2023,11,10),new Date(2024,05,30), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);

        List<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);
        expectingLessons.add(lesson2);

        // Metodos
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(professor.getId());
        when(session.getAttribute("ROLE")).thenReturn(role.getIdRole());
        ModelAndView view = lessonController.getLessons(request);
        when(lessonService.getLessonsByProfessorId(professor.getId())).thenReturn(expectingLessons);

        // Asserts
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("professorLessons");

    }

    @Test
    public void havingALessonStateShouldShowLessonsReferingThatStateWithRoleProfessor(){
        BasicData data = new BasicData();
        Rol role = data.createRole(3L,"profesor");
        Usuario professor = data.createUser(1L,"profesor@unlam.com","1234","Juan", role, true);
        Lugar place = data.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L,"Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        Estado state = data.createState(1L,"Pendiente");
        Clase lesson = data.createLesson(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        Clase lesson2 = data.createLesson(new Date(2023,11,10), new Date(2023,11,10),new Date(2024,05,30), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);

        List<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);
        expectingLessons.add(lesson2);
        DataLesson dataLesson = new DataLesson();
        dataLesson.setIdState(1L);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(professor.getId());
        when(session.getAttribute("ROLE")).thenReturn(role.getIdRole());
        when(lessonService.getLessonsByState(professor.getId(),state.getIdState())).thenReturn(expectingLessons);
        ModelAndView view = lessonController.getLessonsByStateId(request, dataLesson);

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("professorLessons");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();

    }

    @Test
    public void havingAStudentIdShouldShowTheirLessons(){
        BasicData data = new BasicData();
        Rol role = data.createRole(1L,"profesor");
        Usuario professor = data.createUser(1L,"profesor@unlam.com","1234","Juan", role, true);
        Lugar place = data.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L,"Deporte Acuatico");
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        Estado state = data.createState(1L,"pendiente");
        Clase lesson = data.createLesson(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);

        Rol roleStudent = data.createRole(2L,"alumno");
        Usuario student = data.createUser(1L,"alumno@unlam.com","1234","Juan", roleStudent, true);

        List<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);

        // Metodos
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(student.getId());
        when(session.getAttribute("ROLE")).thenReturn(roleStudent.getIdRole());
        when(lessonService.getLessonsByStudentId(student.getId())).thenReturn(expectingLessons);

        ModelAndView view = lessonController.getLessons(request);

        // Asserts
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("studentLessons");

    }

    @Test
    public void havingALessonIdAndProfessorIdShouldCancelTheLesson(){
        BasicData data = new BasicData();
        Rol role = data.createRole(3L,"professor");
        Usuario professor = data.createUser(1L,"profesor@unlam.com","1234","Juan", role, true);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(any())).thenReturn(professor.getId());
        ModelAndView view = lessonController.cancelLesson(request, new DataLesson());

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("professorLessons");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();

    }

    @Test
    public void havingALessonStateShouldShowLessonsReferingThatStateWithRoleStudent(){
        BasicData basicData = new BasicData();
        Rol role = basicData.createRole(3L,"profesor");
        Usuario professor = basicData.createUser(1L,"profesor@unlam.com","1234","Juan", role, true);
        Lugar place = basicData.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = basicData.createDifficulty(1L, "Avanzado");
        Disciplina discipline = basicData.createDiscipline(1L,"Deporte Acuatico");
        LocalTime startTime = basicData.setHourMinutes(2,30);
        LocalTime endTime = basicData.setHourMinutes(4,00);
        Detalle detail = basicData.createDetail(1L,startTime,endTime,50 );
        Estado state = basicData.createState(1L,"Pendiente");
        Clase lesson = basicData.createLesson(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);

        DataLesson dataLesson = new DataLesson();
        dataLesson.setIdState(3L);

        ArrayList<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(1L);
        when(session.getAttribute("ROLE")).thenReturn(2L);
        ModelAndView view = lessonController.getLessonsByStateId(request, dataLesson);
        when(lessonService.getLessonsByState(any(),any())).thenReturn(expectingLessons);

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("studentLessons");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();

    }
    @Test
    public void whenIWantToCalificateALessonShouldCalificateItByStudent(){
        BasicData basicData = new BasicData();
        Rol professorRole = basicData.createRole(3L,"profesor");
        Rol studentRole = basicData.createRole(2L,"alumno");
        Usuario student = basicData.createUser(2L, "alumno@unlam.com","1234","Pepe",studentRole,true);
        Usuario professor = basicData.createUser(1L,"profesor@unlam.com","1234","Juan", professorRole, true);
        Lugar place = basicData.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = basicData.createDifficulty(1L, "Avanzado");
        Disciplina discipline = basicData.createDiscipline(1L,"Deporte Acuatico");
        LocalTime startTime = basicData.setHourMinutes(2,30);
        LocalTime endTime = basicData.setHourMinutes(4,00);
        Detalle detail = basicData.createDetail(1L,startTime,endTime,50 );
        Estado state = basicData.createState(1L,"Pendiente");
        Clase lesson = basicData.createLesson(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        AlumnoClase studentLesson = basicData.createStudentLesson(1,student,lesson);
        Calificacion calification = basicData.createCalification(1L,"Muy buena clase!",5,student,lesson);

        List<Clase> lessons = new ArrayList<>();
        lessons.add(lesson);

        DataCalification dataCalification = new DataCalification();
        dataCalification.setCalificationId(calification.getIdCalification());
        dataCalification.setLessonId(lesson.getIdClass());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(student.getId());
        when(lessonService.calificateLessonByStudent(dataCalification,student.getId())).thenReturn(lessons);
        ModelAndView view = lessonController.calificateLessonByStudent(request,dataCalification);

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("lessons");

    }

    @Test
    public void wantingToModifyLessonInformationShouldLetChangeItByItProfessor(){
        BasicData basicData = new BasicData();
        Rol role = basicData.createRole(3L,"profesor");
        Usuario professor = basicData.createUser(1L,"profesor@unlam.com","1234","Juan", role, true);
        Lugar place = basicData.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = basicData.createDifficulty(1L, "Avanzado");
        Disciplina discipline = basicData.createDiscipline(1L,"Deporte Acuatico");
        LocalTime startTime = basicData.setHourMinutes(2,30);
        LocalTime endTime = basicData.setHourMinutes(4,00);
        Detalle detail = basicData.createDetail(1L,startTime,endTime,50 );
        Estado state = basicData.createState(1L,"Pendiente");
        Clase lesson = basicData.createLesson(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);

        // De aca obtengo el ID de la clase
        DataLesson dataLesson = new DataLesson();
        dataLesson.setLessonId(1L);

        // Lo voy a usar para el retorno de las clases del profesor.
        List<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(1L);
        ModelAndView view = lessonController.modifyLessonInformation(dataLesson, request);
        when(lessonService.modifyLesson(any(),any())).thenReturn(expectingLessons);
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("professorLessons");
    }

    @Test
    public void whenIClickOnModifyLessonShouldBringLessonInformation(){
        BasicData basicData = new BasicData();
        Rol role = basicData.createRole(3L,"profesor");
        Usuario professor = basicData.createUser(1L,"profesor@unlam.com","1234","Juan", role, true);
        Lugar place = basicData.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = basicData.createDifficulty(1L, "Avanzado");
        Disciplina discipline = basicData.createDiscipline(1L,"Deporte Acuatico");
        LocalTime startTime = basicData.setHourMinutes(2,30);
        LocalTime endTime = basicData.setHourMinutes(4,00);
        Detalle detail = basicData.createDetail(1L,startTime,endTime,50 );
        Estado state = basicData.createState(1L,"Pendiente");
        Clase lesson = basicData.createLesson(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);

        DataLessonRegistration dataLesson = new DataLessonRegistration();

        dataLesson.setDate(lesson.getDate());
        dataLesson.setCapacity(lesson.getDetail().getCapacity());
        dataLesson.setAge_max(lesson.getMaximum_age());
        dataLesson.setAge_min(lesson.getMinimum_age());
        dataLesson.setIdDifficulty(lesson.getDifficulty().getIdDifficulty());
        dataLesson.setIdDiscipline(lesson.getDiscipline().getIdDiscipline());
        dataLesson.setIdLugar(lesson.getPlace().getIdPlace());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(1L);
        when(lessonService.getLessonById(any())).thenReturn(dataLesson);
        ModelAndView view = lessonController.getLessonById(request, lesson.getIdClass());

        assertThat(view.getViewName()).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("modifyLesson");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();

    }
    @Test
    public void whenIClickOnReservarshowMeAllTheLessonsThatAreAvailableWhenIamAStudent(){

        BasicData basicData = new BasicData();

        Rol role = basicData.createRole(2L,"alumno");
        Rol role2 = basicData.createRole(3L,"profesor");
        Usuario professor = basicData.createUser(2L,"profesor@unlam.com","1234","Juan", role2, true);
        Usuario alumno = basicData.createUser(4L,"alumno@unlam.com","1234","Facundo", role, true);
        Lugar place = basicData.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = basicData.createDifficulty(1L, "Avanzado");
        Disciplina discipline = basicData.createDiscipline(1L,"Deporte Acuatico");
        LocalTime startTime = basicData.setHourMinutes(2,30);
        LocalTime endTime = basicData.setHourMinutes(4,00);
        Detalle detail = basicData.createDetail(1L,startTime,endTime,50 );
        Estado state = basicData.createState(1L,"Pendiente");

        Clase lesson = basicData.createLesson(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);
        Clase lesson2 = basicData.createLesson(new Date(2023,12,30), new Date(2023,11,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state, "Natacion", 18, 40);


        List<Clase> lessons = new ArrayList<>();
        lessons.add(lesson);
        lessons.add(lesson2);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(alumno.getId());
        when(lessonService.getAllAvailableLessons(4L)).thenReturn(lessons);
        ModelAndView view = lessonController.getAllAvailableLessons(request);

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("availableLessons");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();

    }



}


