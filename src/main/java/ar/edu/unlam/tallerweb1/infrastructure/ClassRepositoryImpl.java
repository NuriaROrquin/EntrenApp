package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.UsuarioClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("ClassRepository")
public class ClassRepositoryImpl implements ClassRepository {

    private SessionFactory sessionFactory;

    @Override
    public List<UsuarioClase> getClassesByIdAlumno(Integer idUser) {
        final Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UsuarioClase> criteriaQuery = criteriaBuilder.createQuery(UsuarioClase.class);
        Root<UsuarioClase> usuarioClaseRoot = criteriaQuery.from(UsuarioClase.class);
        Join<UsuarioClase, Clase> claseJoin = usuarioClaseRoot.join("lesson");
        Join<UsuarioClase, Usuario> alumnoJoin = usuarioClaseRoot.join("user");
        Join<Clase, Usuario> profesorJoin = claseJoin.join("profesor");

        criteriaQuery.select(usuarioClaseRoot);

        List<UsuarioClase> lessons = session.createQuery(criteriaQuery).getResultList();

        System.out.println(lessons);

        return lessons;

    }
}
