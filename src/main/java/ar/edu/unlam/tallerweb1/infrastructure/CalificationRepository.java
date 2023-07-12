package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;

import java.time.LocalTime;

public interface CalificationRepository {

    Long create(String description, int score, Clase lesson, Usuario user);
    Calificacion getCalificationById(Long calificationId);
}
