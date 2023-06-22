package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

import java.util.Date;
import java.util.List;

public interface LessonRepository {
    List<AlumnoClase> getClassesByIdAlumno(Usuario alumno);
    List<Clase> getClassesByProfessorId(Usuario profesor);
    void create(Dificultad dificultad, Detalle detalle, Disciplina disciplina, Lugar place, Date date, Usuario professor);
}
