package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Dificultad;

import java.util.List;

public interface DifficultyRepository {

    Dificultad get(Long difficultyId);

    List<Dificultad> getAllTheDifficulties();
}
