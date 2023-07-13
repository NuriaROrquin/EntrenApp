package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
@Repository("studentLessonRepository")
public class StudentLessonRepositoryImpl implements StudentLessonRepository{
    private SessionFactory sessionFactory;
    @Autowired
    public StudentLessonRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<AlumnoClase> getStudentLessonsCalificated(Usuario student) {
        final Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<AlumnoClase> criteriaQuery = criteriaBuilder.createQuery(AlumnoClase.class);
        Root<AlumnoClase> studentLessonRoot = criteriaQuery.from(AlumnoClase.class);
        Join<AlumnoClase, Calificacion> calificationJoin = studentLessonRoot.join("calification");
        Join<AlumnoClase, Clase> lessonJoin = studentLessonRoot.join("lesson");
        Join<AlumnoClase,Usuario> userJoin = studentLessonRoot.join("user");
        Predicate predicate = criteriaBuilder.equal(userJoin, student);
        criteriaQuery.where(predicate);
        criteriaQuery.select(studentLessonRoot);
        criteriaQuery.orderBy(criteriaBuilder.desc(calificationJoin.get("score")));
        List<AlumnoClase> calificationsResult = session.createQuery(criteriaQuery)
                .setFirstResult(0)
                .setMaxResults(3)
                .getResultList();

        return calificationsResult;
    }
}
