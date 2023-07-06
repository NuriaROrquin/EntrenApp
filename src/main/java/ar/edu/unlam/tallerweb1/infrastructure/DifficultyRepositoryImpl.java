package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Dificultad;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioDificultad")
public class DifficultyRepositoryImpl implements DifficultyRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public DifficultyRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Dificultad get(Long difficultyId) {
        final Session session = sessionFactory.getCurrentSession();
        return (Dificultad) session.createCriteria(Dificultad.class)
                .add(Restrictions.eq("idDifficulty", difficultyId))
                .uniqueResult();
    }

    @Override
    public List<Dificultad> getAllTheDifficulties() {
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteriaQuery = session.createCriteria(Dificultad.class);
        List<Dificultad> difficulties = criteriaQuery.list();

        return difficulties;
    }
}
