package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioUsuarioImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Usuario getUserByEmailAndPassword(String email, String password) {
        final Session session = sessionFactory.getCurrentSession();
        return (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }

    @Override
    public Usuario getUserByEmail(String email) {
        final Session session = sessionFactory.getCurrentSession();
        return (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public Usuario getUserById(Long id) {
        final Session session = sessionFactory.getCurrentSession();
        Usuario usuario = (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return usuario;
    }

    @Override
    public void create(String email, String password, String rol) {

        Usuario user = new Usuario();

        Rol role = new Rol();
        role.setDescription(rol);

        user.setRol(role);
        user.setEmail(email);
        user.setPassword(password);
        user.setActivo(true);

        sessionFactory.getCurrentSession().save(user);
    }
}
