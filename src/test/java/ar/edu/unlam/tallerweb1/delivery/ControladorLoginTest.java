package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DatosLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.IServicioLogin;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ControladorLoginTest {
    private IServicioLogin IServicioLogin;
    private HttpServletRequest request;
    private HttpSession sesion;
    private ControladorLogin controladorLogin;

    @Before
    public void init() {
        IServicioLogin = mock(IServicioLogin.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        controladorLogin = new ControladorLogin(this.IServicioLogin);
    }

    @Test
    public void dadoUnUsuarioExistenteQueSePuedaIniciarSesion() {

        String ROL = "admin";

        DatosLogin datosLogin = dadoQueTengoDatosDeLoginValidos();
        Usuario usuarioEsperado = dadoQueTengoUnUsuarioConRol(ROL);

        ModelAndView vista = cuandoQuieroValidarElLogin(datosLogin, usuarioEsperado, ROL);

        entoncesMeDevuelveLaVistaCorrecta(vista);

        entoncesInicioSesion(ROL);
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

    //Dado
    private ModelAndView cuandoQuieroValidarElLogin(DatosLogin datosLogin, Usuario usuarioEsperado,String rol) {
        when(IServicioLogin.consultarUsuario(any(), any())).thenReturn(usuarioEsperado);
        when(request.getSession()).thenReturn(sesion);
        when(sesion.getAttribute("ROL")).thenReturn(rol);
        return controladorLogin.validarLogin(datosLogin, request);
    }

    //Entonces
    private static void entoncesMeDevuelveLaVistaCorrecta(ModelAndView vista) {
        assertThat(vista.getViewName()).isEqualTo("redirect:/home");
    }
    private void entoncesInicioSesion(String rol) {
        assertThat(sesion.getAttribute("ROL")).isEqualTo(rol);
    }
}
