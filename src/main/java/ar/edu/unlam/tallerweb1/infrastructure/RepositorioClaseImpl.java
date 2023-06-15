package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Dificultad;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository("reposiorioClase")
public class RepositorioClaseImpl implements RepositorioClase {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioClaseImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void create(Dificultad dificultad, Detalle detalle, Disciplina disciplina, Date date, Usuario professor) {
        Clase clase = new Clase();

        clase.setDifficulty(dificultad);
        clase.setDetail(detalle);
        clase.setDiscipline(disciplina);
        clase.setDate(date);
        clase.setProfesor(professor);

        sessionFactory.getCurrentSession().save(clase);
    }
}
