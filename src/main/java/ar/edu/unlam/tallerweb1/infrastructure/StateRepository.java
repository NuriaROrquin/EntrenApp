package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.Estado;

import javax.swing.plaf.nimbus.State;


public interface StateRepository {

    Estado getStateById(long stateId);
}
