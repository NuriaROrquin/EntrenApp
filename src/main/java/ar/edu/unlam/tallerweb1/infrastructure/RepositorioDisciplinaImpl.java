package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioDisciplina")
public class RepositorioDisciplinaImpl implements RepositorioUsuario {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioDisciplinaImpl(SessionFactory sessionFactory) {
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
    public void create(String email, String password, String rol) {

        Usuario user = new Usuario();

        user.setRol(rol);
        user.setEmail(email);
        user.setPassword(password);
        user.setActivo(true);

        sessionFactory.getCurrentSession().save(user);
    }
}
