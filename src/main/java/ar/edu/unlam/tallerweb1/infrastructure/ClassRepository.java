package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Dificultad;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.hsqldb.rights.User;

import java.util.Date;
import java.util.List;

public interface ClassRepository {
    List<AlumnoClase> getClassesByIdAlumno(Usuario alumno);
    List<Clase> getClassesByProfessorId(Usuario profesor);
    void create(Dificultad dificultad, Detalle detalle, Disciplina disciplina, Date date, Usuario professor);
}
