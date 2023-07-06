package ar.edu.unlam.tallerweb1.domain.lesson;

import ar.edu.unlam.tallerweb1.delivery.models.DataLessonRegistration;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;

import java.util.Date;
import java.util.List;

public interface LessonService {

    List<Clase> getLessonsByStudentId(Long idStudent);

    List<Clase> getLessonsByProfessorId(Long id);

    void registerLesson(DataLessonRegistration dataLessonRegistration, Long idProfessor);

    List<Clase> getLessonsByState(Long userId, Long stateId);

    List<Clase> cancelLesson(Long lessonId, Long userId);

    List <Clase> modifyLesson(Long difficultyId, Long detailId, Long disciplineId, Long placeId, Date date, Long lessonId, Long professorId);

    DataLessonRegistration getLessonById(Long idLesson);
}
