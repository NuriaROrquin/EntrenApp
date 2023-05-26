package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DatosLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ControladorLoginTest {
    private ServicioLogin ServicioLogin;
    private HttpServletRequest request;
    private HttpSession sesion;
    private ControladorLogin controladorLogin;

    @Before
    public void init() {
        ServicioLogin = mock(ServicioLogin.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        controladorLogin = new ControladorLogin(this.ServicioLogin);
    }

    @Test
    public void dadoUnAlumnoExistenteQuePuedaIniciarSesion() {

        String rol = "alumno";

        DatosLogin datosLogin = dadoQueTengoDatosDeLoginValidos();
        Usuario usuarioEsperado = dadoQueTengoUnUsuarioConRol(rol);


        when(ServicioLogin.consultarUsuario(any(), any())).thenReturn(usuarioEsperado);
        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute("ROL")).thenReturn(rol);
        ModelAndView vista = controladorLogin.validarLogin(datosLogin, request);

        //asserts
        assertThat(usuarioEsperado).isNotNull();
        assertThat(sesion.getAttribute("ROL")).isNotNull();
        assertThat(sesion.getAttribute("ROL")).isEqualTo(rol);
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isEqualTo("redirect:/homeAlumno");
    }

    @Test
    public void dadoUnProfesorExistenteQuePuedaIniciarSesion() {

        String rol = "profesor";

        DatosLogin datosLogin = dadoQueTengoDatosDeLoginValidos();
        Usuario usuarioEsperado = dadoQueTengoUnUsuarioConRol(rol);


        when(ServicioLogin.consultarUsuario(any(), any())).thenReturn(usuarioEsperado);
        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute("ROL")).thenReturn(rol);
        ModelAndView vista = controladorLogin.validarLogin(datosLogin, request);

        //asserts
        assertThat(usuarioEsperado).isNotNull();
        assertThat(sesion.getAttribute("ROL")).isNotNull();
        assertThat(sesion.getAttribute("ROL")).isEqualTo(rol);
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isEqualTo("redirect:/homeProfesor");
    }


    //Cuando

    private DatosLogin dadoQueTengoDatosDeLoginValidos() {
        return new DatosLogin();
    }

    private Usuario dadoQueTengoUnUsuarioConRol(String rol) {
        Usuario usuario = new Usuario();
        usuario.setRol(rol);
        return usuario;
    }


}
