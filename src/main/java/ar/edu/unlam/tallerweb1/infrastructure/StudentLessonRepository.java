package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;

import java.util.List;

public interface StudentLessonRepository {

    List<AlumnoClase> getStudentLessonsCalificated(Usuario student);
}
