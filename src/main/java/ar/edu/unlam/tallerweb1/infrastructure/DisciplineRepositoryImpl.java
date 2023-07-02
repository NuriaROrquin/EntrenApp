package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioDisciplina")
public class DisciplineRepositoryImpl implements DisciplineRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public DisciplineRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(Integer minAge, Integer maxAge, String name) {
        return null;
    }

    @Override
    public Disciplina get(Long disciplineId) {
        final Session session = sessionFactory.getCurrentSession();
        Disciplina discipline = (Disciplina) session.createCriteria(Disciplina.class)
                .add(Restrictions.eq("idDiscipline", disciplineId))
                .uniqueResult();
        return discipline;
    }
}
