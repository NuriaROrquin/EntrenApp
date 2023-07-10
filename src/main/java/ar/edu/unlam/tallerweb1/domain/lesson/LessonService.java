package ar.edu.unlam.tallerweb1.domain.lesson;

import ar.edu.unlam.tallerweb1.delivery.models.DataLesson;
import ar.edu.unlam.tallerweb1.delivery.models.DataLessonRegistration;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.delivery.models.DataCalification;

import java.util.List;

public interface LessonService {

    List<Clase> getLessonsByStudentId(Long idStudent);

    List<Clase> getLessonsByProfessorId(Long id);

    void registerLesson(DataLessonRegistration dataLessonRegistration, Long idProfessor);

    List<Clase> getLessonsByState(Long userId, Long stateId);

    List<Clase> cancelLesson(Long lessonId, Long userId);

    List<Dificultad> getAllDifficulties();

    List<Disciplina> getAllDisciplines();

    List<Disciplina> getPreferencesOrAllDisciplines(Long userId);

    List<Lugar> getAllPlaces();

    List<Clase> calificateLessonByStudent(DataCalification dataCalification, Long studentId);

    List <Clase> modifyLesson(DataLesson dataLesson, Long professorId);

    DataLessonRegistration getLessonById(Long idLesson);

    List<Clase> getAllAvailableLessons(Long studentId);

    List<Clase> getLessonsByPreferences(Long userId);

    void assingLesson(Long idLesson, Long userId);
}
