package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Dificultad;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Disciplina> getAllTheDisciplines(){
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteriaQuery = session.createCriteria(Disciplina.class);
        List<Disciplina> disciplines = criteriaQuery.list();

        return disciplines;
    }
}
