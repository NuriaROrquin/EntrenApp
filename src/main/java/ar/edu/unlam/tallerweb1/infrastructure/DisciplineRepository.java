package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;

import java.util.List;

public interface DisciplineRepository {

    Integer create(Integer minAge, Integer maxAge, String name);

    Disciplina get(Long disciplineId);

    List<Disciplina> getAllTheDisciplines();
}
