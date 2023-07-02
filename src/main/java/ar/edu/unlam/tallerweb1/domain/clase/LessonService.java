package ar.edu.unlam.tallerweb1.domain.clase;

import ar.edu.unlam.tallerweb1.delivery.models.DataLessonRegistration;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;

import java.util.List;

public interface LessonService {

    List<Clase> getLessonsByStudentId(Long idStudent);

    List<Clase> getLessonsByProfessorId(Long id);

    void registerLesson(DataLessonRegistration dataLessonRegistration, Long idProfessor);

    List<Clase> getLessonsDependingStateFromProfessor(Long id, Long idState);

    List<Clase> cancelLesson(Long lessonId, Long userId);
}
