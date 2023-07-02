package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Dificultad;

public interface DifficultyRepository {

    Dificultad get(Long difficultyId);
}
