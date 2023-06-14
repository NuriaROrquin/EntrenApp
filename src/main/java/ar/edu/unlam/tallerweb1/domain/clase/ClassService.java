package ar.edu.unlam.tallerweb1.domain.clase;

import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

import java.util.List;

public interface ClassService {

    List<AlumnoClase> getClassesByIdAlumno(Usuario alumno);

}
