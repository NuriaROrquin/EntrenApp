package ar.edu.unlam.tallerweb1.domain.clase;

import ar.edu.unlam.tallerweb1.delivery.models.DatosRegisterLessonProfessor;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Dificultad;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;

import java.sql.Time;

public interface ServicioRegisterLessonProfessor {
    void registerLesson(DatosRegisterLessonProfessor datosRegisterLessonProfessor, Long idProfessor);
}
