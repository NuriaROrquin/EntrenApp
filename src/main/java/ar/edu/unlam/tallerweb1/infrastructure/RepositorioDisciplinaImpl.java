package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioDisciplina")
public class RepositorioDisciplinaImpl implements RepositorioDisciplina {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioDisciplinaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(Integer edadMinima, Integer edadMaxima, String nombre) {
        return null;
    }

    @Override
    public Disciplina get(Long idDisciplina) {
        final Session session = sessionFactory.getCurrentSession();
        Disciplina disciplina = (Disciplina) session.createCriteria(Disciplina.class)
                .add(Restrictions.eq("idDiscipline", idDisciplina))
                .uniqueResult();
        return disciplina;
    }
}
