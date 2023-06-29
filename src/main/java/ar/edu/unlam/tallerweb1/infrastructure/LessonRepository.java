package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

import java.util.Date;
import java.util.List;

public interface LessonRepository {
    List<Clase> getClassesByIdAlumno(Usuario alumno);

    List<Clase> getClassesByProfessorId(Usuario profesor);

    List<Clase> getLessonsInStateFinishedByProfessorId(Usuario professor, Estado state);

    void create(Dificultad dificultad, Detalle detalle, Disciplina disciplina, Lugar place, Date date, Usuario professor);
}
