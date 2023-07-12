package ar.edu.unlam.tallerweb1.domain.association;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;

import java.util.List;

public interface StudentLessonService {

    List<AlumnoClase> getStudentLessonsCalificated (Long studentId);
}
