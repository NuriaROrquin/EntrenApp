package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;

import java.util.Date;
import java.util.List;

public interface LessonRepository {
    Clase getLessonById(Long lessonId);

    List<Clase> getLessonsByStudent(Usuario student);

    List<Clase> getLessonsByProfessor(Usuario professor);

    List<Clase> getLessonsByStateAndProfessor(Usuario professor, Estado state);

    List<Clase> getAllAvailableLessons(Usuario student);

    void cancelLessonByProfessor(Clase lesson, Usuario professor);

    void create(Dificultad difficulty, Detalle detail, Disciplina discipline, Lugar place, Date date, Usuario professor, Integer minimumAge, Integer maximumAge, String name, Estado state);

    List<Clase> getLessonsByStateAndStudent(Usuario student, Estado state);

    void modify(Dificultad difficulty, Disciplina discipline, Lugar place, Date date, Clase lesson, Usuario professor);

    List<Clase> getAllLessonsByPreferences(Usuario alumno);

    void updateStateCalificationLesson(Clase lesson);

    List<Calificacion> getLessonsWithCalificationsReferToStudent(Usuario student);

    void assignLesson(Clase lesson, Usuario student);

    AlumnoClase getStudentLesson(Usuario student, Clase lesson);

    void updateStudentLesson(AlumnoClase studentLesson, Calificacion calificacion);

    void updateLessonState(Clase lesson, Estado state);

}
