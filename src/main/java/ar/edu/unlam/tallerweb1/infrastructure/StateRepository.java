package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.clase.entities.Estado;


public interface StateRepository {

    Estado getStateById(long stateId);
}
