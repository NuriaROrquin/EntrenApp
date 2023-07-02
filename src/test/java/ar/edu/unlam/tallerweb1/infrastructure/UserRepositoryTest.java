package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest extends SpringTest {

    @Test
    @Transactional @Rollback
    public void buscarUsuario(){
        String mail = "facundo@mail.com";
        String password = "hola1234";

        Usuario usuario = new Usuario();
        usuario.setEmail("facundo@mail.com");
        usuario.setPassword("hola1234");

        session().save(usuario);

        Usuario resultUser = (Usuario) session().createCriteria(Usuario.class)
                .add(Restrictions.eq("email", mail))
                .add(Restrictions.eq("password", password))
                .uniqueResult();

        assertThat(resultUser).isNotNull();
        assertThat(resultUser.getEmail()).isNotNull();
        assertThat(resultUser.getEmail()).isNotEmpty();
        assertThat(resultUser.getEmail()).isEqualTo(mail);
        assertThat(resultUser.getPassword()).isNotNull();
        assertThat(resultUser.getPassword()).isNotEmpty();
        assertThat(resultUser.getPassword()).isEqualTo(password);
    }

    @Test
    @Transactional @Rollback
    public void buscarMail(){
        String mail = "facundo@mail.com";

        Usuario usuario = new Usuario();
        usuario.setEmail("facundo@mail.com");

        session().save(usuario);

        Usuario resultUser = (Usuario) session().createCriteria(Usuario.class)
                .add(Restrictions.eq("email", mail))
                .uniqueResult();

        assertThat(resultUser).isNotNull();
        assertThat(resultUser.getEmail()).isNotNull();
        assertThat(resultUser.getEmail()).isNotEmpty();
        assertThat(resultUser.getEmail()).isEqualTo(mail);
    }

}
