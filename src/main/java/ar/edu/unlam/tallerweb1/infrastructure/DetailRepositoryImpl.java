package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Detalle;
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

        Detalle detail = new Detalle();

        detail.setStartHour(hourIni);
        detail.setEndHour(hourFin);
        detail.setCapacity(capacity);

        sessionFactory.getCurrentSession().save(detail);
        lastIsertId = detail.getIdDetail();
        return lastIsertId;
    }

    @Override
    public Detalle get(Long detailId) {
        final Session session = sessionFactory.getCurrentSession();
        Detalle detail = (Detalle) session.createCriteria(Detalle.class)
                .add(Restrictions.eq("idDetail", detailId))
                .uniqueResult();
        return detail;
    }

    @Override
    public void modify(Detalle detail) {
        sessionFactory.getCurrentSession().merge(detail);
    }
}
