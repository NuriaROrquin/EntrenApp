package ar.edu.unlam.tallerweb1.domain.lesson;

import ar.edu.unlam.tallerweb1.delivery.models.DataLessonRegistration;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Clase;
import ar.edu.unlam.tallerweb1.delivery.models.DataCalification;

import java.util.List;

public interface LessonService {

    List<Clase> getLessonsByStudentId(Long idStudent);

    List<Clase> getLessonsByProfessorId(Long id);

    void registerLesson(DataLessonRegistration dataLessonRegistration, Long idProfessor);

    List<Clase> getLessonsByState(Long userId, Long stateId);

    List<Clase> cancelLesson(Long lessonId, Long userId);

    List<Clase> calificateLessonByStudent(Long lessonId, DataCalification dataCalification, Long studentId);
}
