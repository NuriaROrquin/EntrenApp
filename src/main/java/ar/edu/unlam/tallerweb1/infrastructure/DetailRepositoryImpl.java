package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository("repositorioDetalle")
public class DetailRepositoryImpl implements DetailRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public DetailRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long create(LocalTime hourIni, LocalTime hourFin, Integer capacity) {

        Long lastIsertId;

        Detalle detalle = new Detalle();

        detalle.setStartHour(hourIni);
        detalle.setEndHour(hourFin);
        detalle.setCapacity(capacity);

        sessionFactory.getCurrentSession().save(detalle);
        lastIsertId = detalle.getIdDetail();
        return lastIsertId;
    }

    @Override
    public Detalle get(Long idDetalle){
        final Session session = sessionFactory.getCurrentSession();
        Detalle detalle = (Detalle) session.createCriteria(Detalle.class)
                .add(Restrictions.eq("idDetail", idDetalle))
                .uniqueResult();
        return detalle;
    }
}
