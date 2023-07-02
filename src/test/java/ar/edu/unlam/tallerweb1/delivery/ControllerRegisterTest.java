package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataRegister;
import ar.edu.unlam.tallerweb1.domain.user.RegisterService;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
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

public class ControllerRegisterTest {

    private ControllerRegister controllerRegister;
    private RegisterService registerService;
    private HttpServletRequest request;
    private HttpSession session;

    @Before
    public void init() {
        registerService = mock(RegisterService.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        controllerRegister = new ControllerRegister(this.registerService);

    }

    @Test
    public void dadoUnUsuarioQueSeQuiereCrear() {

        ModelAndView view = controllerRegister.goToRegister();

        assertThat(view.getViewName()).isEqualTo("register");

    }

    @Test
    public void aPartirDeDatosValidosDeberiaRedireccionarmeAlLogin() {

        DataRegister dataRegister = new DataRegister();

        String email = "pantunez@alumno.unlam.edu.ar";
        String password = "pablito";
        String verificatedPassword = "pablito";
        long role = 2;

        dataRegister.setEmail(email);
        dataRegister.setPassword(password);
        dataRegister.setVerificatedPassword(verificatedPassword);
        dataRegister.setRole(role);

        when(registerService.getUserByEmail(any())).thenReturn(null);

        ModelAndView view = controllerRegister.validate(dataRegister);

        //asserts
        assertThat(dataRegister).isNotNull();
        assertThat(dataRegister.getPassword()).isNotNull();
        assertThat(dataRegister.getVerificatedPassword()).isNotNull();
        assertThat(dataRegister.getPassword()).isEqualTo(dataRegister.getVerificatedPassword());
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isEqualTo("redirect:/login");
    }

    @Test
    public void aPartirDeEncontrarElMailDeberiaDarErrorDeYaExiste() {

        DataRegister dataRegister = new DataRegister();
        String email = "pantunez@alumno.unlam.edu.ar";
        String password = "pablito";
        String verificatedPassword = "pablito";
        long role = 2;
        dataRegister.setEmail(email);
        dataRegister.setPassword(password);
        dataRegister.setVerificatedPassword(verificatedPassword);
        dataRegister.setRole(role);

        Usuario user = new Usuario();
        Rol rol = new Rol();

        rol.setIdRole(2L);
        rol.setDescription("alumno");
        user.setEmail("pantunez@alumno.unlam.edu.ar");
        user.setActivo(true);
        user.setRol(rol);
        user.setPassword("pablito");
        user.setId(1L);

        ModelMap model = new ModelMap();
        model.put("error", "El mail ingresado ya existe en nuestro sistema");

        when(registerService.getUserByEmail(any())).thenReturn(user);

        ModelAndView view = controllerRegister.validate(dataRegister);

        //asserts
        assertThat(dataRegister).isNotNull();
        assertThat(dataRegister.getPassword()).isNotNull();
        assertThat(dataRegister.getVerificatedPassword()).isNotNull();
        assertThat(dataRegister.getPassword()).isEqualTo(dataRegister.getVerificatedPassword());
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isEqualTo("register");

        assertThat(view.getModel()).isEqualTo(model);
    }

    @Test
    public void aPartirDeContrasenasDistintasDeberiaMostrarRegister() {
        DataRegister dataRegister = new DataRegister();
        String email = "pantunez@alumno.unlam.edu.ar";
        String password = "pablito";
        String verificatedPassword = "pablito2";
        long role = 2;
        dataRegister.setEmail(email);
        dataRegister.setPassword(password);
        dataRegister.setVerificatedPassword(verificatedPassword);
        dataRegister.setRole(role);
        ModelMap model = new ModelMap();
        model.put("error", "Las contrasenas no coinciden");

        //llamado al metodo
        ModelAndView view = controllerRegister.validate(dataRegister);

        //asserts
        assertThat(dataRegister).isNotNull();
        assertThat(dataRegister.getPassword()).isNotNull();
        assertThat(dataRegister.getVerificatedPassword()).isNotNull();
        assertThat(dataRegister.getPassword()).isNotEqualTo(dataRegister.getVerificatedPassword());
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("register");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();
        assertThat(view.getModelMap()).isEqualTo(model);
    }

}
