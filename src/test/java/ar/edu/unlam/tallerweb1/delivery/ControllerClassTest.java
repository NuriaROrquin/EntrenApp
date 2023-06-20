package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.clase.ClassService;
import ar.edu.unlam.tallerweb1.domain.clase.ClassServiceImpl;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerClassTest {

    private ControllerClass controllerClass;
    private ClassService classService;
    private HttpServletRequest request;
    private HttpSession session;

    // Constructor
    @Before
    public void init(){
        classService = mock(ClassService.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        controllerClass = new ControllerClass(this.classService);
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

        //disciplina
        Disciplina discipline = new Disciplina();
        discipline.setName("Crossfit");

        //detalle
        Detalle detail = new Detalle();
        detail.setStartHour(new Time(8, 0, 0));
        detail.setEndHour(new Time(9, 0, 0));

        BasicData data = new BasicData();
        Clase lesson = data.createClase(1,discipline,new Date(2024,12,30), detail, professor);

        //clase2
        Clase lesson2 = new Clase();
        lesson2.setIdClass(1);
        lesson2.setDiscipline(discipline);
        lesson2.setDate(new Date(2024, 10, 30));
        lesson2.setDetail(detail);
        lesson2.setProfesor(professor);


        List<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);
        expectingLessons.add(lesson2);

        // Metodos
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(any())).thenReturn(professor.getId());
        ModelAndView view = controllerClass.getLessonsByProfessorId(request);
        when(classService.getLessonsByProfessorId(professor.getId())).thenReturn(expectingLessons);

        // Asserts
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("professorLessons");


    }

}
