package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

@Repository("classRepository")
public class LessonRepositoryImpl implements LessonRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public LessonRepositoryImpl(SessionFactory sessionFactory){
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
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(profesorJoin.get("rol"), profesor.getRol().getIdRole()));
        criteriaQuery.where(predicate);
        criteriaQuery.select(ClaseRoot);
        List<Clase> lessons = session.createQuery(criteriaQuery).getResultList();

        return lessons;
    }

    @Override
    public List<Clase> getLessonsDependingStateFromProfessor(Usuario professor, Estado state){
        final Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Clase> criteriaQuery = criteriaBuilder.createQuery(Clase.class);
        Root<Clase> ClaseRoot = criteriaQuery.from(Clase.class);

        Join<Clase, Usuario> profesorJoin = ClaseRoot.join("professor");
        Join<Clase, Estado> estadoJoin = ClaseRoot.join("state");
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(profesorJoin.get("id"), professor.getId()),
                criteriaBuilder.equal(estadoJoin.get("idState"), state.getIdState())
        );
        criteriaQuery.where(predicate);
        criteriaQuery.select(ClaseRoot);

        List<Clase> lessons = session.createQuery(criteriaQuery).getResultList();
        return lessons;
    }
    @Override
    public void create(Dificultad dificultad, Detalle detalle, Disciplina disciplina, Lugar place, Date date, Usuario professor) {
        Clase clase = new Clase();

        clase.setDifficulty(dificultad);
        clase.setDetail(detalle);
        clase.setDiscipline(disciplina);
        clase.setPlace(place);
        clase.setDate(date);
        clase.setProfesor(professor);

        sessionFactory.getCurrentSession().save(clase);
    }

}
