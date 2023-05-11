package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DatosRegister;
import ar.edu.unlam.tallerweb1.domain.usuarios.IServicioRegister;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerRegisterTest {

    private ControllerRegister controllerRegister;
    private IServicioRegister IServicioRegister;

    @Before
    public void init() {
        controllerRegister = new ControllerRegister(this.IServicioRegister);
    }

    @Test
    public void dadoUnUsuarioQueSeQuiereCrear() {

        ModelAndView vista = controllerRegister.irARegister();

        assertThat(vista.getViewName()).isEqualTo("registro-usuario");

    }

    @Test
    public void aPartirDeDatosValidosDeberiaRedireccionarmeAlLogin() {
        DatosRegister datosRegister = new DatosRegister();
        ModelAndView vista = controllerRegister.registrarme(datosRegister);

        String email = "pantunez@alumno.unlam.edu.ar";
        String password = "pablito";
        String verificatedPassword = "pablito";

        datosRegister.setEmail(email);
        datosRegister.setPassword(password);
        datosRegister.setVerificatedPassword(verificatedPassword);

        controllerRegister.registrarme(datosRegister);

        assertThat(vista.getViewName()).isEqualTo("redirect:/login");
    }

    @Test
    public void deberiaValidarQueElUsuarioNoExistaEnLaTablaDeUsuarios(){
        //when(servicioRegister.consultarUsuario(any())).thenReturn(status().isOk());
    }
}
