package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
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
    private ServicioLogin ServicioLogin;
    private HttpServletRequest request;
    private HttpSession sesion;
    private ControllerLogin controllerLogin;

    @Before
    public void init() {
        ServicioLogin = mock(ServicioLogin.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        controllerLogin = new ControllerLogin(this.ServicioLogin);
    }

    @Test
    public void dadoUnAlumnoExistenteQuePuedaIniciarSesion() {

        String rol = "alumno";

        DataLogin datosLogin = dadoQueTengoDatosDeLoginValidos();
        Usuario usuarioEsperado = dadoQueTengoUnUsuarioConRol(rol);


        when(ServicioLogin.getUserByEmailAndPassword(any(), any())).thenReturn(usuarioEsperado);
        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute("ROLE")).thenReturn(rol);
        when(sesion.getAttribute("ID_USUARIO")).thenReturn(1);
        ModelAndView vista = controllerLogin.validate(datosLogin, request);

        //asserts
        assertThat(usuarioEsperado).isNotNull();
        assertThat(sesion.getAttribute("ROLE")).isNotNull();
        assertThat(sesion.getAttribute("ROLE")).isEqualTo(rol);
        assertThat(sesion.getAttribute("ID_USUARIO")).isNotNull();
        assertThat(sesion.getAttribute("ID_USUARIO")).isEqualTo(1);
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isEqualTo("redirect:/home");
    }

    @Test
    public void dadoUnProfesorExistenteQuePuedaIniciarSesion() {

        String rol = "profesor";

        DataLogin datosLogin = dadoQueTengoDatosDeLoginValidos();
        Usuario usuarioEsperado = dadoQueTengoUnUsuarioConRol(rol);


        when(ServicioLogin.getUserByEmailAndPassword(any(), any())).thenReturn(usuarioEsperado);
        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute("ROLE")).thenReturn(rol);
        when(sesion.getAttribute("ID_USUARIO")).thenReturn(1);
        ModelAndView vista = controllerLogin.validate(datosLogin, request);

        //asserts
        assertThat(usuarioEsperado).isNotNull();
        assertThat(sesion.getAttribute("ROLE")).isNotNull();
        assertThat(sesion.getAttribute("ROLE")).isEqualTo(rol);
        assertThat(sesion.getAttribute("ID_USUARIO")).isNotNull();
        assertThat(sesion.getAttribute("ID_USUARIO")).isEqualTo(1);
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isEqualTo("redirect:/home");
    }

    @Test
    public void dadoUnAdminExistenteQuePuedaIniciarSesion() {
        //variables
        String rol = "admin";

        DataLogin datosLogin = dadoQueTengoDatosDeLoginValidos();
        Usuario usuarioEsperado = dadoQueTengoUnUsuarioConRol(rol);
        when(ServicioLogin.getUserByEmailAndPassword(any(), any())).thenReturn(usuarioEsperado);
        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute("ROLE")).thenReturn(rol);
        when(sesion.getAttribute("ID_USUARIO")).thenReturn(1);

        //metodos
        ModelAndView vista = controllerLogin.validate(datosLogin, request);
        //asserts
        assertThat(usuarioEsperado).isNotNull();
        assertThat(sesion.getAttribute("ROLE")).isNotNull();
        assertThat(sesion.getAttribute("ROLE")).isEqualTo(rol);
        assertThat(sesion.getAttribute("ID_USUARIO")).isNotNull();
        assertThat(sesion.getAttribute("ID_USUARIO")).isEqualTo(1);
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isNotNull();
        assertThat(vista.getViewName()).isNotEmpty();
        assertThat(vista.getViewName()).isEqualTo("redirect:/home");
    }


    @Test
    public void dadoQueSeBuscaUnUsuarioElMismoEsNulo() {
        // variables
        DataLogin datosLogin = dadoQueTengoDatosDeLoginValidos();
        when(ServicioLogin.getUserByEmailAndPassword(any(), any())).thenReturn(null);
        ModelMap model = new ModelMap();
        model.put("error", "Usuario o clave incorrecta");
        when(request.getSession()).thenReturn(sesion);

        //metodos
        ModelAndView vista = controllerLogin.validate(datosLogin, request);

        //asserts
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isNotNull();
        assertThat(vista.getViewName()).isNotEmpty();
        assertThat(vista.getViewName()).isEqualTo("login");
        assertThat(vista.getModelMap()).isNotNull();
        assertThat(vista.getModelMap()).isNotEmpty();
        assertThat(vista.getModelMap()).isEqualTo(model);
    }


    private DataLogin dadoQueTengoDatosDeLoginValidos() {
        return new DataLogin();
    }

    private Usuario dadoQueTengoUnUsuarioConRol(String rol) {
        Usuario usuario = new Usuario();
        Rol role = new Rol();
        role.setDescription(rol);
        role.setIdRole(2); // hardcodeado el usuario rol - alumno
        usuario.setRol(role);
        return usuario;
    }


}
