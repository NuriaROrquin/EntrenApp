package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Estado;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.prefs.Preferences;

@Repository("preferencesRepository")
public class PreferencesRepositoryImpl implements PreferencesRepository{

    private SessionFactory sessionFactory;

    @Autowired
    public PreferencesRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void create(Usuario user, Disciplina discipline) {

        Preferencias preferences = new Preferencias();

        preferences.setUser(user);
        preferences.setDiscipline(discipline);

        sessionFactory.getCurrentSession().save(preferences);
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
