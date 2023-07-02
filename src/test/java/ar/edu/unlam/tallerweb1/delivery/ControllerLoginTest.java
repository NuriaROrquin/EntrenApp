package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.LoginService;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ControllerLoginTest {
    private LoginService loginService;
    private HttpServletRequest request;
    private HttpSession session;
    private ControllerLogin controllerLogin;

    @Before
    public void init() {
        loginService = mock(LoginService.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        controllerLogin = new ControllerLogin(this.loginService);
    }

    @Test
    public void dadoUnAlumnoExistenteQuePuedaIniciarSesion() {

        String role = "alumno";

        DataLogin dataLogin = dadoQueTengoDatosDeLoginValidos();
        Usuario expectedUser = dadoQueTengoUnUsuarioConRol(role);


        when(loginService.getUserByEmailAndPassword(any(), any())).thenReturn(expectedUser);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ROLE")).thenReturn(role);
        when(session.getAttribute("ID_USUARIO")).thenReturn(1);
        ModelAndView view = controllerLogin.validate(dataLogin, request);

        //asserts
        assertThat(expectedUser).isNotNull();
        assertThat(session.getAttribute("ROLE")).isNotNull();
        assertThat(session.getAttribute("ROLE")).isEqualTo(role);
        assertThat(session.getAttribute("ID_USUARIO")).isNotNull();
        assertThat(session.getAttribute("ID_USUARIO")).isEqualTo(1);
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isEqualTo("redirect:/home");
    }

    @Test
    public void dadoUnProfesorExistenteQuePuedaIniciarSesion() {

        String role = "profesor";

        DataLogin dataLogin = dadoQueTengoDatosDeLoginValidos();
        Usuario expectedUser = dadoQueTengoUnUsuarioConRol(role);


        when(loginService.getUserByEmailAndPassword(any(), any())).thenReturn(expectedUser);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ROLE")).thenReturn(role);
        when(session.getAttribute("ID_USUARIO")).thenReturn(1);
        ModelAndView view = controllerLogin.validate(dataLogin, request);

        //asserts
        assertThat(expectedUser).isNotNull();
        assertThat(session.getAttribute("ROLE")).isNotNull();
        assertThat(session.getAttribute("ROLE")).isEqualTo(role);
        assertThat(session.getAttribute("ID_USUARIO")).isNotNull();
        assertThat(session.getAttribute("ID_USUARIO")).isEqualTo(1);
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isEqualTo("redirect:/home");
    }

    @Test
    public void dadoUnAdminExistenteQuePuedaIniciarSesion() {
        //variables
        String role = "admin";

        DataLogin dataLogin = dadoQueTengoDatosDeLoginValidos();
        Usuario expectedUser = dadoQueTengoUnUsuarioConRol(role);
        when(loginService.getUserByEmailAndPassword(any(), any())).thenReturn(expectedUser);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("ROLE")).thenReturn(role);
        when(session.getAttribute("ID_USUARIO")).thenReturn(1);

        //metodos
        ModelAndView view = controllerLogin.validate(dataLogin, request);
        //asserts
        assertThat(expectedUser).isNotNull();
        assertThat(session.getAttribute("ROLE")).isNotNull();
        assertThat(session.getAttribute("ROLE")).isEqualTo(role);
        assertThat(session.getAttribute("ID_USUARIO")).isNotNull();
        assertThat(session.getAttribute("ID_USUARIO")).isEqualTo(1);
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("redirect:/home");
    }


    @Test
    public void dadoQueSeBuscaUnUsuarioElMismoEsNulo() {
        // variables
        DataLogin dataLogin = dadoQueTengoDatosDeLoginValidos();
        when(loginService.getUserByEmailAndPassword(any(), any())).thenReturn(null);
        ModelMap model = new ModelMap();
        model.put("error", "Usuario o clave incorrecta");
        when(request.getSession()).thenReturn(session);

        //metodos
        ModelAndView view = controllerLogin.validate(dataLogin, request);

        //asserts
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("login");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();
        assertThat(view.getModelMap()).isEqualTo(model);
    }


    private DataLogin dadoQueTengoDatosDeLoginValidos() {
        return new DataLogin();
    }

    private Usuario dadoQueTengoUnUsuarioConRol(String rol) {
        Usuario user = new Usuario();
        Rol role = new Rol();
        role.setDescription(rol);
        role.setIdRole(2);
        user.setRol(role);
        return user;
    }


}
