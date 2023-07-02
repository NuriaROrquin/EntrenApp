package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.UserRepository;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LoginServiceTest {

    private UserRepository userRepository;
    private HttpServletRequest request;
    private HttpSession sesion;

    private LoginServiceImpl servicioLogin;

    @Before
    public void init() {
        userRepository = mock(UserRepository.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        servicioLogin = new LoginServiceImpl(this.userRepository);
    }

    @Test
    public void consultarUsuario(){
        String mail = "facundo@mail.com";
        String password = "hola1234";

        Usuario usuario = new Usuario();
        usuario.setEmail("facundo@mail.com");
        usuario.setPassword("hola1234");

        when(userRepository.getUserByEmailAndPassword(any(),any())).thenReturn(usuario);
        Usuario resultUser = servicioLogin.getUserByEmailAndPassword(mail, password);

        assertThat(resultUser).isNotNull();
        assertThat(resultUser.getEmail()).isNotNull();
        assertThat(resultUser.getEmail()).isNotEmpty();
        assertThat(resultUser.getEmail()).isEqualTo(mail);
        assertThat(resultUser.getPassword()).isNotNull();
        assertThat(resultUser.getPassword()).isNotEmpty();
        assertThat(resultUser.getPassword()).isEqualTo(password);
    }

}
