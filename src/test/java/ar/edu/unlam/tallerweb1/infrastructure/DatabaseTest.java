package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

// Clase que prueba la conexion a la base de datos. Hereda de SpringTest por lo que corre dentro del contexto
// de spring
public class DatabaseTest extends SpringTest{

    @Test
    @Transactional @Rollback
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }

    @Test
    @Transactional @Rollback
    public void crearUsuario(){
        Usuario usuario = new Usuario();
        Rol rol = new Rol();

        rol.setDescription("admin");
        usuario.setName("Sebastian");
        usuario.setEmail("seba@gmail.com");
        usuario.setPassword("1234");
        usuario.setRol(rol);
        session().save(usuario);
        assertThat(usuario.getId()).isNotNull();
    }
}
