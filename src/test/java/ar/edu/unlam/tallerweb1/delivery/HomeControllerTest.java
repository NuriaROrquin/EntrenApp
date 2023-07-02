package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.user.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HomeControllerTest {

    private HomeController homeController;
    private LessonService lessonService;
    private LoginService loginService;
    private HttpServletRequest request;
    private HttpSession sesion;

    @Before
    public void init() {
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        homeController = new HomeController(lessonService, loginService);
    }

    @Test
    public void dadoUnAlumnoQueSeQuiereIrASuHome() {
        //preparacion de datos
        long rol = 2;

        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute(any())).thenReturn(rol);

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

        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute(any())).thenReturn(rol);

        //llamo al controlador - metodos
        ModelAndView vista = homeController.goToHome(request);

        //assert
        assertThat(rol).isNotNull();
        assertThat(rol).isEqualTo(3);
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isNotEmpty();
        assertThat(vista.getViewName()).isEqualTo("professorHome");
    }


}
