package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DatosRegister;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegister;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerRegisterTest {

    private ControllerRegister controllerRegister;
    private ServicioRegister ServicioRegister;
    private HttpServletRequest request;
    private HttpSession sesion;

    @Before
    public void init() {
        ServicioRegister = mock(ServicioRegister.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        controllerRegister = new ControllerRegister(this.ServicioRegister);

    }

    @Test
    public void dadoUnUsuarioQueSeQuiereCrear() {

        ModelAndView vista = controllerRegister.irARegister();

        assertThat(vista.getViewName()).isEqualTo("registroUsuario");

    }

    @Test
    public void aPartirDeDatosValidosDeberiaRedireccionarmeAlLogin() {
        DatosRegister datosRegister = new DatosRegister();

        String email = "pantunez@alumno.unlam.edu.ar";
        String password = "pablito";
        String verificatedPassword = "pablito";

        datosRegister.setEmail(email);
        datosRegister.setPassword(password);
        datosRegister.setVerificatedPassword(verificatedPassword);

        Usuario usuarioEsperado = new Usuario();

        usuarioEsperado.setEmail("pantunez@alumno.unlam.edu.ar");
        usuarioEsperado.setActivo(true);
        usuarioEsperado.setRol("alumno");
        usuarioEsperado.setPassword("1234");
        usuarioEsperado.setId(1L);

        Usuario usuarioRecibido = (Usuario) when(ServicioRegister.consultarUsuario(usuarioEsperado.getEmail())).thenReturn(usuarioEsperado);

        assertThat(usuarioEsperado).isEqualTo(usuarioRecibido);


        ModelAndView vista = controllerRegister.registrarme(datosRegister);

        assertThat(vista.getViewName()).isEqualTo("redirect:/login");
    }

    @Test
    public void deberiaValidarQueElUsuarioNoExistaEnLaTablaDeUsuarios(){
        when(ServicioRegister.consultarUsuario(any())).thenReturn(null);
    }
}
