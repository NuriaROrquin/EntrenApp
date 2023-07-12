package ar.edu.unlam.tallerweb1.domain.user;

import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.RoleRepository;
import ar.edu.unlam.tallerweb1.infrastructure.UserRepository;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class RegisterServiceTest {

    private UserRepository userServiceDao;
    private RoleRepository roleServiceDao;
    private HttpServletRequest request;
    private HttpSession session;
    private RegisterServiceImpl registerService;

    @Before
    public void init() {
        userServiceDao = mock(UserRepository.class);
        roleServiceDao = mock(RoleRepository.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        registerService = new RegisterServiceImpl(this.userServiceDao, this.roleServiceDao);
    }

    @Test
    public void registrarUsuario(){
        String mail = "facundo@mail.com";
        String password = "hola1234";
        Rol role = new Rol();
        role.setIdRole(2);
        role.setDescription("alumno");
        Long age = 40L;
        String name = "Alberto";


        when(roleServiceDao.getRoleById(role.getIdRole())).thenReturn(role);
        when(userServiceDao.create(mail, password, role, age, name)).thenReturn(true);
        boolean isCreated = registerService.registerUser(mail, password, 2, age, name);

        assertThat(isCreated).isTrue();

    }

    @Test
    public void consultarUsuario(){
        String mail = "facundo@mail.com";

        Usuario user = new Usuario();
        user.setEmail("facundo@mail.com");

        when(userServiceDao.getUserByEmail(any())).thenReturn(user);
        Usuario resultUser = registerService.getUserByEmail(mail);

        assertThat(resultUser).isNotNull();
        assertThat(resultUser.getEmail()).isNotNull();
        assertThat(resultUser.getEmail()).isNotEmpty();
        assertThat(resultUser.getEmail()).isEqualTo(mail);
    }

}
