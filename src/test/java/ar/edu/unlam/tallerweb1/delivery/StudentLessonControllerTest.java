package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataLesson;
import ar.edu.unlam.tallerweb1.domain.association.StudentLessonService;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import ar.edu.unlam.tallerweb1.infrastructure.StudentLessonRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentLessonControllerTest {

    private StudentLessonService studentLessonService;
    private StudentLessonController studentLessonController;
    private HttpServletRequest request;
    private HttpSession session;
    @Before
    public void init(){
        studentLessonService = mock(StudentLessonService.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        studentLessonController = new StudentLessonController(this.studentLessonService);
    }


}
