package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
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

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private HttpServletRequest request;
    private HttpSession sesion;
    private RegisterServiceImpl servicioRegister;

    @Before
    public void init() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        servicioRegister = new RegisterServiceImpl(this.userRepository, this.roleRepository);
    }

    @Test
    public void registrarUsuario(){
        String mail = "facundo@mail.com";
        String password = "hola1234";
        Rol rol = new Rol();
        rol.setIdRole(2);
        rol.setDescription("alumno");


        when(roleRepository.getRoleById(rol.getIdRole())).thenReturn(rol);
        when(userRepository.create(mail, password, rol)).thenReturn(true);
        boolean isCreated = servicioRegister.registerUser(mail, password, 2);

        assertThat(isCreated).isTrue();

    }

    @Test
    public void consultarUsuario(){
        String mail = "facundo@mail.com";

        Usuario usuario = new Usuario();
        usuario.setEmail("facundo@mail.com");

        when(userRepository.getUserByEmail(any())).thenReturn(usuario);
        Usuario resultUser = servicioRegister.getUserByEmail(mail);

        assertThat(resultUser).isNotNull();
        assertThat(resultUser.getEmail()).isNotNull();
        assertThat(resultUser.getEmail()).isNotEmpty();
        assertThat(resultUser.getEmail()).isEqualTo(mail);
    }

}
