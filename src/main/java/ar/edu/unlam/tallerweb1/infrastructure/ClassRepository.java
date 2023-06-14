package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.hsqldb.rights.User;

import java.util.List;

public interface ClassRepository {

    List<AlumnoClase> getClassesByIdAlumno(Usuario alumno);
}
