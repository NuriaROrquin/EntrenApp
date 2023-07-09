package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("preferencesRepository")
public class PreferencesRepositoryImpl implements PreferencesRepository{

    private SessionFactory sessionFactory;

    @Autowired
    public PreferencesRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Preferencias get(Long preferenceId) {
        final Session session = sessionFactory.getCurrentSession();

        Preferencias preference = (Preferencias) session.createCriteria(Preferencias.class)
                .add(Restrictions.eq("idPreferences", preferenceId))
                .uniqueResult();

        return preference;
    }

    @Override
    public void create(Usuario user, Disciplina discipline) {

        Preferencias preferences = new Preferencias();

        preferences.setUser(user);
        preferences.setDiscipline(discipline);

        sessionFactory.getCurrentSession().save(preferences);
    }

    @Override
    public void delete(Preferencias preference) {
        sessionFactory.getCurrentSession().delete(preference);
    }

    @Override
    public Preferencias getByDisciplineIdAndUserId(Long disciplineId, Long userId) {
        final Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Preferencias> criteriaQuery = criteriaBuilder.createQuery(Preferencias.class);
        Root<Preferencias> preferencesRoot = criteriaQuery.from(Preferencias.class);

        Predicate userPredicate = criteriaBuilder.equal(preferencesRoot.get("user").get("id"), userId);
        Predicate disciplinePredicate = criteriaBuilder.equal(preferencesRoot.get("discipline").get("idDiscipline"), disciplineId);

        Predicate predicate = criteriaBuilder.and(userPredicate, disciplinePredicate);

        criteriaQuery.where(predicate);
        criteriaQuery.select(preferencesRoot);

        try{
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Preferencias> getPreferredDisciplinesById(Long userId) {
        final Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Preferencias> criteriaQuery = criteriaBuilder.createQuery(Preferencias.class);
        Root<Preferencias> preferencesRoot = criteriaQuery.from(Preferencias.class);

        Predicate userPredicate = criteriaBuilder.equal(preferencesRoot.get("user").get("id"), userId);

        Predicate predicate = criteriaBuilder.and(userPredicate);

        criteriaQuery.where(predicate);
        criteriaQuery.select(preferencesRoot);

        List<Preferencias> preferenceList = session.createQuery(criteriaQuery).getResultList();

        return preferenceList;
    }
}
