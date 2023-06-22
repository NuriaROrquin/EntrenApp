package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.clase.LessonService;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerHomeTest {

    private ControllerHome controllerHome;
    private ServicioLogin ServicioLogin;
    private LessonService ServicioClase;
    private HttpServletRequest request;
    private HttpSession sesion;

    @Before
    public void init() {
        ServicioLogin = mock(ServicioLogin.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        controllerHome = new ControllerHome(this.ServicioLogin, this.ServicioClase);
    }

    @Test
    public void dadoUnAlumnoQueSeQuiereIrASuHome() {
        //preparacion de datos
        long rol = 2;

        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute(any())).thenReturn(rol);

        //llamo al controlador - metodos
        ModelAndView vista = controllerHome.irAHome(request);

        //assert
        assertThat(rol).isNotNull();
        assertThat(rol).isEqualTo(2);
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isNotEmpty();
        assertThat(vista.getViewName()).isEqualTo("homeAlumno");
    }

    @Test
    public void dadoUnProfesorQueSeQuiereIrASuHome() {
        //preparacion de datos
        long rol = 3;

        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute(any())).thenReturn(rol);

        //llamo al controlador - metodos
        ModelAndView vista = controllerHome.irAHome(request);

        //assert
        assertThat(rol).isNotNull();
        assertThat(rol).isEqualTo(3);
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isNotEmpty();
        assertThat(vista.getViewName()).isEqualTo("homeProfesor");
    }

    @Test
    public void dadoUnAlumnoQueTieneClasesDeberiaListarClases(){

    }


}
