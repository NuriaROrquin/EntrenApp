package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataLessonState;
import ar.edu.unlam.tallerweb1.domain.clase.LessonService;
import ar.edu.unlam.tallerweb1.domain.clase.entities.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Basic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

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
        BasicData data = new BasicData();
        Rol role = data.createRole(3L,"professor");
        Usuario professor = data.createUser(1L,"profesor@unlam.com","1234","Juan", role, true);
        Lugar place = data.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        Estado state = data.createState(1L,"pendiente");
        Clase lesson = data.createClase(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);
        Clase lesson2 = data.createClase(new Date(2023,11,10), new Date(2023,11,10),new Date(2024,05,30), detail, place, difficulty, discipline, professor, state);

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

    @Test
    public void havingALessonStateShouldShowLessonsReferingThatState(){
        BasicData data = new BasicData();
        Rol role = data.createRole(3L,"professor");
        Usuario professor = data.createUser(1L,"profesor@unlam.com","1234","Juan", role, true);
        Lugar place = data.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        Estado state = data.createState(1L,"Pendiente");
        Clase lesson = data.createClase(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);
        Clase lesson2 = data.createClase(new Date(2023,11,10), new Date(2023,11,10),new Date(2024,05,30), detail, place, difficulty, discipline, professor, state);

        List<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);
        expectingLessons.add(lesson2);
        DataLessonState dataLessonState = new DataLessonState();
        dataLessonState.setIdState(1L);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(any())).thenReturn(professor.getId());
        ModelAndView view = controllerLesson.getLessonsByStateIdAndProfessorId(request, dataLessonState);
        when(lessonService.getLessonsDependingStateFromProfessor(any(),any())).thenReturn(expectingLessons);
        
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("professorLessons");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();

    }
}


