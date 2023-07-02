package ar.edu.unlam.tallerweb1.infrastructure;


import ar.edu.unlam.tallerweb1.domain.lesson.entities.Estado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioEstado")
public class StateRepositoryImpl implements StateRepository {
    private SessionFactory sessionFactory;
    @Autowired
    public StateRepositoryImpl (SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;

    }
    @Override
    public Estado getStateById(long stateId) {
        final Session session = sessionFactory.getCurrentSession();
        return (Estado) session.createCriteria(Estado.class)
                .add(Restrictions.eq("idState", stateId))
                .uniqueResult();
    }
}
