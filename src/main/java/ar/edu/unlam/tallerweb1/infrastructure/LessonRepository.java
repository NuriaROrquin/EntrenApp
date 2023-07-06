package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;

import java.util.Date;
import java.util.List;

public interface LessonRepository {
    Clase getLessonById(Long lessonId);

    List<Clase> getLessonsByStudent(Usuario student);

    List<Clase> getLessonsByProfessor(Usuario professor);

    List<Clase> getLessonsByStateAndProfessor(Usuario professor, Estado state);

    void cancelLessonByProfessor(Clase lesson, Usuario professor);

    void create(Dificultad difficulty, Detalle detail, Disciplina discipline, Lugar place, Date date, Usuario professor);

    List<Clase> getLessonsByStateAndStudent(Usuario student, Estado state);

    void modify(Dificultad difficulty, Disciplina discipline, Lugar place, Date date, Clase lesson, Usuario professor);
}
