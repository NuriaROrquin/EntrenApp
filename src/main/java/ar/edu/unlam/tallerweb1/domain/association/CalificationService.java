package ar.edu.unlam.tallerweb1.domain.association;

import ar.edu.unlam.tallerweb1.delivery.models.DataCalification;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;

import java.util.List;

public interface CalificationService {

    List<Calificacion> getProfessorCalifications(Long professorId, Integer limit);

    Double getProfessorCalificationsAverage(Long professorId);
}
