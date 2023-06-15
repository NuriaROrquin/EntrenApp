package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioRol")
public class RepositorioRolImpl implements RepositorioRol {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioRolImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Rol getRolByDescription(String description) {
        final Session session = sessionFactory.getCurrentSession();
        return (Rol) session.createCriteria(Rol.class)
                .add(Restrictions.eq("description", description))
                .uniqueResult();
    }
}
