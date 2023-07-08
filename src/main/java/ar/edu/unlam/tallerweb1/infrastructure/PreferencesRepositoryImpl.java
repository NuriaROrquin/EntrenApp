package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.hibernate.SessionFactory;

public class PreferencesRepositoryImpl implements PreferencesRepository{

    private SessionFactory sessionFactory;

    @Override
    public void create(Usuario user, Disciplina discipline) {

        Preferencias preferences = new Preferencias();

        preferences.setUser(user);
        preferences.setDiscipline(discipline);

        sessionFactory.getCurrentSession().save(preferences);
    }

    @Override
    public Preferencias getPreferencesByUser(Usuario user){
        return null;
    }
}
