package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

@Repository("classRepository")
public class ClassRepositoryImpl implements ClassRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public ClassRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<AlumnoClase> getClassesByIdAlumno(Usuario alumno) {
        final Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<AlumnoClase> criteriaQuery = criteriaBuilder.createQuery(AlumnoClase.class);
        Root<AlumnoClase> usuarioClaseRoot = criteriaQuery.from(AlumnoClase.class);
        Join<AlumnoClase, Clase> claseJoin = usuarioClaseRoot.join("lesson");
        Join<AlumnoClase, Usuario> alumnoJoin = usuarioClaseRoot.join("user");
        Join<Clase, Usuario> profesorJoin = claseJoin.join("profesor");

        criteriaQuery.select(usuarioClaseRoot);

        List<AlumnoClase> lessons = session.createQuery(criteriaQuery).getResultList();

        return lessons;
    }

    @Override
    public List<Clase> getClassesByProfessorId(Usuario profesor){
        final Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> ClaseRoot = criteriaQuery.from(Clase.class);
        Join<Clase, Usuario> profesorJoin = ClaseRoot.join("professor");
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(profesorJoin.get("rol"), 3));

        criteriaQuery.select(ClaseRoot);
        List<Clase> lessons = session.createQuery(criteriaQuery).getResultList();

        return lessons;
    }

}
