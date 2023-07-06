package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioCalification")
public class CalificactionRepositoryImpl implements CalificationRepository {

    private SessionFactory sessionFactory;
    @Autowired
    public CalificactionRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Calificacion create(String description, int score, Clase lesson, Usuario user) {
        Calificacion calification = new Calificacion();
        calification.setUser(user);
        calification.setDescription(description);
        calification.setScore(score);
        calification.setLesson(lesson);

        sessionFactory.getCurrentSession().save(calification);
        return calification;
    }

    @Override
    public Calificacion getCalificationById(Long calificationId) {
        final Session session = sessionFactory.getCurrentSession();
        Calificacion calification = (Calificacion) session.createCriteria(Calificacion.class).add(Restrictions.eq("idCalification", calificationId)).uniqueResult();
        return calification;
    }
}
