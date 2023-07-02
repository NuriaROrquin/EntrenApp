package ar.edu.unlam.tallerweb1.domain.usuarios;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioRol;
import ar.edu.unlam.tallerweb1.infrastructure.RepositorioUsuario;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class ServicioRegisterTest {

    private RepositorioUsuario repositorioUsuario;
    private RepositorioRol repositorioRol;
    private HttpServletRequest request;
    private HttpSession sesion;
    private ServicioRegisterImpl servicioRegister;

    @Before
    public void init() {
        repositorioUsuario = mock(RepositorioUsuario.class);
        repositorioRol = mock(RepositorioRol.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        servicioRegister = new ServicioRegisterImpl(this.repositorioUsuario, this.repositorioRol);
    }

    @Test
    public void registrarUsuario(){
        String mail = "facundo@mail.com";
        String password = "hola1234";
        Rol rol = new Rol();
        rol.setIdRole(2);
        rol.setDescription("alumno");


        when(repositorioRol.getRolById(rol.getIdRole())).thenReturn(rol);
        when(repositorioUsuario.create(mail, password, rol)).thenReturn(true);
        boolean isCreated = servicioRegister.registerUser(mail, password, 2);

        assertThat(isCreated).isTrue();

    }

    @Test
    public void consultarUsuario(){
        String mail = "facundo@mail.com";

        Usuario usuario = new Usuario();
        usuario.setEmail("facundo@mail.com");

        when(repositorioUsuario.getUserByEmail(any())).thenReturn(usuario);
        Usuario resultUser = servicioRegister.getUserByEmail(mail);

        assertThat(resultUser).isNotNull();
        assertThat(resultUser.getEmail()).isNotNull();
        assertThat(resultUser.getEmail()).isNotEmpty();
        assertThat(resultUser.getEmail()).isEqualTo(mail);
    }

}
