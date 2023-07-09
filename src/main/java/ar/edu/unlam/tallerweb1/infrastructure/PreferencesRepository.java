package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;

import java.util.List;


public interface PreferencesRepository {

    Preferencias get(Long preferenceId);

    void create(Usuario usuario, Disciplina discipline);

    void delete(Preferencias preference);

    Preferencias getByDisciplineIdAndUserId(Long disciplineId, Long userId);

    List<Preferencias> getPreferredDisciplinesById(Long userId);
}
