package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.Instant;
import java.util.Date;

@Repository("repositorioDetalle")
public class RepositorioDetalleImpl implements RepositorioDetalle {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioDetalleImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(String hourIni, String hourFin, String capacity) {

        /*Integer lastIsertId;

        Detalle detalle = new Detalle();

        detalle.setStartHour(Date.from(Instant.now()));
        detalle.setEndHour(Date.from(Instant.now()));
        detalle.setCapacity(2);

        sessionFactory.getCurrentSession().save(detalle);
        lastIsertId = (Integer) sessionFactory.getCurrentSession().createSQLQuery("SELECT_LAST_INSERT_ID()").uniqueResult();
        return lastIsertId;*/
        return null;
    }

    @Override
    public Detalle get(Integer idDetalle){
        final Session session = sessionFactory.getCurrentSession();
        return (Detalle) session.createCriteria(Detalle.class)
                .add(Restrictions.eq("idDetail", idDetalle))
                .uniqueResult();
    }
}
