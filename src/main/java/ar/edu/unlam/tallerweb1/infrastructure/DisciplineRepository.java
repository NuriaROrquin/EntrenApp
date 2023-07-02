package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;

public interface DisciplineRepository {

    Integer create(Integer minAge, Integer maxAge, String name);

    Disciplina get(Long idDiscipline);

}
