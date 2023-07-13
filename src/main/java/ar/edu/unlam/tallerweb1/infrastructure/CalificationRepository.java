package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;

import java.time.LocalTime;
import java.util.List;

public interface CalificationRepository {

    Long create(String description, int score, Clase lesson, Usuario user);
    Calificacion getCalificationById(Long calificationId);

    Double getProfessorAverage(Usuario professor);

    List<Calificacion> getProfessorCalificationsDao(Usuario professor, Integer Limit);
}
