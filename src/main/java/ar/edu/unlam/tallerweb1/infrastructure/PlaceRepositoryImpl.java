package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Lugar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioLugar")
public class PlaceRepositoryImpl implements PlaceRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public PlaceRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Lugar get(Long idLugar) {
        final Session session = sessionFactory.getCurrentSession();
        Lugar place = (Lugar) session.createCriteria(Lugar.class)
                .add(Restrictions.eq("idPlace", idLugar))
                .uniqueResult();

        return place;
    }
}
