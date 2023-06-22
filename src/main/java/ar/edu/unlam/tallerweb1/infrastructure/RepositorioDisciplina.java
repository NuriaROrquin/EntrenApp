package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;

public interface RepositorioDisciplina {

    Integer create(Integer edadMinima, Integer edadMaxima, String nombre);

    Disciplina get(Long idDisciplina);

}
