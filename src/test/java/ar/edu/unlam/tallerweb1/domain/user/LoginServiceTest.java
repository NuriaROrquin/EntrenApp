package ar.edu.unlam.tallerweb1.domain.user;

import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
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

    private UserRepository userServiceDao;
    private HttpServletRequest request;
    private HttpSession session;
    private LoginServiceImpl servicioLogin;

    @Before
    public void init() {
        userServiceDao = mock(UserRepository.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        servicioLogin = new LoginServiceImpl(this.userServiceDao);
    }

    @Test
    public void consultarUsuario() {
        String mail = "facundo@mail.com";
        String password = "hola1234";

        Usuario user = new Usuario();
        user.setEmail("facundo@mail.com");
        user.setPassword("hola1234");

        when(userServiceDao.getUserByEmailAndPassword(any(), any())).thenReturn(user);
        Usuario userResult = servicioLogin.getUserByEmailAndPassword(mail, password);

        assertThat(userResult).isNotNull();
        assertThat(userResult.getEmail()).isNotNull();
        assertThat(userResult.getEmail()).isNotEmpty();
        assertThat(userResult.getEmail()).isEqualTo(mail);
        assertThat(userResult.getPassword()).isNotNull();
        assertThat(userResult.getPassword()).isNotEmpty();
        assertThat(userResult.getPassword()).isEqualTo(password);
    }

}
