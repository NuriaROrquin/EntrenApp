package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;

import java.util.List;


public interface PreferencesRepository {

    void create(Usuario usuario, Disciplina discipline);

    List<Disciplina> getPreferredDisciplinesById(Long userId);
}
