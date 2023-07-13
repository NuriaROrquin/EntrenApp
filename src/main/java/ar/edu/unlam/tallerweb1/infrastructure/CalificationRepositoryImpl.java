package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

@Repository("repositorioCalification")
public class CalificationRepositoryImpl implements CalificationRepository {

    private SessionFactory sessionFactory;
    @Autowired
    public CalificationRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Long create(String description, int score, Clase lesson, Usuario user) {
        Calificacion calification = new Calificacion();
        calification.setUser(user);
        calification.setDescription(description);
        calification.setScore(score);
        calification.setLesson(lesson);

        sessionFactory.getCurrentSession().save(calification);
        Long lastInsertedId = calification.getIdCalification();
        return lastInsertedId;
    }

    @Override
    public Calificacion getCalificationById(Long calificationId) {
        final Session session = sessionFactory.getCurrentSession();
        Calificacion calification = (Calificacion) session.createCriteria(Calificacion.class).add(Restrictions.eq("idCalification", calificationId)).uniqueResult();
        return calification;
    }

    @Override
    public Double getProfessorAverage(Usuario professor) {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
        Root<Calificacion> calificationRoot = criteriaQuery.from(Calificacion.class);
        Join<Calificacion, Clase> lessonJoin = calificationRoot.join("lesson");
        Join<Clase, Usuario> userJoin = lessonJoin.join("professor");
        Predicate predicate = criteriaBuilder.equal(userJoin, professor);
        criteriaQuery.where(predicate);
        criteriaQuery.select(criteriaBuilder.avg(calificationRoot.get("score")));
        Double professorAverage = session.createQuery(criteriaQuery).getSingleResult();
        return professorAverage;
    }

    @Override
    public List<Calificacion> getProfessorCalificationsDao(Usuario professor, Integer limit) {
        final Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Calificacion> criteriaQuery = criteriaBuilder.createQuery(Calificacion.class);
        Root<Calificacion> calificationRoot = criteriaQuery.from(Calificacion.class);
        Join<Calificacion,Clase> lessonJoin = calificationRoot.join("lesson");
        Join<Clase, Usuario> userJoin = lessonJoin.join("professor");
        Predicate predicate = criteriaBuilder.equal(userJoin, professor);
        criteriaQuery.orderBy(criteriaBuilder.asc(calificationRoot.get("score")));

        List<Calificacion> professorCalifications = null;
        if (limit != null){
            professorCalifications = session.createQuery(criteriaQuery)
                    .setMaxResults(limit)
                    .getResultList();
        }else{
            professorCalifications = session.createQuery(criteriaQuery).getResultList();
        }
        return professorCalifications;
    }
}
