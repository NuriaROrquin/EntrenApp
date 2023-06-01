package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerHomeTest {

    private ControladorHome controladorHome;
    private ServicioLogin ServicioLogin;
    private HttpServletRequest request;
    private HttpSession sesion;

    @Before
    public void init() {
        ServicioLogin = mock(ServicioLogin.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        controladorHome = new ControladorHome(this.ServicioLogin);
    }

    @Test
    public void dadoUnAlumnoQueSeQuiereIrASuHome() {
        request.getSession().setAttribute("ROL","");
        ModelAndView vista = controladorHome.irAHome(request);
        assertThat(vista).isNotNull();

        //assertThat(vista.getViewName()).isEqualTo("home");

    }


}
