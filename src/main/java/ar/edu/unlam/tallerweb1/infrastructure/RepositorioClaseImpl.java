package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("reposiorioClase")
public class RepositorioClaseImpl implements RepositorioClase {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioClaseImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void create(Integer dificultad, Integer idDetalle, Integer idDisciplina) {

        Clase clase = new Clase();

        clase.setDifficulty(dificultad);
        clase.setDetail(idDetalle);
        clase.setDiscipline(idDisciplina);

        sessionFactory.getCurrentSession().save(clase);

    }
}
