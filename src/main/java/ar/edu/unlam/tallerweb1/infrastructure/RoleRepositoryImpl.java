package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioRol")
public class RoleRepositoryImpl implements RoleRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public RoleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Rol getRoleById(long roleId) {
        final Session session = sessionFactory.getCurrentSession();

        Rol role = (Rol) session.createCriteria(Rol.class)
                .add(Restrictions.eq("idRole", roleId))
                .uniqueResult();

        return role;
    }
}
