package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Lugar;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioLugar")
public class PlaceRepositoryImpl implements PlaceRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public PlaceRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Lugar getPlaceById(Long placeId) {
        final Session session = sessionFactory.getCurrentSession();
        Lugar place = (Lugar) session.createCriteria(Lugar.class)
                .add(Restrictions.eq("idPlace", placeId))
                .uniqueResult();

        return place;
    }

    @Override
    public List<Lugar> getAllThePlaces(){
        final Session session = sessionFactory.getCurrentSession();
        Criteria criteriaQuery = session.createCriteria(Lugar.class);
        List<Lugar> places = criteriaQuery.list();

        return places;
    }}
