package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.clase.LessonService;
import ar.edu.unlam.tallerweb1.domain.clase.entities.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerLessonTest {

    private ControllerLesson controllerLesson;
    private LessonService lessonService;
    private HttpServletRequest request;
    private HttpSession session;

    // Constructor
    @Before
    public void init(){
        lessonService = mock(LessonService.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        controllerLesson = new ControllerLesson(this.lessonService);
    }

    @Test
    public void havingAProfessorIdShouldShowTheirLessons(){
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
        Estado state = dataState.createState(1L,"pendiente");


        // Clase 1
        BasicData dataLesson = new BasicData();
        Clase lesson = dataLesson.createClase(1,new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);

        // Clase 2
        BasicData dataLesson2 = new BasicData();
        Clase lesson2 = dataLesson2.createClase(1,new Date(2023,11,10), new Date(2023,11,10),new Date(2024,05,30), detail, place, difficulty, discipline, professor, state);


        List<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);
        expectingLessons.add(lesson2);

        // Metodos
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(any())).thenReturn(professor.getId());
        ModelAndView view = controllerLesson.getLessonsByProfessorId(request);
        when(lessonService.getLessonsByProfessorId(professor.getId())).thenReturn(expectingLessons);

        // Asserts
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("professorLessons");

    }

}
