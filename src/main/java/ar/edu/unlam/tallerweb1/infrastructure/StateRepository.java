package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Estado;


public interface StateRepository {

    Estado getStateById(long stateId);
}
