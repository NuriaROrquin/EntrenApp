package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Dificultad;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioDificultad")
public class RepositorioDificultadImpl implements RepositorioDificultad{

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioDificultadImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Dificultad get(Long idDificultad) {
        final Session session = sessionFactory.getCurrentSession();
        return (Dificultad) session.createCriteria(Dificultad.class)
                .add(Restrictions.eq("idDifficulty", idDificultad))
                .uniqueResult();
    }
}
