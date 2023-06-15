package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Time;

@Repository("repositorioDetalle")
public class RepositorioDetalleImpl implements RepositorioDetalle {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioDetalleImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(Time hourIni, Time hourFin, Integer capacity) {

        Integer lastIsertId;

        Detalle detalle = new Detalle();

        detalle.setStartHour(hourIni);
        detalle.setEndHour(hourFin);
        detalle.setCapacity(capacity);

        sessionFactory.getCurrentSession().save(detalle);
        lastIsertId = (Integer) sessionFactory.getCurrentSession().createSQLQuery("SELECT_LAST_INSERT_ID()").uniqueResult();
        return lastIsertId;
    }
}
